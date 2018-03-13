package com.cts.test.ribbondemo;

import com.cts.test.ribbondemo.config.RibbonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@RibbonClient(name = "client-demo", configuration = RibbonConfiguration.class)
public class RibbonDemoApplication {

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
	public String greet(){

		return this.restTemplate.getForObject("http://client-demo/greetings", String.class);

	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonDemoApplication.class, args);
	}
}
