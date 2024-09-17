package org.company.api.common;

import org.springframework.util.Assert;

import java.util.UUID;

public record TaskId(UUID uuid) {

    public TaskId {

        Assert.notNull(uuid, "Id cannot be null");

    }

    public TaskId(){

        this(UUID.randomUUID());

    }


}
