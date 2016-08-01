package com.cim.adserver.common;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class AdServerUtilTest {
	
	@Test
	public void testAddDuration() {
		Date startDt = new Date();
		Date endDt = AdServerUtil.addDuration(startDt, 60);
		Assert.assertNotNull(endDt);
		Assert.assertTrue(endDt.after(startDt));
	}
	
	@Test
	public void testNullAddDuration() {
		Date startDt = null;
		Date endDt = AdServerUtil.addDuration(startDt, 60);
		Assert.assertNull(endDt);
	}
}
