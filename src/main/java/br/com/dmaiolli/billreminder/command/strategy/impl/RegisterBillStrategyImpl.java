package br.com.dmaiolli.billreminder.command.strategy.impl;

import br.com.dmaiolli.billreminder.annotation.CommandBody;
import br.com.dmaiolli.billreminder.command.CommandSender;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.command.strategy.DiscordCommandStrategy;
import br.com.dmaiolli.billreminder.model.Bill;
import br.com.dmaiolli.billreminder.model.BillType;
import br.com.dmaiolli.billreminder.model.Responsavel;
import br.com.dmaiolli.billreminder.service.BillService;
import br.com.dmaiolli.billreminder.util.DateUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.text.ParseException;
import java.util.List;

@Component
public class RegisterBillStrategyImpl implements DiscordCommandStrategy {

    private final BillService billService;

    public RegisterBillStrategyImpl(BillService billService) {
        this.billService = billService;
    }

    @Override
    @CommandBody(value = "!registerBill", minArguments = 4, maxArguments = 4, usage = "<BillType> <dd/MM/yyyy> <isRecurrent> <Responsible>")
    public void execute(CommandSender commandSender, List<String> args) {
        MessageEmbed messageEmbed;
        try {
            Bill bill = new Bill.Builder()
                    .withBillType(BillType.valueOf(args.get(0)))
                    .withDueDate(DateUtil.stringToDate(args.get(1)))
                    .withIsRecurrent(Boolean.valueOf(args.get(2)))
                    .withResponsible(Responsavel.valueOf(args.get(3)))
                    .withUser(commandSender.getUserAccount())
                    .build();

            billService.registerNewBill(bill);

            messageEmbed = new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTitle("Conta salva com sucesso")
                    .addField("Tipo de conta", bill.getBillType().name(), true)
                    .addField("ID", bill.getId().toString(), true)
                    .addField("Responsável", bill.getResponsible().name(), true)
                    .addField("Data de vencimento", DateUtil.dateFormatter(bill.getDueDate()), true)
                    .build();

        } catch (ParseException e) {
            messageEmbed = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTitle("Oops, Algo deu errado")
                    .addField("Data inválida", "Adicione a data no formato dd/MM/yyyy (17/12/2001)", true)
                    .build();
        }
        commandSender.sendEmbedMessage(messageEmbed);
    }

    @Override
    public DiscordCommandEnum commandType() {
        return DiscordCommandEnum.REGISTER_BILL;
    }
}
