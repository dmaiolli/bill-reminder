package br.com.dmaiolli.billreminder.strategies.command;

public class PingStrategy implements DiscordCommandStrategy{
    @Override
    public String messageToSend() {
        return "Pong!!!";
    }
}
