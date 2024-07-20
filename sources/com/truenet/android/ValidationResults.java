package com.truenet.android;

import com.startapp.common.p046c.C5303f;
import java.util.ArrayList;
import java.util.List;
import p055a.p056a.p058b.p060b.C2928h;

/* compiled from: StartAppSDK */
public final class ValidationResults {
    @C5303f(mo45478b = ArrayList.class, mo45479c = ValidationResult.class)
    private final List<ValidationResult> results;

    public static /* synthetic */ ValidationResults copy$default(ValidationResults validationResults, List<ValidationResult> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = validationResults.results;
        }
        return validationResults.copy(list);
    }

    public final List<ValidationResult> component1() {
        return this.results;
    }

    public final ValidationResults copy(List<ValidationResult> list) {
        C2928h.m6157b(list, "results");
        return new ValidationResults(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ValidationResults) && C2928h.m6156a((Object) this.results, (Object) ((ValidationResults) obj).results);
        }
        return true;
    }

    public int hashCode() {
        List<ValidationResult> list = this.results;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ValidationResults(results=" + this.results + ")";
    }

    public ValidationResults(List<ValidationResult> list) {
        C2928h.m6157b(list, "results");
        this.results = list;
    }

    public final List<ValidationResult> getResults() {
        return this.results;
    }
}
