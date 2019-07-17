package com.enterprise.NX01.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan("com.enterprise.NX01.models")
@EnableAsync
public class EnterpriseConfig {
}
