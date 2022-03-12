package br.com.dmaiolli.billreminder.strategy.command.impl;

import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandStrategy;

public class PingStrategyImpl implements DiscordCommandStrategy {
    @Override
    public String messageToSend() {
        return "Pong!!!";
    }
}
