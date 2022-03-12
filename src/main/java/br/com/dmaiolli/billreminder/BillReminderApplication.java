package br.com.dmaiolli.billreminder;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.LoginException;

//@SpringBootApplication
public class BillReminderApplication {

	public static void main(String[] args) throws LoginException {
//		SpringApplication.run(BillReminderA"pplication.class, args);
		JDA jda = JDABuilder.createDefault("OTUyMjI2NTAyNjk3NzA1NTE0.Yiy8CQ.uE8ogrpSmjYMo99NDHSnWWgicac").build();

	}

}
