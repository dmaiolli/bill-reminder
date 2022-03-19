package br.com.dmaiolli.billreminder.command;

import br.com.dmaiolli.billreminder.component.MessageComponent;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

public class CommandSender {

    private final MessageChannel messageChannel;
    private final MessageComponent messageComponent;
    private final User userAccount;

    public CommandSender(MessageChannel messageChannel,
                         MessageComponent messageComponent,
                         User userAccount) {
        this.messageChannel = messageChannel;
        this.messageComponent = messageComponent;
        this.userAccount = userAccount;
    }

    public void sendMessage(String key, String... arguments) {
        this.messageChannel.sendMessage(this.messageComponent.get(key, arguments)).queue();
    }

    public void sendEmbedMessage(MessageEmbed messageEmbed) {
        this.messageChannel.sendMessageEmbeds(messageEmbed).queue();
    }

    public String getMessage(String key, String... arguments) {
        return this.messageComponent.get(key, arguments);
    }

    public User getJdaUser() {
        return this.userAccount;
    }
}
