package br.com.dmaiolli.billreminder.discord.listener;

import br.com.dmaiolli.billreminder.service.BillService;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandEnum;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MessageListener extends ListenerAdapter {

    @Autowired
    private BillService billService;

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
