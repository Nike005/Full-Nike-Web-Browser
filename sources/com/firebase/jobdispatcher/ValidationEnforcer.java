package com.firebase.jobdispatcher;

import android.text.TextUtils;
import java.util.List;

public class ValidationEnforcer implements JobValidator {
    private final JobValidator mValidator;

    public ValidationEnforcer(JobValidator jobValidator) {
        this.mValidator = jobValidator;
    }

    public List<String> validate(JobParameters jobParameters) {
        return this.mValidator.validate(jobParameters);
    }

    public List<String> validate(JobTrigger jobTrigger) {
        return this.mValidator.validate(jobTrigger);
    }

    public List<String> validate(RetryStrategy retryStrategy) {
        return this.mValidator.validate(retryStrategy);
    }

    public final boolean isValid(JobParameters jobParameters) {
        return validate(jobParameters) == null;
    }

    public final boolean isValid(JobTrigger jobTrigger) {
        return validate(jobTrigger) == null;
    }

    public final boolean isValid(RetryStrategy retryStrategy) {
        return validate(retryStrategy) == null;
    }

    public final void ensureValid(JobParameters jobParameters) {
        ensureNoErrors(validate(jobParameters));
    }

    public final void ensureValid(JobTrigger jobTrigger) {
        ensureNoErrors(validate(jobTrigger));
    }

    public final void ensureValid(RetryStrategy retryStrategy) {
        ensureNoErrors(validate(retryStrategy));
    }

    private void ensureNoErrors(List<String> list) {
        if (list != null) {
            throw new ValidationException("JobParameters is invalid", list);
        }
    }

    public static final class ValidationException extends RuntimeException {
        private final List<String> mErrors;

        public ValidationException(String str, List<String> list) {
            super(str + ": " + TextUtils.join("\n  - ", list));
            this.mErrors = list;
        }

        public List<String> getErrors() {
            return this.mErrors;
        }
    }
}
