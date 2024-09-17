package org.company.api.common;

import org.springframework.util.Assert;

import java.util.UUID;

public record CommentId(UUID uuid) {

    public CommentId {

        Assert.notNull(uuid, "Id cannot be null");

    }

    public CommentId(){

        this(UUID.randomUUID());

    }


}
