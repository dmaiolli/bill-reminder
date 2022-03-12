package br.com.dmaiolli.billreminder.strategy.command.impl;

import br.com.dmaiolli.billreminder.model.Bill;
import br.com.dmaiolli.billreminder.service.BillService;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAllBillsNotPaidStrategyImpl implements DiscordCommandStrategy {

    @Autowired
    private BillService billService;

    @Override
    public String messageToSend() {
        List<Bill> billsNotPaid = billService.findAllBillIsNotPaid();
        return billsNotPaid.toString();
    }
}
