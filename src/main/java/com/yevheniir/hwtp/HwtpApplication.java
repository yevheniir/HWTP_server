package com.yevheniir.hwtp;

import com.yevheniir.hwtp.config.FileStorageProperties;
import com.yevheniir.hwtp.model.Stuff;
import com.yevheniir.hwtp.service.HwtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class HwtpApplication {

	@Autowired
	private HwtpService hwtpService;

	public static void main(String[] args) {
		SpringApplication.run(HwtpApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("hello world, I have just started up");
	}
}


