package br.com.ruderson.portfolio_api.services.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg) {
        super(msg);
    }
}