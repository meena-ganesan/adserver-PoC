package com.cim.adserver.controllers;

import com.cim.adserver.model.AdCampaign;
import com.cim.adserver.repository.AdServerRepository;
import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AdServerControllerTest {

    @InjectMocks
    private final AdServerController adServerController = new AdServerController();

    @Mock
    private AdServerRepository adServerRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(adServerRepository.save(Mockito.any(AdCampaign.class))).thenReturn(getMockCampaign());
    }

    @Test
    public void testCreateCampaign_NoExistingCampaign() {
        AdCampaign adCampaign = getMockCampaign();
        Mockito.when(adServerRepository.findOne(Mockito.anyInt())).thenReturn(null);
        AdCampaign response = adServerController.createCampaign(adCampaign);
        Assert.assertNotNull(response);
    }

    @Test
    public void testCreateCampaign_ExpiredExistingCampaign() {
        AdCampaign adCampaign = getMockCampaign();
        Mockito.when(adServerRepository.findOne(Mockito.anyInt())).thenReturn(getMockExpiredCampaign());
        AdCampaign response = adServerController.createCampaign(adCampaign);
        Assert.assertNotNull(response);
    }
    
    @Test
    public void testCreateCampaign_ActiveExistingCampaign() {
        AdCampaign adCampaign = getMockCampaign();
        Mockito.when(adServerRepository.findOne(Mockito.anyInt())).thenReturn(getMockActiveCampaign());
        AdCampaign response = adServerController.createCampaign(adCampaign);
        Assert.assertNull(response);
    }
    
    @Test
    public void testFetchCampaign_NoExistingCampaign() {
        Mockito.when(adServerRepository.findOne(Mockito.anyInt())).thenReturn(null);
        AdCampaign response = adServerController.fetchCampaign(1);
        Assert.assertNull(response);
    }
    
    @Test
    public void testFetchCampaign_ExpiredExistingCampaign() {
        Mockito.when(adServerRepository.findOne(Mockito.anyInt())).thenReturn(getMockExpiredCampaign());
        AdCampaign response = adServerController.fetchCampaign(1);
        Assert.assertNull(response);
    }
    
    @Test
    public void testFetchCampaign_ActiveExistingCampaign() {
        Mockito.when(adServerRepository.findOne(Mockito.anyInt())).thenReturn(getMockActiveCampaign());
        AdCampaign response = adServerController.fetchCampaign(1);
        Assert.assertNotNull(response);
    }

    private AdCampaign getMockCampaign() {
        AdCampaign adCampaign = new AdCampaign();
        adCampaign.setPartnerId(1);
        adCampaign.setAdContent("This is test ad content");
        adCampaign.setDuration(60);
        return adCampaign;
    }

    private AdCampaign getMockExpiredCampaign() {
        AdCampaign adCampaign = new AdCampaign();
        adCampaign.setPartnerId(1);
        adCampaign.setAdContent("This is test ad content");
        adCampaign.setCampaignStartDt(getPastTime());
        adCampaign.setCampaignEndDt(getPastTime());
        adCampaign.setDuration(60);
        return adCampaign;
    }
    
    private AdCampaign getMockActiveCampaign() {
        AdCampaign adCampaign = new AdCampaign();
        adCampaign.setPartnerId(1);
        adCampaign.setAdContent("This is test ad content");
        adCampaign.setCampaignStartDt(getPastTime());
        adCampaign.setCampaignEndDt(getFutureTime());
        adCampaign.setDuration(60);
        return adCampaign;
    }

    private Date getPastTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -2);
        return cal.getTime();
    }

    private Date getFutureTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 2);
        return cal.getTime();
    }
}
