package org.company.api.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.UUID;

public class NoteIdDeserializer extends JsonDeserializer<NoteId> {
    @Override
    public NoteId deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String uuidString = jsonParser.getText();
        return new NoteId(UUID.fromString(uuidString));
    }
}