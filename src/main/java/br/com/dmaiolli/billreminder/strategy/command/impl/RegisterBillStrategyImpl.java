package br.com.dmaiolli.billreminder.strategy.command.impl;

import br.com.dmaiolli.billreminder.service.BillService;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandStrategy;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterBillStrategyImpl implements DiscordCommandStrategy {

    // TODO - Think some way to use constructor injection here
    @Autowired
    private BillService billService;

    @Override
    public String messageToSend() {
        return null;
    }
}
