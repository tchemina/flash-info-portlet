package org.esco.portlet.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.esco.portlet.dao.IFlashInfoResource;
import org.esco.portlet.model.FlashInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Created by jgribonvald on 13/09/16.
 */
public class MockFlashInfoResourceImpl implements IFlashInfoResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private List<FlashInfo> getServiceInfos(String url) {
        if (log.isDebugEnabled()) {
            log.debug("Requesting Flash Infos on URL {}", url );
        }

        Assert.isTrue(url.matches("^https?://[a-z0-9+.-]+(:[0-9]{1,4})?/.*"));

        List<FlashInfo> flL = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final String imgL = "https//my.domain.com/images/test_" + i + ".png";
            final String title = "un titre " + i;
            final String text = "un text " + i;
            final String kml = "https//my.domain.com/test_" + i;
            final String alt = title;
            final FlashInfo info = new FlashInfo(imgL, title, text, kml, alt);
            flL.add(info);
        }
        return flL;
    }

    public List<FlashInfo> retrieveInfos(String flashUrl) {
        return this.getServiceInfos(flashUrl);
    }
}
