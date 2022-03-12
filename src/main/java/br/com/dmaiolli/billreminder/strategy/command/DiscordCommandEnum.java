package br.com.dmaiolli.billreminder.strategy.command;

import br.com.dmaiolli.billreminder.exception.StrategyNotFoundException;
import br.com.dmaiolli.billreminder.strategy.command.impl.ListAllBillsNotPaidStrategyImpl;
import br.com.dmaiolli.billreminder.strategy.command.impl.PingStrategyImpl;
import br.com.dmaiolli.billreminder.strategy.command.impl.PongStrategyImpl;
import br.com.dmaiolli.billreminder.strategy.command.impl.RegisterBillStrategyImpl;

import java.util.Arrays;

public enum DiscordCommandEnum {
    PING("!Ping", new PingStrategyImpl()),
    PONG("!Pong", new PongStrategyImpl()),
    REGISTER_BILL("!register", new RegisterBillStrategyImpl()),
    LIST_ALL_NON_PAID("!nonpaid", new ListAllBillsNotPaidStrategyImpl());

    private final String command;
    private final DiscordCommandStrategy strategyClass;

    DiscordCommandEnum(String command, DiscordCommandStrategy classe) {
        this.command = command;
        this.strategyClass = classe;
    }

    public DiscordCommandStrategy getStrategyClass() {
        return strategyClass;
    }

    public String getCommand() {
        return command;
    }

    public static DiscordCommandEnum findEnumByCommand(String command) {
        return Arrays.stream(values())
                .filter(value -> value.getCommand().equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(StrategyNotFoundException::new);
    }
}
