package org.ike.pms.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("org.ike.pms.api.mapper")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
