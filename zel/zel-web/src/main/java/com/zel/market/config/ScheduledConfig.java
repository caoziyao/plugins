package com.zel.market.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/10
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {
    @Autowired
    private ApplicationContext context;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
    }
}
