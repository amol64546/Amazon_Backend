package com.bada.bazaar.exception;

import static com.bada.bazaar.error.CommonErrors.KAFKA_CONSUMPTION_ERROR;

public class KafkaConsumptionException extends ApplicationException {

    public KafkaConsumptionException(String errorMessage) {
        super(KAFKA_CONSUMPTION_ERROR, errorMessage);
    }

    public KafkaConsumptionException(String errorMessage, Throwable throwable) {
        super(KAFKA_CONSUMPTION_ERROR, errorMessage, throwable);
    }
}
