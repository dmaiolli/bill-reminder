package br.com.dmaiolli.billreminder.command;

import br.com.dmaiolli.billreminder.component.MessageComponent;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class CommandSender {

    private final MessageChannel messageChannel;
    private final MessageComponent messageComponent;

    public CommandSender(MessageChannel messageChannel, MessageComponent messageComponent) {
        this.messageChannel = messageChannel;
        this.messageComponent = messageComponent;
    }

    public void sendMessage(String key, String... arguments) {
        this.messageChannel.sendMessage(this.messageComponent.get(key, arguments)).queue();
    }

    public void sendEmbedMessage(MessageEmbed messageEmbed) {
        this.messageChannel.sendMessageEmbeds(messageEmbed).queue();
    }
}
