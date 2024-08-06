package com.example.demo;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.MembershipDAO;
import com.example.demo.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		return runner -> {
			demoBeforeAdvice(accountDAO, membershipDAO);
		};
	}

	private void demoBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

		// We expect the advices will have the following order:

			// 1) MyCloudLogAspect
			// 2) DemoLoggingAspect
			// 3) MyApiAnalyticsAspect

		// In DemoLoggingAspect.beforeAddAccountAdvice advice
		// We'll access and print the params of JoinPoint

		// Call the account business method
		Account account = new Account();
		account.setName("John");
		account.setLevel("platinum");

		accountDAO.addAccount(account, true);
		accountDAO.doWork();

		// Call the accountDao getter/setter methods
		accountDAO.setName("foobar");
		accountDAO.setServiceCode("silver");

		String name = accountDAO.getName();
		String code = accountDAO.getServiceCode();

		// Call the membership business method
		membershipDAO.addMember();
		membershipDAO.goToSleep();

	}

}
