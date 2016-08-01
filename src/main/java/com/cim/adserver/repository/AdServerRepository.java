package com.cim.adserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cim.adserver.model.AdCampaign;

public interface AdServerRepository extends MongoRepository<AdCampaign, Integer>{

}
