package br.com.dmaiolli.billreminder.strategy.command.impl;

import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandStrategy;
import org.springframework.stereotype.Component;

@Component
public class RegisterBillStrategyImpl implements DiscordCommandStrategy {

    @Override
    public String execute() {
        return null;
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.REGISTER_BILL;
    }
}
