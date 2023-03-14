package com.example.lintchecks;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SampleIssueRegistry extends IssueRegistry {
    @NotNull
    @Override
    public List<Issue> getIssues() {
        ArrayList<Issue> list = new ArrayList<>();
        list.addAll(Collections.singletonList(ButtonDetector.buttonIssue));
        list.addAll(Collections.singletonList(CustomButtonDetector.wrongMarginIssue));
        list.addAll(Collections.singletonList(CustomButtonDetector.noMarginIssue));
        list.addAll(Collections.singletonList(CustomButtonDetector.redundantMarginIssue));
        list.addAll(Collections.singletonList(CustomButtonDetector.missingContentDescriptionIssue));
        list.addAll(Collections.singletonList(SampleCodeDetector.javaLintIssue));
        list.addAll(Collections.singletonList(SampleMethodDetector.lintMethodIssue));
        list.addAll(Collections.singletonList(SampleMethodDetector.lintMethodParameterIssue));
        list.addAll(Collections.singletonList(SampleMethodDetector.lintMethodCommentIssue));
        return list;
    }

    @Override
    public int getApi() {
        return ApiKt.CURRENT_API;
    }

//    @Override
//    public int getMinApi() {
//        return 8;
//    }

//    @Override
//    public Vendor getVendor() {
//        return new Vendor(
//                "Android Open Source Project",
//                "",
//                "https://github.com/googlesamples/android-custom-lint-rules/issues",
//                "https://github.com/googlesamples/android-custom-lint-rules"
//        );
//    }
}
