package br.com.dmaiolli.billreminder.strategies.command;

/**
 * Common strategy for all Discord commands
 */
public interface DiscordCommandStrategy {

    String messageToSend();

}
