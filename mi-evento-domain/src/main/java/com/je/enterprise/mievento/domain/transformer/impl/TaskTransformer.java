package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Task;
import com.je.enterprise.mievento.domain.entity.common.event.TaskEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class TaskTransformer extends Transformer<TaskEntity, Task>{

	public TaskTransformer() {
	}

	@Override
	public Task transformDomainToApi(TaskEntity domainObject) {
		return new Task(domainObject.getName(),domainObject.getInitialDate(), domainObject.getFinalDate());
	}

	@Override
	public TaskEntity transformApiToDomain(Task apiObject) {
		return new TaskEntity(apiObject.getName(),apiObject.getInitialDate(), apiObject.getFinalDate());
	}

}
