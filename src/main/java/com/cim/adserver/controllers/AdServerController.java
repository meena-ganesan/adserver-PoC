package com.cim.adserver.controllers;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cim.adserver.common.AdServerUtil;
import com.cim.adserver.model.AdCampaign;
import com.cim.adserver.repository.AdServerRepository;

@RestController
@RequestMapping("/ad")
public class AdServerController {

	@Autowired
	private AdServerRepository adServerRepository;

	@RequestMapping(method = RequestMethod.POST)
	public AdCampaign createCampaign(@RequestBody AdCampaign adCampaign){
		DateTime startDt = new DateTime();
		
		//Check if an active campaign already exists
		AdCampaign existingCampaign = adServerRepository.findOne(adCampaign.getPartnerId());
		adCampaign.setCampaignStartDt(startDt.toDate());
		Date endDt = AdServerUtil.addDuration(adCampaign.getCampaignStartDt(), adCampaign.getDuration());
		if(existingCampaign != null && new DateTime(existingCampaign.getCampaignEndDt()).getMillis() >= startDt.getMillis()) {
			return null;
		}
		adCampaign.setCampaignEndDt(endDt);
		return adServerRepository.save(adCampaign);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fetchCampaign")
	public AdCampaign fetchCampaign(@RequestParam(value="partner_id") Integer partnerId){
		DateTime startDt = new DateTime();
		AdCampaign existingCampaign = adServerRepository.findOne(partnerId);
		if(existingCampaign != null && new DateTime(existingCampaign.getCampaignEndDt()).getMillis() < startDt.getMillis()) {
			return null;
		}
		return existingCampaign;
	}
}
