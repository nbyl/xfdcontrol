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

package com.github.nbyl.xfdcontrol.plugins.notification.blink1;

import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Blink1NotificationPluginTest {

    private Blink1NotificationPlugin plugin;

    @Before
    public void initPlugin() {
        this.plugin = new Blink1NotificationPlugin();
    }

    @Test
    public void successIsMappedToGreen() {
        assertThat(this.plugin.mapStatusToColor(JobStatus.Status.SUCCESS), is(Color.GREEN));
    }

    @Test
    public void testFailureIsMappedToYellow() {
        assertThat(this.plugin.mapStatusToColor(JobStatus.Status.TESTS_FAILING), is(Color.YELLOW));
    }

    @Test
    public void failureIsMappedToRed() {
        assertThat(this.plugin.mapStatusToColor(JobStatus.Status.FAILED), is(Color.RED));
    }

}