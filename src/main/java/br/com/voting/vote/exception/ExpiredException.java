package br.com.voting.vote.exception;

public class ExpiredException extends RuntimeException {

    public ExpiredException(String message) {
        super(message);
    }
}
