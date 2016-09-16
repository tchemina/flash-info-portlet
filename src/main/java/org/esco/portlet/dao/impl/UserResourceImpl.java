package org.esco.portlet.dao.impl;

import javax.portlet.PortletRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.esco.portlet.dao.IUserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserResourceImpl implements IUserResource/*, InitializingBean*/{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    private String currentEtabCodeInfoKey;

    private IUserResource mockedUserResource;

    /*public String getCurrentEtabCode(final PortletRequest request) {
        String escoUaiCourant = null;

        final List<String> uaiCourant = this.getUserInfo(request, this.currentEtabCodeInfoKey);

        if (uaiCourant.size() == 1) {
            // Monovalued attribute
            escoUaiCourant = uaiCourant.iterator().next().toLowerCase();
        }

        if (!StringUtils.hasText(escoUaiCourant)) {
            escoUaiCourant = null;
            log.warn("Unable to retrieve {} attribute in Portal UserInfo !", this.currentEtabCodeInfoKey);
        }

        return escoUaiCourant;
    }*/

    /**
     * Retrieve the user info attribute from portlet context, or the Mocked user info
     *
     * @param request the portlet request
     * @param attributeName the attribute to retrieve
     * @return the user info attribute values
     */
    @SuppressWarnings("unchecked")
    public List<String> getUserInfo(final PortletRequest request, final String attributeName) {
        Map<String, List<String>> userInfo =
                (Map<String, List<String>>) request.getAttribute("org.jasig.portlet.USER_INFO_MULTIVALUED");

        List<String> attributeValues = null;

        if (userInfo != null) {
            attributeValues = userInfo.get(attributeName);
        } else if ("true".equals(System.getProperty("flash-info-portlet.testEnv"))) {
            attributeValues = this.mockedUserResource.getUserInfo(null, attributeName);
        } else {
            log.error("Unable to retrieve Portal UserInfo !");
            //throw new IllegalStateException("Unable to retrieve Portal UserInfo !");
        }

        if (attributeValues == null) {
            attributeValues = Collections.EMPTY_LIST;
        }

        return attributeValues;
    }

//    public void afterPropertiesSet() throws Exception {
//        Assert.hasText(this.currentEtabCodeInfoKey, "No property currentEtabCodeInfoKey is set !");
//    }

//    public String getCurrentEtabCodeInfoKey() {
//        return currentEtabCodeInfoKey;
//    }
//
//    public void setCurrentEtabCodeInfoKey(String currentEtabCodeInfoKey) {
//        this.currentEtabCodeInfoKey = currentEtabCodeInfoKey;
//    }

    public IUserResource getMockedUserResource() {
        return mockedUserResource;
    }

    public void setMockedUserResource(IUserResource mockedUserResource) {
        this.mockedUserResource = mockedUserResource;
    }

}
