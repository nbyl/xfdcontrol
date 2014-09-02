package com.github.nbyl.xfdcontrol.service.jenkins;

import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JenkinsJobStatusTest {

    @Test
    public void getStatusReturnsErrorForNullValue() {
        assertThat(new JenkinsJobStatus("test-job", null).getStatus(), is(JobStatus.Status.ERROR));
    }

    @Test
    public void getStatusReturnsSuccessForBlue() {
        assertThat(new JenkinsJobStatus("test-job", "blue").getStatus(), is(JobStatus.Status.SUCCESS));
    }

    @Test
    public void getStatusReturnsTestFailingForYellow() {
        assertThat(new JenkinsJobStatus("test-job", "yellow").getStatus(), is(JobStatus.Status.TESTS_FAILING));
    }

    @Test
    public void getStatusReturnsFailedForRed() {
        assertThat(new JenkinsJobStatus("test-job", "red").getStatus(), is(JobStatus.Status.FAILED));
    }

    @Test public void isBuildingReturnsTrueOnStatus() {
        assertThat(new JenkinsJobStatus("test-job", "red_anime").isBuilding(), is(true));
    }

    @Test public void isBuildingReturnsFalseOnStatus() {
        assertThat(new JenkinsJobStatus("test-job", "red").isBuilding(), is(false));
    }
}