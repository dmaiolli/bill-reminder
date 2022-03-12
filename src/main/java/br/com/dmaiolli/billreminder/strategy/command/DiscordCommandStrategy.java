package br.com.dmaiolli.billreminder.strategies.command;

import org.springframework.stereotype.Component;

/**
 * Common strategy for all Discord commands
 */
@Component
public interface DiscordCommandStrategy {

    String messageToSend();

}
