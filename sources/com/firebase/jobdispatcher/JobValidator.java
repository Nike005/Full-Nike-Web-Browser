package com.firebase.jobdispatcher;

import java.util.List;

public interface JobValidator {
    List<String> validate(JobParameters jobParameters);

    List<String> validate(JobTrigger jobTrigger);

    List<String> validate(RetryStrategy retryStrategy);
}
