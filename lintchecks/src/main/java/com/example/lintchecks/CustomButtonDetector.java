package com.example.lintchecks;

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
import org.w3c.dom.Node;

import java.util.Collection;
import java.util.Collections;

public class CustomButtonDetector extends Detector implements Detector.XmlScanner {

    @Override
    public boolean appliesTo(@NotNull ResourceFolderType folderType) {
        return ResourceFolderType.LAYOUT == folderType;
    }

    @Nullable
    @Override
    public Collection<String> getApplicableElements() {
        return Collections.singletonList("com.example.linttest.CustomButton");
    }

    @Override
    public void visitElement(@NotNull XmlContext context, @NotNull Element element) {
        boolean horizontal = false, left = false, right = false, start = false, end = false, text = false;
        Node leftItem = null, rightItem = null, startItem = null, endItem = null;
        for (int i = 0; i < element.getAttributes().getLength(); i++) {
            Node item = element.getAttributes().item(i);
            if (item.getLocalName().equals("layout_marginHorizontal")) {
                checkMarginAndReport(context, item);
                horizontal = true;
            } else if (item.getLocalName().equals("layout_marginStart")) {
                checkMarginAndReport(context, item);
                startItem = item;
                start = true;
            } else if (item.getLocalName().equals("layout_marginEnd")) {
                checkMarginAndReport(context, item);
                endItem = item;
                end = true;
            } else if (item.getLocalName().equals("layout_marginLeft")) {
                checkMarginAndReport(context, item);
                leftItem = item;
                left = true;
            } else if (item.getLocalName().equals("layout_marginRight")) {
                checkMarginAndReport(context, item);
                rightItem = item;
                right = true;
            } else if (item.getLocalName().equals("text")) {
                if (item.getNodeValue().equals("")) {
                    context.report(missingContentDescriptionIssue,
                            context.getLocation(item),
                            "Missing content description",
                            LintFix.create()
                                    .replace()
                                    .text("\"\"")
                                    .with("\"Sample\"")
                                    .build());
                }
                text = true;
            }
        }
        if (horizontal) {
            if (start) reportRedundantMargin(context, startItem);
            if (end) reportRedundantMargin(context, endItem);
            if (left) reportRedundantMargin(context, leftItem);
            if (right) reportRedundantMargin(context, rightItem);
        } else if (start && end) {
            if (left) reportRedundantMargin(context, leftItem);
            if (right) reportRedundantMargin(context, rightItem);
        } else if (left && right) {
            if (start) reportRedundantMargin(context, startItem);
            if (end) reportRedundantMargin(context, endItem);
        } else {
            context.report(noMarginIssue,
                    context.getLocation(element, LocationType.NAME),
                    "Missing margins",
                    LintFix.create()
                            .replace()
                            .text(element.getNodeValue())
                            .with("com.example.linttest.CustomButton\tandroid:layout_marginHorizontal=\"5dp\"")
                            .build());
        }
        if (!text) {
            context.report(missingContentDescriptionIssue,
                    context.getLocation(element, LocationType.NAME),
                    "Missing content description",
                    LintFix.create()
                            .replace()
                            .text(element.getNodeValue())
                            .with("com.example.linttest.CustomButton\tandroid:text=\"Sample\"")
                            .build());
        }
    }

    public static Issue wrongMarginIssue = Issue.create(
            "Wrong_Margin_ID",
            "Wrong margin",
            "HDB design standards require margins to be exactly 5dp",
            Category.COMPLIANCE,
            7,
            Severity.WARNING,
            new Implementation(CustomButtonDetector.class, Scope.RESOURCE_FILE_SCOPE)
    );

    public static Issue noMarginIssue = Issue.create(
            "Missing_Margin_ID",
            "Missing margin",
            "Please add margins for usability reasons",
            Category.USABILITY,
            10,
            Severity.WARNING,
            new Implementation(CustomButtonDetector.class, Scope.RESOURCE_FILE_SCOPE)
    );

    public static Issue redundantMarginIssue = Issue.create(
            "Redundant_Margin_ID",
            "Redundant margin",
            "This margin is not required",
            Category.CORRECTNESS,
            5,
            Severity.INFORMATIONAL,
            new Implementation(CustomButtonDetector.class, Scope.RESOURCE_FILE_SCOPE)
    );

    public static Issue missingContentDescriptionIssue = Issue.create(
            "Missing_Content_Description_ID",
            "Missing content description",
            "This button needs a label for accessibility services, such as screen readers," +
                    " to use to describe the button's use",
            Category.A11Y,
            5,
            Severity.ERROR,
            new Implementation(CustomButtonDetector.class, Scope.RESOURCE_FILE_SCOPE)
    );

    private void reportRedundantMargin(XmlContext context, Node item) {
        context.report(redundantMarginIssue,
                context.getLocation(item),
                "Redundant margins",
                LintFix.create()
                        .replace()
                        .text(item.getNodeName()+"=\""+item.getNodeValue()+"\"")
                        .with("")
                        .build());
    }

    private void checkMarginAndReport(XmlContext context, Node item) {
        if (!item.getNodeValue().equals("5dp")) {
            context.report(wrongMarginIssue,
                    context.getLocation(item),
                    "Wrong margins",
                    LintFix.create()
                            .replace()
                            .text(item.getNodeValue())
                            .with("5dp")
                            .build());
        }
    }
}