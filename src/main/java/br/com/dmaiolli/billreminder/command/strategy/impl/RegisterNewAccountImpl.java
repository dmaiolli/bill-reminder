package br.com.dmaiolli.billreminder.command.strategy.impl;

import br.com.dmaiolli.billreminder.command.CommandSender;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandStrategy;
import br.com.dmaiolli.billreminder.model.UserAccount;
import br.com.dmaiolli.billreminder.service.UserAccountService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class RegisterNewAccountImpl implements DiscordCommandStrategy {

    private final UserAccountService userAccountService;

    public RegisterNewAccountImpl(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public void execute(CommandSender commandSender) {
        Long userId = commandSender.getJdaUser().getIdLong();

        boolean isUserAlreadyExists = userAccountService.accountAlreadyExistsById(userId);

        if(isUserAlreadyExists) {
            String footer = commandSender.getMessage("already-exists");

            MessageEmbed messageEmbed = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setFooter(footer, null)
                    .build();

            commandSender.sendEmbedMessage(messageEmbed);
            return;
        }

        UserAccount userAccount = new UserAccount.Builder()
                .withId(userId)
                .withName(commandSender.getJdaUser().getName())
                .build();
        userAccountService.registerNewAccount(userAccount);

        String title = commandSender.getMessage("successfully-registered");
        String nickname = commandSender.getMessage("nickname");
        String identifier = commandSender.getMessage("identifier");
        String date = commandSender.getMessage("date");

        MessageEmbed messageEmbed = new EmbedBuilder()
                .setColor(Color.GREEN)
                .setTitle(title)
                .addField(nickname, userAccount.getName(), true)
                .addField(identifier, userAccount.getId().toString(), true)
                .addField(date, userAccount.getRegistrationDate().toString(), true)
                .build();

        commandSender.sendEmbedMessage(messageEmbed);
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.REGISTER_ACCOUNT;
    }
}
