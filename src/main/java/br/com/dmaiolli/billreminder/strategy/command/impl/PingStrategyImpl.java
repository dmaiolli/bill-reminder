package br.com.dmaiolli.billreminder.strategy.command.impl;

import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandStrategy;
import org.springframework.stereotype.Component;

@Component
public class PingStrategyImpl implements DiscordCommandStrategy {
    @Override
    public String execute() {
        return "Pong!!!";
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.PING;
    }
}
