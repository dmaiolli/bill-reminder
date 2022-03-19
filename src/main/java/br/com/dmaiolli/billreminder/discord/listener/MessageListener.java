package br.com.dmaiolli.billreminder.discord.listener;

import br.com.dmaiolli.billreminder.annotation.CommandBody;
import br.com.dmaiolli.billreminder.command.CommandSender;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandStrategy;
import br.com.dmaiolli.billreminder.command.strategy.factory.DiscordCommandFactory;
import br.com.dmaiolli.billreminder.component.MessageComponent;
import br.com.dmaiolli.billreminder.util.ArrayUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        List<String> args = Arrays.stream(message.getContentRaw().split(" ")).collect(Collectors.toList());

        DiscordCommandStrategy discordCommandStrategy = discordCommandFactory.
                getStrategyCommandFor(DiscordCommandEnum.findEnumByCommand(ArrayUtil.removeAndReturnArrayElement(args, 0)));

        CommandSender commandSender = new CommandSender(event.getChannel(), messageComponent, event.getAuthor());

        for(Method method : discordCommandStrategy.getClass().getDeclaredMethods()) {
            if(method.isAnnotationPresent(CommandBody.class)) {
                CommandBody commandBody = method.getAnnotation(CommandBody.class);

                if(args.size() < commandBody.minArguments() || args.size() > commandBody.maxArguments()) {
                    String usage = commandBody.value() + " " + commandBody.usage();
                    MessageEmbed messageEmbed = new EmbedBuilder()
                            .setColor(Color.RED)
                            .addField("Argumentos inválidos", usage, true)
                            .build();

                    commandSender.sendEmbedMessage(messageEmbed);
                    return;
                }
            }
        }

        discordCommandStrategy.execute(commandSender, args);
    }
}
