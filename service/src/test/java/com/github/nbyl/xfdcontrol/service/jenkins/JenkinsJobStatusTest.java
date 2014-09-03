/*
 * Copyright 2014 Nicolas Byl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nbyl.xfdcontrol.service.jenkins;

import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    @Test
    public void isBuildingReturnsTrueOnStatus() {
        assertThat(new JenkinsJobStatus("test-job", "red_anime").isBuilding(), is(true));
    }

    @Test
    public void isBuildingReturnsFalseOnStatus() {
        assertThat(new JenkinsJobStatus("test-job", "red").isBuilding(), is(false));
    }
}