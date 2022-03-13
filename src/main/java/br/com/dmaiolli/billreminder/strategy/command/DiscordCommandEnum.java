package br.com.dmaiolli.billreminder.strategy.command;

import br.com.dmaiolli.billreminder.exception.StrategyNotFoundException;

import java.util.Arrays;

public enum DiscordCommandEnum {
    PING("!Ping"),
    PONG("!Pong"),
    REGISTER_BILL("!register"),
    LIST_ALL_NON_PAID("!nonpaid");

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
