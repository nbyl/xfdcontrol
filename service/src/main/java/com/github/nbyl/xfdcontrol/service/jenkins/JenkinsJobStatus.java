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
import com.google.common.base.Objects;
import com.google.common.base.Strings;

public class JenkinsJobStatus implements JobStatus {

    private String name;

    private String color;

    public JenkinsJobStatus(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public JenkinsJobStatus() {
    }

    @Override
    public Status getStatus() {
        if (Strings.isNullOrEmpty(this.color)) {
            return Status.ERROR;
        } else if (this.color.startsWith("blue")) {
            return Status.SUCCESS;
        } else if (this.color.startsWith("yellow")) {
            return Status.TESTS_FAILING;
        } else if (this.color.startsWith("red")) {
            return Status.FAILED;
        }

        return Status.ERROR;
    }

    @Override
    public boolean isBuilding() {
        if (Strings.isNullOrEmpty(this.color)) {
            return false;
        }
        return this.color.endsWith("_anime");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, color);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final JenkinsJobStatus other = (JenkinsJobStatus) obj;
        return Objects.equal(this.name, other.name) && Objects.equal(this.color, other.color);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("color", color)
                .add("name", name)
                .toString();
    }

}
