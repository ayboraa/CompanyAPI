package org.company.api.service;

import org.company.api.common.CommentId;
import org.company.api.common.MemberId;
import org.springframework.util.Assert;

public class Comment {
    private CommentId id;
    private MemberId authorId;
    private String content;

    public Comment(MemberId authorId, String content) {
        Assert.notNull(authorId, "authorId cannot be null");
        Assert.notNull(content, "content cannot be null");

        this.id = new CommentId();
        this.authorId = authorId;
        this.content = content;
    }


    public CommentId getId() {
        return id;
    }
    public void setId(CommentId id) {
        this.id = id;
    }

    public MemberId getAuthorId() {
        return authorId;
    }

    public void setAuthorId(MemberId authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }



}
