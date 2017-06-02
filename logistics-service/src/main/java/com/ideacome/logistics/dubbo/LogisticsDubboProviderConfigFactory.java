package com.ideacome.logistics.dubbo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.ideacome.logistics.service.ConfigService;
import com.ideacome.logistics.service.LogisticsService;

@Configuration
public class LogisticsDubboProviderConfigFactory {

	@Autowired
    private LogisticsService logisticsService;
	
	@Autowired
	private ConfigService configService;
	
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName(configService.getString("logistics.protocol.name"));
		protocolConfig.setPort(Integer.parseInt(configService.getString("logistics.protocol.port")));
		return protocolConfig;
	}

	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress(configService.getString("logistics.registry.address"));
		registryConfig.setPort(Integer.parseInt(configService.getString("logistics.registry.port")));
		registryConfig.setProtocol(configService.getString("logistics.registry.protocol"));

		return registryConfig;
	}

	@Bean
	public MonitorConfig monitorConfig() {
		MonitorConfig monitorConfig = new MonitorConfig();
		monitorConfig.setAddress(configService.getString("logistics.monitor.address"));
		return monitorConfig;
	}

	@Bean
	public ApplicationConfig application() {
		ApplicationConfig application = new ApplicationConfig();
		application.setEnvironment(configService.getString("logistics.application.env"));
		application.setName(configService.getString("logistics.application.name"));
		if (StringUtils.isNotEmpty(configService.getString("logistics.monitor.isNeed"))
				&& configService.getString("logistics.monitor.isNeed").equals("need")) {
			application.setMonitor(monitorConfig());
		}
		return application;
	}

	@Bean
	public ServiceConfig<LogisticsService> logisticsServiceConfig(){
	    ServiceConfig<LogisticsService> logisticsServiceConfig = new ServiceConfig<LogisticsService>();
	    logisticsServiceConfig.setInterface(LogisticsService.class);
	    logisticsServiceConfig.setGroup(configService.getString("logistics.service.group"));
	    logisticsServiceConfig.setApplication(application());
	    logisticsServiceConfig.setProtocol(protocolConfig());
	    logisticsServiceConfig.setRegistry(registryConfig());
	    logisticsServiceConfig.setRef(logisticsService);
	    logisticsServiceConfig.export();
        return logisticsServiceConfig;
	}
}
