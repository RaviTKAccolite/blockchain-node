package com.accolitedigital.blockchain.node;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableAutoConfiguration
public class BlockchainNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockchainNodeApplication.class, args);
	}

}
