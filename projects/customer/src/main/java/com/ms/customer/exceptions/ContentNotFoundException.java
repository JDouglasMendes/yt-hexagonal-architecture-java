package com.ms.customer.exceptions;

    public class ContentNotFoundException extends RuntimeException {
        public ContentNotFoundException(Long id) {
            super(String.format("Content %s not found", id));
        }
}
