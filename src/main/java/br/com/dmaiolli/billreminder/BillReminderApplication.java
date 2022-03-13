package br.com.dmaiolli.billreminder;

import br.com.dmaiolli.billreminder.discord.listener.MessageListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.com.dmaiolli" })
public class BillReminderApplication implements CommandLineRunner {

	private final MessageListener messageListener;

	public BillReminderApplication(MessageListener messageListener) {
		this.messageListener = messageListener;
	}

	public static void main(String[] args) {
		SpringApplication.run(BillReminderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Dotenv dotenv = Dotenv.load();
		JDABuilder jdaBuilder = JDABuilder.createDefault(dotenv.get("DISCORD_BOT_KEY"));

		// Disable parts of the cache
		jdaBuilder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
		// Enable the bulk delete event
		jdaBuilder.setBulkDeleteSplittingEnabled(false);
		// Disable compression (not recommended)
		jdaBuilder.setCompression(Compression.NONE);
		// Set activity (like "playing Something")
		jdaBuilder.setActivity(Activity.watching("TV"));

		JDA jda = jdaBuilder.build();
		jda.addEventListener(messageListener);
	}
}
