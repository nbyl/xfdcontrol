package com.github.nbyl.xfdcontrol.core.status;

public interface JobStatus {

    enum Status {
        SUCCESS,
        TESTS_FAILING,
        FAILED,
        ERROR
    };

    Status getStatus();

    boolean isBuilding();
}
