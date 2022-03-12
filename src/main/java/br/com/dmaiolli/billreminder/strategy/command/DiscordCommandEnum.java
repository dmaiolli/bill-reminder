package br.com.dmaiolli.billreminder.strategies.command;

import br.com.dmaiolli.billreminder.exception.StrategyNotFoundException;

import java.util.Arrays;

public enum DiscordCommandEnum {

    PING("!Ping", new PingStrategy()),
    PONG("!Pong", new PongStrategy()),
    REGISTER_BILL("!register", new RegisterBillStrategy());

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
