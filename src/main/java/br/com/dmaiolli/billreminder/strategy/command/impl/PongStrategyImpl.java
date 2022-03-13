package br.com.dmaiolli.billreminder.strategy.command.impl;

import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandStrategy;
import org.springframework.stereotype.Component;

@Component
public class PongStrategyImpl implements DiscordCommandStrategy {
    @Override
    public String execute() {
        return "Ping!!";
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.PONG;
    }
}
