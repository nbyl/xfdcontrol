package com.github.nbyl.xfdcontrol.plugins.notification.blink1;

import com.github.nbyl.xfdcontrol.core.status.JobStatus;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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