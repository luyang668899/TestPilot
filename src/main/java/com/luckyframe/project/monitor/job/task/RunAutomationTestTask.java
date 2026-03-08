package com.luckyframe.project.monitor.job.task;

import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.luckyframe.common.constant.ClientConstants;
import com.luckyframe.common.utils.client.HttpRequest;
import com.luckyframe.common.utils.client.RunTaskEntity;
import com.luckyframe.common.utils.client.TaskAssignmentService;
import com.luckyframe.project.system.client.domain.Client;
import com.luckyframe.project.system.client.service.IClientService;
import com.luckyframe.project.testexecution.taskExecute.domain.TaskExecute;
import com.luckyframe.project.testexecution.taskExecute.service.ITaskExecuteService;
import com.luckyframe.project.testexecution.taskScheduling.domain.TaskScheduling;
import com.luckyframe.project.testexecution.taskScheduling.service.ITaskSchedulingService;

/**
 * 测试任务调度客户端
 * =================================================================
 * 这是一个受限制的自由软件！您不能在任何未经允许的前提下对程序代码进行修改和用于商业用途；也不允许对程序代码修改后以任何形式任何目的的再发布。
 * 为了尊重作者的劳动成果，LuckyFrame关键版权信息严禁篡改 有任何疑问欢迎联系作者讨论。 QQ:1573584944 Seagull
 * =================================================================
 * @author Seagull
 * @date 2019年3月26日
 */
@Component("runAutomationTestTask")
public class RunAutomationTestTask
{
	@Autowired
	private ITaskExecuteService taskExecuteService;
	
	@Autowired
	private ITaskSchedulingService taskSchedulingService;
	
	@Autowired
	private IClientService clientService;
	
    private static final Logger log = LoggerFactory.getLogger(RunAutomationTestTask.class);
	
	public static RunAutomationTestTask runAutomationTestTask;
	public static IClientService clientServiceStatic;

	@PostConstruct
	public void init() {
		runAutomationTestTask = this;
		clientServiceStatic = clientService;
	}
	
    public void runTask(String params) throws IOException {
		TaskScheduling taskScheduling = taskSchedulingService.selectTaskSchedulingById(Integer.valueOf(params));
		
		if(null!=taskScheduling){
			String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String taskName ="【"+taskScheduling.getSchedulingName()+ "】_"+time;
			TaskExecute taskExecute = new TaskExecute();
			taskExecute.setSchedulingId(taskScheduling.getSchedulingId());
			taskExecute.setProjectId(taskScheduling.getProjectId());
			taskExecute.setTaskName(taskName);
			taskExecute.setTaskStatus(0);
			taskExecuteService.insertTaskExecute(taskExecute);
			
			// 获取所有可用的客户端
			List<Client> availableClients = clientService.selectClientList(new Client());
			
			// 使用任务分配服务选择最合适的客户端
			Client selectedClient = TaskAssignmentService.selectBestClient(taskScheduling, availableClients);
			
			if (selectedClient != null) {
				String url= "http://"+selectedClient.getClientIp()+":"+ClientConstants.CLIENT_MONITOR_PORT+"/runTask";
				RunTaskEntity runTaskEntity = new RunTaskEntity();
				runTaskEntity.setTaskId(taskExecute.getTaskId().toString());
				runTaskEntity.setSchedulingName(taskScheduling.getSchedulingName());
				runTaskEntity.setLoadPath(taskScheduling.getClientDriverPath());
				runTaskEntity.setTaskType(taskScheduling.getTaskType());
				try {
					HttpRequest.httpClientPost(url, selectedClient, JSONObject.toJSONString(runTaskEntity),3000);
				} catch (ConnectException e) {
					// 客户端连接失败，尝试故障转移
					log.error("测试任务执行，远程链接客户端 {} 出现异常，尝试故障转移...", selectedClient.getClientName());
					Client failoverClient = TaskAssignmentService.failoverTask(selectedClient.getClientIp(), taskScheduling, availableClients);
					if (failoverClient != null) {
						String failoverUrl= "http://"+failoverClient.getClientIp()+":"+ClientConstants.CLIENT_MONITOR_PORT+"/runTask";
						try {
							HttpRequest.httpClientPost(failoverUrl, failoverClient, JSONObject.toJSONString(runTaskEntity),3000);
						} catch (ConnectException ex) {
							log.error("故障转移失败，远程链接客户端 {} 也出现异常...", failoverClient.getClientName());
							taskExecute.setTaskStatus(4);
							taskExecuteService.updateTaskExecute(taskExecute);
						}
					} else {
						log.error("故障转移失败，没有可用的客户端...");
						taskExecute.setTaskStatus(4);
						taskExecuteService.updateTaskExecute(taskExecute);
					}
				}
			} else {
				log.error("没有可用的客户端执行任务...");
				taskExecute.setTaskStatus(4);
				taskExecuteService.updateTaskExecute(taskExecute);
			}
		}
    }
    
}
