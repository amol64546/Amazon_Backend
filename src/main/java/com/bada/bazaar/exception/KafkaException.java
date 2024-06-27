package com.bada.bazaar.exception;


import static com.bada.bazaar.error.CommonErrors.KAFKA_ERROR;

public class KafkaException extends ApplicationException {

    public KafkaException(String errorMessage) {
        super(KAFKA_ERROR, errorMessage);
    }

    public KafkaException(String errorMessage, Throwable throwable) {
        super(KAFKA_ERROR, errorMessage, throwable);
    }
}
