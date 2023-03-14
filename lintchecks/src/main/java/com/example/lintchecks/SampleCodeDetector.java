package com.example.lintchecks;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.LintFix;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.ULiteralExpression;

import java.util.Collections;
import java.util.List;

public class SampleCodeDetector extends Detector implements Detector.UastScanner {
    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        return Collections.singletonList(ULiteralExpression.class);
    }
    @Override
    public UElementHandler createUastHandler(@NotNull JavaContext context) {

        return new UElementHandler() {
            @Override
            public void visitLiteralExpression(@NotNull ULiteralExpression node) {
                if (node.getValue() != null) {
                    String string = node.getValue().toString();
                    if (string.contains("lint")) {
                        context.report(
                                javaLintIssue, node, context.getLocation(node),
                                "This code mentions `lint`: **Congratulations!!!**",
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
                .with("new text")
                .build();
    }

    public static Issue javaLintIssue = Issue.create(
            "SampleLintId",
            "Lint Mentions",
            "This check highlights string literals in code which mentions the word `lint`. Blah blah blah. Another paragraph here.",
            Category.MESSAGES,
            6,
            Severity.INFORMATIONAL,
            new Implementation(SampleCodeDetector.class, Scope.JAVA_FILE_SCOPE)
    );
}
