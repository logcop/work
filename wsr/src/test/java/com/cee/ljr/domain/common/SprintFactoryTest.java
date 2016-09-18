package com.cee.ljr.domain.common;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class SprintFactoryTest {

	@Autowired
	SprintFactory sprintFactory;
	
	@Test
	public void testGetSprint() {
		String sprintName = "PACAF Sprint 13";
		Sprint sprint =	sprintFactory.getSprint(sprintName);
		assertNotNull(sprint);
		assertTrue(sprintName.equals(sprint.getName()));
		assertTrue(13 == sprint.getNumber());
		assertNotNull(sprint.getStartDate());
		assertNotNull(sprint.getEndDate());
		System.out.println(sprint);
	}
	
	@Test
	public void testGetSprintsByNumber() {
		List<Sprint> sprintList = sprintFactory.getSprintsByNumber(14);
		assertFalse(sprintList.isEmpty());
	}
}
