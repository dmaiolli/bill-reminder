package br.com.dmaiolli.billreminder.command.strategy.impl;

import br.com.dmaiolli.billreminder.annotation.CommandBody;
import br.com.dmaiolli.billreminder.command.CommandSender;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandStrategy;
import br.com.dmaiolli.billreminder.exception.BillAlreadyPaidException;
import br.com.dmaiolli.billreminder.model.Bill;
import br.com.dmaiolli.billreminder.service.BillService;
import br.com.dmaiolli.billreminder.util.DateUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class SetBillPaidStrategyImpl implements DiscordCommandStrategy {

    private final BillService billService;

    public SetBillPaidStrategyImpl(BillService billService) {
        this.billService = billService;
    }

    @Override
    @CommandBody(value = "!setPaid", minArguments = 1, maxArguments = 1, usage = "<billId>")
    public void execute(CommandSender commandSender, List<String> args) {
        Bill bill;
        try{
            bill = billService.setBillPaid(Long.valueOf(args.get(0)));
        } catch (BillAlreadyPaidException e) {
            MessageEmbed messageEmbed = new EmbedBuilder()
                    .setColor(Color.YELLOW)
                    .setTitle("Conta já foi paga!")
                    .addField("ID", args.get(0), true)
                    .build();

            commandSender.sendEmbedMessage(messageEmbed);
            return;
        }

        MessageEmbed messageEmbed = new EmbedBuilder()
                .setColor(Color.GREEN)
                .setTitle("Conta paga!")
                .addField("ID", bill.getId().toString(), true)
                .addField("Tipo da conta", bill.getBillType().name(), true)
                .addField("Data de vencimento", DateUtil.dateFormatter(bill.getDueDate()), true)
                .build();

        commandSender.sendEmbedMessage(messageEmbed);
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.SET_BILL_PAID;
    }
}
