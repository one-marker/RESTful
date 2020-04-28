package ru.spring.marker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ru.spring.marker.rest.config.AppConfig.class);

	//	com.journaldev.spring.dao.PersonDAO personDAO = context.getBean(com.journaldev.spring.dao.PersonDAO.class);

		//System.out.println("List of person is:");

		//context.close();



	}


}
