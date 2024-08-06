package com.example.demo;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.MembershipDAO;
import com.example.demo.model.Account;
import com.example.demo.service.TrafficFortuneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO,
											   MembershipDAO membershipDAO,
											   TrafficFortuneService trafficFortuneService) {
		return runner -> {
//			demoBeforeAdvice(accountDAO, membershipDAO);
//			demoAfterReturningAdvice(accountDAO);
//			demoTheAfterThrowingAdvice(accountDAO);
//			demoAfterAdvice(accountDAO);
//			demoAroundAdvice(trafficFortuneService);
			demoAroundAdviceHandleException(trafficFortuneService);
		};
	}

	private void demoAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		log.info("Main program: demoAroundAdviceHandleException");
		log.info("Calling getFortune()");

		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		log.info("My fortune: {}", data);
		log.info("Finished!");
	}

	private void demoAroundAdvice(TrafficFortuneService trafficFortuneService) {
		log.info("Main program: demoAroundAdvice");
		log.info("Calling getFortune()");
		String data = trafficFortuneService.getFortune();
		log.info("My fortune: {}", data);
		log.info("Finished!");
	}

	private void demoAfterAdvice(AccountDAO accountDAO) {
		// Call method to find the accounts
		List<Account> accounts = null;

		try {

			// Add a boolean flag to simulate exceptions
			boolean tripWire = true;
			accounts = accountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			log.info("Main program ... caught exception: {}", e.toString());
		}

		// Display the accounts
		log.info("main Program: demoAfterThrowingAdvice");
		log.info("----");
		log.info("{}", accounts);
	}

	private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {

		// Call method to find the accounts
		List<Account> accounts = null;

		try {

			// Add a boolean flag to simulate exceptions
			boolean tripWire = true;
			accounts = accountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			log.info("Main program ... caught exception: {}", e.toString());
		}

		// Display the accounts
		log.info("main Program: demoAfterThrowingAdvice");
		log.info("----");
		log.info("{}", accounts);

	}

	private void demoAfterReturningAdvice(AccountDAO accountDAO) {

		// Call method to find the accounts
		List<Account> accounts = accountDAO.findAccounts();

		// Display the accounts
		log.info("Main Program: demoAfterReturningAdvice");
		log.info("----");
		log.info("{}", accounts);

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
