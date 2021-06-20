package co.com.narfco.meli.mutants.meli.mutants.kernel.exception;

import lombok.Getter;

@Getter
public class RepositoryException extends RuntimeException {

    private final String message;

    public RepositoryException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

}
