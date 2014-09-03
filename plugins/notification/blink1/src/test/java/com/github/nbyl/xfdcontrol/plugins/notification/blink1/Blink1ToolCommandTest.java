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

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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