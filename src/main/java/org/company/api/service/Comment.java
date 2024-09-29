package org.company.api.service;

import jakarta.annotation.Nullable;
import org.company.api.common.CommentId;
import org.company.api.common.MemberId;
import org.springframework.util.Assert;

public class Comment {
    private CommentId id;
    private MemberId authorId;
    private String content;

    /// todo: Comment assigned to what?

    public Comment(@Nullable CommentId commentId, MemberId authorId, String content) {
        Assert.notNull(authorId, "authorId cannot be null");
        Assert.notNull(content, "content cannot be null");

        this.id = (commentId == null) ? new CommentId() : commentId;
        this.authorId = authorId;
        this.content = content;
    }


    public CommentId getId() {
        return id;
    }

    public MemberId getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }



}
