package br.com.dmaiolli.billreminder.strategy.command.impl;

import br.com.dmaiolli.billreminder.model.Bill;
import br.com.dmaiolli.billreminder.service.BillService;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAllBillsNotPaidStrategyImpl implements DiscordCommandStrategy {

    private final BillService billService;

    public ListAllBillsNotPaidStrategyImpl(BillService billService) {
        this.billService = billService;
    }

    @Override
    public String execute() {
        List<Bill> billsNotPaid = billService.findAllBillIsNotPaid();
        return billsNotPaid.toString();
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.LIST_ALL_NON_PAID;
    }
}
