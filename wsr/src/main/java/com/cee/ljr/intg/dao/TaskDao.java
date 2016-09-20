package com.cee.ljr.intg.dao;

import java.util.List;

import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.domain.common.Task;

public interface TaskDao {

	List<Task> getAllSprintTasksForDeveloper(Sprint sprint, Developer developer);
}
