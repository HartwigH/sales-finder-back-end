package com.sf;

import com.sf.config.SalesFinderCoreContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(value = {SalesFinderCoreContext.class})
public class SfApplication {

	public static void main(String[] args) {
		 SpringApplication.run(SfApplication.class, args);
	}

}
