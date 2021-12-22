package io.cmartinez.productsapi.infra.exception.persistence;

/**
 * @author Carlos
 * @version 1.0
 */

public class NotFoundException extends RuntimeException {
    public NotFoundException(String s) {
        super(s);
    }
}
