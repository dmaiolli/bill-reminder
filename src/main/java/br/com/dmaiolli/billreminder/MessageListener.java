package br.com.dmaiolli.billreminder;

import br.com.dmaiolli.billreminder.strategies.command.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.strategies.command.DiscordCommandStrategy;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Objects;

public class MessageListener extends ListenerAdapter {

    private DiscordCommandStrategy discordCommandStrategy;

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder jdaBuilder = JDABuilder.createDefault("OTUyMjI2NTAyNjk3NzA1NTE0.Yiy8CQ.uE8ogrpSmjYMo99NDHSnWWgicac");

        // Disable parts of the cache
        jdaBuilder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        jdaBuilder.setBulkDeleteSplittingEnabled(false);
        // Disable compression (not recommended)
        jdaBuilder.setCompression(Compression.NONE);
        // Set activity (like "playing Something")
        jdaBuilder.setActivity(Activity.watching("TV"));

        JDA jda = jdaBuilder.build();
//        jda.addEventListener(new MessageListener());
        jda.awaitReady();

        List<TextChannel> channels = jda.getTextChannelsByName("a", true);
        for(TextChannel channel : channels) {
            sendMessage("Oi", channel);
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        String messageToSend;
        DiscordCommandEnum commandStrategyValue = DiscordCommandEnum.findEnumByCommand(message.getContentDisplay());

        if(Objects.isNull(commandStrategyValue)) {
            messageToSend = "Comando não encontrado";
        } else {
            messageToSend = commandStrategyValue.getStrategyClass().messageToSend();
        }

        MessageChannel messageChannel = event.getChannel();
        messageChannel.sendMessage(messageToSend).queue();
    }

    public static void sendMessage(String message, TextChannel textChannel) {
        textChannel.sendMessage(message).queue();
    }
}
