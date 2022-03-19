package br.com.dmaiolli.billreminder.exception;

public class BillAlreadyPaidException extends Exception {

    public BillAlreadyPaidException() {
    }

    public BillAlreadyPaidException(String message) {
        super(message);
    }
}
