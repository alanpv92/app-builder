package com.appbuilder.appbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppbuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppbuilderApplication.class, args);
	}

}


/*

check y createat and updatedAt comming null on project creation

why filter getting called

check the logic for project entity and project memeber

add logic to handle duplucate error on db like price id added 2 times

validate plans if it exit before sending
add customer id if coming second time


id in_1UAA6J3ACsPgWHKON4CwNLK9

 */