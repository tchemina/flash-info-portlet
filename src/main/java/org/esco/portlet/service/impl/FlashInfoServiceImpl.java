package org.esco.portlet.service.impl;

import javax.portlet.PortletRequest;

import java.util.List;

import com.google.common.collect.Lists;
import org.esco.portlet.dao.IFlashInfoResource;
import org.esco.portlet.model.FlashInfo;
import org.esco.portlet.service.IFlashInfoService;
import org.esco.portlet.service.bean.IFlashUrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jgribonvald on 14/09/16.
 */
@Service
public class FlashInfoServiceImpl implements IFlashInfoService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String PREF_FLASH_URL = "flashUrl";

    @Autowired
    private IFlashInfoResource flashInfoResource;

    @Autowired
    private IFlashUrlBuilder flashUrlBuilder;


    public List<FlashInfo> retrieveFlashInfos(final PortletRequest request) {
        final String flashUrl = request.getPreferences().getValue(PREF_FLASH_URL, "false");
        if (log.isDebugEnabled()) {
            log.debug("Preference flash url is {}",flashUrl);
        }

        if (flashUrl == null || flashUrl.trim().isEmpty() ) {
            return Lists.newArrayList();
        }

        // case of url is relative
        String rewroteUrl = flashUrlBuilder.transform(request, flashUrl);
        if (rewroteUrl == null || rewroteUrl.trim().isEmpty()) {
            return Lists.newArrayList();
        }

        if (log.isDebugEnabled()) {
            log.debug("After url completion flashUrl is {}",rewroteUrl );
        }

        return flashInfoResource.retrieveInfos(rewroteUrl);
    }

    public IFlashInfoResource getFlashInfoResource() {
        return flashInfoResource;
    }

    public void setFlashInfoResource(final IFlashInfoResource flashInfoResource) {
        this.flashInfoResource = flashInfoResource;
    }

    public IFlashUrlBuilder getFlashUrlBuilder() {
        return flashUrlBuilder;
    }

    public void setFlashUrlBuilder(final IFlashUrlBuilder flashUrlBuilder) {
        this.flashUrlBuilder = flashUrlBuilder;
    }

    public static String getPrefFlashUrl() {
        return PREF_FLASH_URL;
    }

}
