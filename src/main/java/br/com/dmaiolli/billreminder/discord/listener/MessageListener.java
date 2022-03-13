package br.com.dmaiolli.billreminder.discord.listener;

import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandStrategy;
import br.com.dmaiolli.billreminder.strategy.command.factory.DiscordCommandFactory;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MessageListener extends ListenerAdapter {

    private static final String BOT_ID = "952226502697705514";

    private final DiscordCommandFactory discordCommandFactory;

    public MessageListener(DiscordCommandFactory discordCommandFactory) {
        this.discordCommandFactory = discordCommandFactory;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getAuthor().getId().equals(BOT_ID)) {
            return;
        }
        Message message = event.getMessage();
        String messageToSend = "";

        DiscordCommandStrategy discordCommandStrategy = discordCommandFactory.
                getStrategyCommandFor(DiscordCommandEnum.findEnumByCommand(message.getContentDisplay()));

        messageToSend = discordCommandStrategy.execute();
        MessageChannel messageChannel = event.getChannel();
        messageChannel.sendMessage(messageToSend).queue();
    }

    public static void sendMessage(String message, TextChannel textChannel) {
        textChannel.sendMessage(message).queue();
    }
}
