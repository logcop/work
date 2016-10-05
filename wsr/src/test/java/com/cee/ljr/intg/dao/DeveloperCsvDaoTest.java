package com.cee.ljr.intg.dao;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.dao.impl.DeveloperCsvDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class DeveloperCsvDaoTest {

	@Autowired
	DeveloperCsvDao developerCsvDao;
	
	@Test
	public void testGetAll() {
		Set<Developer> developerList = developerCsvDao.getAll();
		
		Assert.assertNotNull(developerList);
		Assert.assertFalse(developerList.isEmpty());
		System.out.println(developerList);
	}
}
