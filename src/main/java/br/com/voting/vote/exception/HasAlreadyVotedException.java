package br.com.voting.vote.exception;

public class HasAlreadyVotedException extends RuntimeException {

    public HasAlreadyVotedException(String message) {
        super(message);
    }
}
