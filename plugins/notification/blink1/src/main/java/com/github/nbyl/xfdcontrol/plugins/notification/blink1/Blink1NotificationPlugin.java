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

import com.github.nbyl.xfdcontrol.core.plugins.AbstractNotificationPlugin;
import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import com.github.nbyl.xfdcontrol.core.status.JobStatusChangedEvent;
import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;

@Component
public class Blink1NotificationPlugin extends AbstractNotificationPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(Blink1NotificationPlugin.class);

    @Value("${blink1.tool.path}")
    private String blink1ToolPath;

    @Value("${blink1.statusChange.blinkCount:5}")
    private int statusChangeBlinkCount;

    @Override
    public void jobStatusChanged(JobStatusChangedEvent event) {
        LOGGER.debug("Job status changed to " + event.getNewStatus().getStatus() + ".");
        LOGGER.debug("Job is building: " + event.getNewStatus().isBuilding() + ".");

        JobStatus newStatus = event.getNewStatus();
        if (newStatus.isBuilding() && event.getOldStatus().isPresent()) {
            return;
        }
        Color statusColor = mapStatusToColor(newStatus.getStatus());
        try {
            blink(statusColor);
            changeToColor(statusColor);
        } catch (IOException e) {
            LOGGER.error("There was an error updating the blink1.", e);
        }
    }

    private void blink(Color statusColor) throws IOException {
        new Blink1ToolCommand(this.blink1ToolPath)
                .setColor(statusColor)
                .setBlinkCount(this.statusChangeBlinkCount).run();
    }

    private void changeToColor(Color statusColor) throws IOException {
        new Blink1ToolCommand(this.blink1ToolPath)
                .setColor(statusColor).run();
    }

    @VisibleForTesting
    Color mapStatusToColor(JobStatus.Status status) {
        switch (status) {
            case SUCCESS:
                return Color.green;
            case TESTS_FAILING:
                return Color.YELLOW;
            case FAILED:
                return Color.RED;
        }

        return Color.ORANGE;
    }
}
