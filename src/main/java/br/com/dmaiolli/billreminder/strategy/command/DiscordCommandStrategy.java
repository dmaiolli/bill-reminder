package br.com.dmaiolli.billreminder.strategy.command;

/**
 * Common strategy for all Discord commands
 */
public interface DiscordCommandStrategy {

    String messageToSend();

}
