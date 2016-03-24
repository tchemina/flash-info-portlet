/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.esco.portlet.mvc.portlet;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * HelpController is a simple controller that displays the help interface
 */
@Controller
@RequestMapping("HELP")
public class HelpController {

    protected final Log logger = LogFactory.getLog(getClass());
    
    /**
     * Returns the help view.  The help view is a very simple JSP, so we don't
     * both returning a model.
     * 
     * @param request
     * @param response
     * @return
     */
    @RenderMapping
    public ModelAndView showHelpView(final RenderRequest request, final RenderResponse response) {
        final String viewName = "help";
        final ModelAndView mav = new ModelAndView(viewName);
        
        if(logger.isDebugEnabled()) {
            logger.debug("Using view name " + viewName + " for help view");
        }

        return mav;
    }

    @ActionMapping
    public void doAction() {
        // no-op action mapping to prevent accidental calls to this URL from
        // crashing the portlet
    }

}
