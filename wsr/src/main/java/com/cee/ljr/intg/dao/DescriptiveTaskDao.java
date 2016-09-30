package com.cee.ljr.intg.dao;

import java.util.List;

import com.cee.ljr.domain.common.DescriptiveTask;

public interface DescriptiveTaskDao {

	List<DescriptiveTask> getAllSprintTasksForDeveloper(String developerName, List<String> sprintNames);
}
