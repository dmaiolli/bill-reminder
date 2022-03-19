package br.com.dmaiolli.billreminder.discord.listener;

import br.com.dmaiolli.billreminder.command.CommandSender;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandStrategy;
import br.com.dmaiolli.billreminder.command.strategy.factory.DiscordCommandFactory;
import br.com.dmaiolli.billreminder.component.MessageComponent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MessageListener extends ListenerAdapter {

    private static final String BOT_ID = "952226502697705514";

    private final DiscordCommandFactory discordCommandFactory;
    private final MessageComponent messageComponent;

    public MessageListener(DiscordCommandFactory discordCommandFactory, MessageComponent messageComponent) {
        this.discordCommandFactory = discordCommandFactory;
        this.messageComponent = messageComponent;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getAuthor().getId().equals(BOT_ID)) {
            return;
        }
        Message message = event.getMessage();

        DiscordCommandStrategy discordCommandStrategy = discordCommandFactory.
                getStrategyCommandFor(DiscordCommandEnum.findEnumByCommand(message.getContentDisplay()));

        CommandSender commandSender = new CommandSender(event.getChannel(), messageComponent, event.getAuthor());
        discordCommandStrategy.execute(commandSender);
    }

    public static void sendMessage(String message, TextChannel textChannel) {
        textChannel.sendMessage(message).queue();
    }
}
