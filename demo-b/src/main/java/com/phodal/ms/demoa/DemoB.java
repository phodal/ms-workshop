package com.phodal.ms.demoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoB {
    public static void main(String[] args) {
        SpringApplication.run(DemoB.class, args);
    }
}

@RestController
class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate;

    @RequestMapping("/name")
    public String name(){
        String name = restTemplate.getForObject("http://demo-a/name", String.class);
        return "A & " + name;
    }
}