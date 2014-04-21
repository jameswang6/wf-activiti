package com.sanfutech.wf;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class Demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		ProcessEngine processEngine = 
				 ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
				 .buildProcessEngine();
		
		// get service.
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		repositoryService.createDeployment()
		.addClasspathResource("diagrams/financialReport.bpmn")
		.deploy();
		
		// Start a process instance.
		runtimeService.startProcessInstanceByKey("financialReport");
		
		int exec = runtimeService.createExecutionQuery().list().size();
		
		TaskService taskService = processEngine.getTaskService();
		
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
		
		for( Task t: tasks){
			System.out.println("Task name:" + t.getName());
			
		}
		

	}

}
