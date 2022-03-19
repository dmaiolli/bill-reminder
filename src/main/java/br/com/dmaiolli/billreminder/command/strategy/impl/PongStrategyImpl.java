package br.com.dmaiolli.billreminder.command.strategy.impl;

import br.com.dmaiolli.billreminder.command.CommandSender;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PongStrategyImpl implements DiscordCommandStrategy {
    @Override
    public void execute(CommandSender commandSender, List<String> args) {
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.PONG;
    }
}
