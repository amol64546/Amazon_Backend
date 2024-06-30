package com.bada.bazaar.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PhoneNumberSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Assuming phone numbers are 10 digits long and we want to mask all but the last 4 digits
        String masked = value.replaceAll("\\d(?=\\d{4})", "*");
        gen.writeString(masked);
    }
}
