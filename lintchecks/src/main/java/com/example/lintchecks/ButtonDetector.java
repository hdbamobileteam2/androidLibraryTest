package com.example.lintchecks;

import com.android.SdkConstants;
import com.android.resources.ResourceFolderType;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.LintFix;
import com.android.tools.lint.detector.api.LocationType;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Element;

import java.util.Collection;
import java.util.Collections;

public class ButtonDetector extends Detector implements Detector.XmlScanner {

    @Override
    public boolean appliesTo(@NotNull ResourceFolderType folderType) {
        return ResourceFolderType.LAYOUT == folderType;
    }

    @Nullable
    @Override
    public Collection<String> getApplicableElements() {
        return Collections.singletonList(SdkConstants.BUTTON);
    }

    @Override
    public void visitElement(@NotNull XmlContext context, @NotNull Element element) {
        // report the issue to the developer or the user
        context.report(buttonIssue,
                context.getLocation(element, LocationType.NAME),
                ButtonInfo.USAGES,
                createFix());
    }

    private LintFix createFix() {
        return LintFix.create()
                .replace()
                .text(SdkConstants.BUTTON)
                .with(ButtonInfo.CUSTOM_VIEW)
                .build();
    }

    public static Issue buttonIssue = Issue.create(
            ButtonInfo.ID,
            ButtonInfo.USAGES,
            ButtonInfo.EXPLANATION,
            Category.COMPLIANCE,
            10,
            Severity.ERROR,
            new Implementation(ButtonDetector.class, Scope.RESOURCE_FILE_SCOPE)
    );

    public static class ButtonInfo {
        public static String ID = "Button_ID";
        public static String USAGES = "Try to use the team custom view";
        public static String EXPLANATION = "The team is trying to use a custom view in place of" +
                " the normal android button widget, Custom View ==> CustomButton";
        public static String CUSTOM_VIEW = "com.example.linttest.CustomButton";
    }


}