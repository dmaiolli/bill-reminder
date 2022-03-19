package br.com.dmaiolli.billreminder.command.strategy;

import br.com.dmaiolli.billreminder.command.CommandSender;

/**
 * Common strategy for all Discord commands
 */
public interface DiscordCommandStrategy {

    void execute(CommandSender commandSender);

    DiscordCommandEnum commandType();

}
