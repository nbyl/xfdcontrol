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

package com.github.nbyl.xfdcontrol.web;

import com.github.nbyl.xfdcontrol.core.status.JobStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatusController {

    @Autowired
    public JobStatusService statusService;

    @RequestMapping("/status")
    public ModelAndView showStatusPage() {
        return new ModelAndView("status");
    }

    @RequestMapping("/status/view")
    public ModelAndView getCurrentStatus() {
        return new ModelAndView("status-view", "jobStatus", this.statusService.getLastStatus());
    }
}
