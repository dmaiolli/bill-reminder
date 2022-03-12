package br.com.dmaiolli.billreminder.strategies.command;

public class PongStrategy implements DiscordCommandStrategy{
    @Override
    public String messageToSend() {
        return "Ping";
    }
}
