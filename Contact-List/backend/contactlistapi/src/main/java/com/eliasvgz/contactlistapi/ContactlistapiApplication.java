package com.eliasvgz.contactlistapi;

import com.eliasvgz.contactlistapi.entity.Contact;
import com.eliasvgz.contactlistapi.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ContactlistapiApplication {



	public static void main(String[] args) {
		SpringApplication.run(ContactlistapiApplication.class, args);


	}

	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
