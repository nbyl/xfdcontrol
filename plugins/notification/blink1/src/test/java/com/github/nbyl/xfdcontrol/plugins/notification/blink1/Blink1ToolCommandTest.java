package com.github.nbyl.xfdcontrol.plugins.notification.blink1;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Blink1ToolCommandTest {

    private Blink1ToolCommand command;

    @Before
    public void createCommand() {
        this.command = new Blink1ToolCommand("blink1-tool");
    }

    @Test
    public void mapColorToRgbArgumentForRed() {
        assertThat(this.command.mapColorToRgbArgument(Color.RED), is("255,0,0"));
    }

    @Test
    public void mapColorToRgbArgumentForGreen() {
        assertThat(this.command.mapColorToRgbArgument(Color.GREEN), is("0,255,0"));
    }

    @Test
    public void mapColorToRgbArgumentForBlue() {
        assertThat(this.command.mapColorToRgbArgument(Color.BLUE), is("0,0,255"));
    }

    @Test
    public void mapColorToRgbArgumentForOrange() {
        assertThat(this.command.mapColorToRgbArgument(Color.ORANGE), is("255,200,0"));
    }
}