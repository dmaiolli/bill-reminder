package br.com.dmaiolli.billreminder.command.strategy;

import br.com.dmaiolli.billreminder.exception.StrategyNotFoundException;

import java.util.Arrays;

public enum DiscordCommandEnum {
    PING("!Ping"),
    PONG("!Pong"),
    REGISTER_BILL("!registerBill"),
    LIST_ALL_NON_PAID("!nonpaid"),
    REGISTER_ACCOUNT("!register"),
    SET_BILL_PAID("!setPaid");

    private final String command;

    DiscordCommandEnum(String command) {
        this.command = command;
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
