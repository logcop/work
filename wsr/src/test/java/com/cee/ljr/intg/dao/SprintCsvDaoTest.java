package com.cee.ljr.intg.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.dao.impl.SprintCsvDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class SprintCsvDaoTest {
	
	@Autowired
	SprintCsvDao sprintCsvDao;
	
	@Test
	public void testGetAll() {
		List<Sprint> sprintList = sprintCsvDao.getAll();
		
		Assert.assertNotNull(sprintList);
		Assert.assertFalse(sprintList.isEmpty());
		System.out.println(sprintList);
	}
	
	@Test
	public void testGetByNumber() {
		int sprintNumber = 1;
		
		List<Sprint> sprintList = sprintCsvDao.getByNumber(sprintNumber);
		
		Assert.assertNotNull(sprintList);
		Assert.assertFalse(sprintList.isEmpty());
		System.out.println(sprintList);
	}
}
