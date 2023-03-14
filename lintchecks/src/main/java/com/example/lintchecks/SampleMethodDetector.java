package com.example.lintchecks;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.LintFix;
import com.android.tools.lint.detector.api.LocationType;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.lang.jvm.JvmParameter;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UComment;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UMethod;

import java.util.Collections;
import java.util.List;

public class SampleMethodDetector extends Detector implements Detector.UastScanner {
    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        return Collections.singletonList(UMethod.class);
    }

    @Nullable
    @Override
    public UElementHandler createUastHandler(@NotNull JavaContext context) {
        return new UElementHandler() {
            @Override
            public void visitMethod(@NotNull UMethod node) {
                String string = node.getName();
                if (string.contains("lint")) {
                    context.report(
                            lintMethodIssue, node, context.getLocation(node, LocationType.NAME),
                            "This method mentions `lint`: **Congratulations!!!**"
                    );
                }
                for (JvmParameter parameter: node.getParameters()) {
                    if (parameter.getName().contains("lint")) {
                        context.report(
                                lintMethodParameterIssue, (PsiElement) parameter, context.getLocation((PsiElement) parameter),
                                "This method parameter mentions `lint`: **Congratulations!!!**"
                        );
                    }
                }
                for (UComment comment: node.getUastBody().getComments()) {
                    if (comment.getText().contains("lint")) {
                        context.report(
                                lintMethodCommentIssue, comment, context.getLocation(comment),
                                "This method comment mentions `lint`: **Congratulations!!!**",
                                createFix()
                        );
                    }
                }
            }
        };
    }

    private LintFix createFix() {
        return LintFix.create()
                .replace()
                .text("lint")
                .with("nice")
                .build();
    }

    public static Issue lintMethodIssue = Issue.create(
            "LintMethodId",
            "Lint Mentions",
            "This check highlights methods in code which mentions the word `lint`. Blah blah blah. Another paragraph here.",
            Category.TESTING,
            3,
            Severity.WARNING,
            new Implementation(SampleMethodDetector.class, Scope.JAVA_FILE_SCOPE)
    );

    public static Issue lintMethodParameterIssue = Issue.create(
            "LintMethodParameterId",
            "Lint Mentions",
            "This check highlights parameters of methods in code which mentions the word `lint`. Blah blah blah. Another paragraph here.",
            Category.TESTING,
            3,
            Severity.INFORMATIONAL,
            new Implementation(SampleMethodDetector.class, Scope.JAVA_FILE_SCOPE)
    );


    public static Issue lintMethodCommentIssue = Issue.create(
            "LintMethodCommentId",
            "Lint Mentions",
            "This check highlights comments in methods in code which mentions the word `lint`. Blah blah blah. Another paragraph here.",
            Category.TESTING,
            3,
            Severity.INFORMATIONAL,
            new Implementation(SampleMethodDetector.class, Scope.JAVA_FILE_SCOPE)
    );
}
