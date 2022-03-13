package br.com.dmaiolli.billreminder.strategy.command.factory;

import br.com.dmaiolli.billreminder.exception.StrategyNotFoundException;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandEnum;
import br.com.dmaiolli.billreminder.strategy.command.DiscordCommandStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DiscordCommandFactory {

    private static final Map<DiscordCommandEnum, DiscordCommandStrategy> discordCommandEnumDiscordCommandStrategyMap = new HashMap<>();

    private final List<DiscordCommandStrategy> commandStrategies;

    public DiscordCommandFactory(List<DiscordCommandStrategy> commandStrategies) {
        this.commandStrategies = commandStrategies;
    }

    @PostConstruct
    public void initFactoryCache() {
        for(DiscordCommandStrategy commandStrategy : commandStrategies) {
            discordCommandEnumDiscordCommandStrategyMap.put(commandStrategy.commandType(), commandStrategy);
        }
    }

    public DiscordCommandStrategy getStrategyCommandFor(DiscordCommandEnum commandEnum) {
        if(!discordCommandEnumDiscordCommandStrategyMap.containsKey(commandEnum)) {
            throw new StrategyNotFoundException("Unknown strategy for " + commandEnum.getCommand() + " command");
        }

        return discordCommandEnumDiscordCommandStrategyMap.get(commandEnum);
    }
}
