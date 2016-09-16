package org.esco.portlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.esco.portlet.dao.IFlashInfoResource;
import org.esco.portlet.model.FlashInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;


/**
 * Created by jgribonvald on 14/09/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:restDaoContext.xml")
public class FlashInfoDaoTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("flashInfoResourceGson")
    private IFlashInfoResource flashInfoResourceGson;

    @Autowired
    @Qualifier("flashInfoResourceJackson")
    private IFlashInfoResource flashInfoResourceJackson;

    @Autowired
    @Qualifier("resourceGood")
    private Resource resource;

    @Autowired
    @Qualifier("resourceBad")
    private Resource resourceBad;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        //this.restTemplate = new RestTemplate();
        this.mockServer =  MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGson() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line).append('\n');
        }
        br.close();

        List<FlashInfo> fil = flashInfoResourceGson.retrieveInfos(stringBuilder.toString());
        //flashInfoResourceGson.retrieveInfos("https://test-lycee.reciaent.fr/static/mockGuestService");
        Assert.notNull(fil);
        Assert.notEmpty(fil);
        Assert.isTrue(fil.size() == 4);
    }

    @Test
    public void testJackson() throws Exception {
        mockServer.expect(MockRestRequestMatchers.requestTo("/mockGuestService")).andRespond(MockRestResponseCreators.withSuccess(resource, MediaType.APPLICATION_OCTET_STREAM));

        List<FlashInfo> fil = flashInfoResourceJackson.retrieveInfos("/mockGuestService");
        //flashInfoResourceJackson.retrieveInfos("https://test-lycee.reciaent.fr/static/mockGuestService");
        Assert.notNull(fil);
        Assert.notEmpty(fil);
        Assert.isTrue(fil.size() == 4);
    }

    @Test
    public void testGsonBad() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(resourceBad.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line).append('\n');
        }
        br.close();

        List<FlashInfo> fil = flashInfoResourceGson.retrieveInfos(stringBuilder.toString());
        //flashInfoResourceGson.retrieveInfos("https://test-lycee.reciaent.fr/static/mockGuestService");
        Assert.notNull(fil);
        Assert.isTrue(fil.isEmpty());
    }

    @Test
    public void testJacksonBad() throws Exception {
        mockServer.expect(MockRestRequestMatchers.requestTo("/mockGuestService")).andRespond(MockRestResponseCreators.withSuccess(resourceBad, MediaType.APPLICATION_OCTET_STREAM));
        List<FlashInfo> fil = flashInfoResourceJackson.retrieveInfos("/mockGuestService");
        //flashInfoResourceJackson.retrieveInfos("https://test-lycee.reciaent.fr/static/mockGuestService");
        Assert.notNull(fil);
        Assert.isTrue(fil.isEmpty());
    }

}
