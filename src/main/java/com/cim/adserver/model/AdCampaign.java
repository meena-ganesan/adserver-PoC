package com.cim.adserver.model;

import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;

public class AdCampaign {

    @Id
    private Integer partnerId;
    private Integer duration;
    private String adContent;
    private Date campaignStartDt;
    private Date campaignEndDt;

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public Date getCampaignStartDt() {
        return campaignStartDt;
    }

    public void setCampaignStartDt(Date campaignStartDt) {
        this.campaignStartDt = campaignStartDt;
    }

    public Date getCampaignEndDt() {
		return campaignEndDt;
	}

	public void setCampaignEndDt(Date campaignEndDt) {
		this.campaignEndDt = campaignEndDt;
	}

	@Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.partnerId);
        hash = 17 * hash + Objects.hashCode(this.duration);
        hash = 17 * hash + Objects.hashCode(this.adContent);
        hash = 17 * hash + Objects.hashCode(this.campaignStartDt);
        hash = 17 * hash + Objects.hashCode(this.campaignEndDt);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdCampaign other = (AdCampaign) obj;
        if (!Objects.equals(this.partnerId, other.partnerId)) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        if (!Objects.equals(this.adContent, other.adContent)) {
            return false;
        }
        if (!Objects.equals(this.campaignStartDt, other.campaignStartDt)) {
            return false;
        }
        if (!Objects.equals(this.campaignEndDt, other.campaignEndDt)) {
            return false;
        }
        return true;
    }
}
