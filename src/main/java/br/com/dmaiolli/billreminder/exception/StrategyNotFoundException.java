package br.com.dmaiolli.billreminder.exception;

public class StrategyNotFoundException extends RuntimeException {
    public StrategyNotFoundException() {
    }

    public StrategyNotFoundException(String message) {
        super(message);
    }
}
