package org.company.api.common;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.util.Assert;

import java.util.UUID;

@JsonDeserialize(using = MemberIdDeserializer.class)
public record NoteId(UUID uuid) {

    public NoteId{
        Assert.notNull(uuid, "Id cannot be null");
    }

    public NoteId(){
        this(UUID.randomUUID());
    }


    @JsonValue
    public String value() {
        return uuid.toString();
    }


}
