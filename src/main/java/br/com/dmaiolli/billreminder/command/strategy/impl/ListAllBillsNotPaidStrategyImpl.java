package br.com.dmaiolli.billreminder.command.strategy.impl;

import br.com.dmaiolli.billreminder.command.CommandSender;
import br.com.dmaiolli.billreminder.model.Bill;
import br.com.dmaiolli.billreminder.service.BillService;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandStrategy;
import br.com.dmaiolli.billreminder.util.DateUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class ListAllBillsNotPaidStrategyImpl implements DiscordCommandStrategy {

    private final BillService billService;

    public ListAllBillsNotPaidStrategyImpl(BillService billService) {
        this.billService = billService;
    }

    @Override
    public void execute(CommandSender commandSender) {
        List<Bill> billsNotPaid = billService.findAllBillIsNotPaid();

        for(Bill bill : billsNotPaid) {
            MessageEmbed messageEmbed = this.buildEmbedMessage(bill);
            commandSender.sendEmbedMessage(messageEmbed);
        }
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.LIST_ALL_NON_PAID;
    }

    public MessageEmbed buildEmbedMessage(Bill billNotPaid) {
        return new EmbedBuilder()
                .setColor(Color.RED)
                .addField("Data de vencimento", DateUtil.dateFormatter(billNotPaid.getDueDate()), true)
                .addField("Tipo de conta", billNotPaid.getBillType().name(), true)
                .addField("Responsável", billNotPaid.getResponsible().name(), true)
                .addField("Menção", "<@522189090599206915>", true)
                .setTitle("CONTA PERTO DO VENCIMENTO")
                .build();
    }
}
