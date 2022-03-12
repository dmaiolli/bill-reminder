package br.com.dmaiolli.billreminder.strategies.command;

import java.util.Arrays;

public enum DiscordCommandEnum {

    PING("!Ping", new PingStrategy()),
    PONG("!Pong", new PongStrategy());

    private String command;
    private DiscordCommandStrategy strategyClass;

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
        return Arrays
                .stream(values())
                .filter(value -> value.getCommand().equalsIgnoreCase(command))
                .findFirst()
                .orElse(null);
    }
}
