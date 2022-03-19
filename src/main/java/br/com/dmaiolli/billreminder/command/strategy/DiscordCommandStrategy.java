package br.com.dmaiolli.billreminder.command.strategy;

import br.com.dmaiolli.billreminder.command.CommandSender;

import java.util.List;

/**
 * Common strategy for all Discord commands
 */
public interface DiscordCommandStrategy {

    void execute(CommandSender commandSender, List<String> args);

    DiscordCommandEnum commandType();

}
