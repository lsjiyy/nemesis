package com.lsjyy.nemesis.employee;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.lsjyy.nemesis.common","com.lsjyy.nemesis.employee"})
@EnableEurekaClient
@EnableAutoConfiguration
@RestController
public class NemesisEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NemesisEmployeeApplication.class, args);
	}

	@GetMapping("/call")
	public AjaxResult employeeAnswer() {
		return AjaxResult.success();
	}
}
