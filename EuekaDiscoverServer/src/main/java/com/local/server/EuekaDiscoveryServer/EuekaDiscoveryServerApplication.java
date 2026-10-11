package com.local.server.EuekaDiscoveryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EuekaDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EuekaDiscoveryServerApplication.class, args);
	}

}
