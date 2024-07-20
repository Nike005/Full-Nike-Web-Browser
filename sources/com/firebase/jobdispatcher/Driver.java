package com.firebase.jobdispatcher;

public interface Driver {
    int cancel(String str);

    int cancelAll();

    JobValidator getValidator();

    boolean isAvailable();

    int schedule(Job job);
}
