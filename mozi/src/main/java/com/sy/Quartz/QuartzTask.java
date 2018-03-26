package com.sy.Quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 对设备进行收费定时器
 * @author Administrator
 *
 */
@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class QuartzTask extends QuartzJobBean {
	
	
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		   
		    System.out.println("定时器 =======1====="+new Date());
	}
}
