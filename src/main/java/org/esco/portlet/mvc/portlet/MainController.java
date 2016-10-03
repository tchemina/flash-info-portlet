/**
 * Copyright © 2016 ESUP-Portail (https://www.esup-portail.org/)
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
package org.esco.portlet.mvc.portlet;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import java.util.List;

import org.esco.portlet.model.FlashInfo;
import org.esco.portlet.service.IFlashInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * Main portlet view.
 */
@Controller
@RequestMapping("VIEW")
public class MainController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IFlashInfoService flashInfoService;

    @SuppressWarnings("unchecked")
    @RenderMapping
    public ModelAndView showMainView(final RenderRequest request, final RenderResponse response) {
        final String viewName = "main";        
        final ModelAndView mav = new ModelAndView(viewName);
        
        if(log.isDebugEnabled()) {
            log.debug("Using view name " + viewName + " for main view");
        }

        List<FlashInfo> flashInfos = this.flashInfoService.retrieveFlashInfos(request);

        mav.addObject("flashinfos", flashInfos);

        if(log.isDebugEnabled()) {
            log.debug("Rendering main view");
        }
        return mav;
    }

    @ActionMapping
    public void doAction() {
        // no-op action mapping to prevent accidental calls to this URL from
        // crashing the portlet
    }

    public IFlashInfoService getFlashInfoService() {
        return flashInfoService;
    }

    public void setFlashInfoService(IFlashInfoService flashInfoService) {
        this.flashInfoService = flashInfoService;
    }

}
