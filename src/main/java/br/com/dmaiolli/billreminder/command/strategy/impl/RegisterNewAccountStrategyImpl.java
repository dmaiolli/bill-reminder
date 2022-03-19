package br.com.dmaiolli.billreminder.command.strategy.impl;

import br.com.dmaiolli.billreminder.command.CommandSender;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandStrategy;
import br.com.dmaiolli.billreminder.model.UserAccount;
import br.com.dmaiolli.billreminder.service.UserAccountService;
import br.com.dmaiolli.billreminder.util.DateUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class RegisterNewAccountStrategyImpl implements DiscordCommandStrategy {

    private final UserAccountService userAccountService;

    public RegisterNewAccountStrategyImpl(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public void execute(CommandSender commandSender, List<String> args) {
        Long userId = commandSender.getJdaUser().getIdLong();

        boolean isUserAlreadyExists = userAccountService.accountAlreadyExistsById(userId);

        if(isUserAlreadyExists) {
            MessageEmbed messageEmbed = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setFooter("Essa conta já existe", null)
                    .build();

            commandSender.sendEmbedMessage(messageEmbed);
            return;
        }

        UserAccount userAccount = new UserAccount.Builder()
                .withId(userId)
                .withName(commandSender.getJdaUser().getName())
                .build();
        userAccountService.registerNewAccount(userAccount);

        MessageEmbed messageEmbed = new EmbedBuilder()
                .setColor(Color.GREEN)
                .setTitle("Registrado com sucesso")
                .addField("Username", userAccount.getName(), true)
                .addField("Identifier", userAccount.getId().toString(), true)
                .addField("Data de registro", DateUtil.dateFormatter(userAccount.getRegistrationDate()), true)
                .build();

        commandSender.sendEmbedMessage(messageEmbed);
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.REGISTER_ACCOUNT;
    }
}
