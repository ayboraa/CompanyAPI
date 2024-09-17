package org.company.api.common;

import org.springframework.util.Assert;

import java.util.UUID;

public record NoteId(UUID uuid) {

    public NoteId {

        Assert.notNull(uuid, "Id cannot be null");

    }

    public NoteId(){

        this(UUID.randomUUID());

    }


}
