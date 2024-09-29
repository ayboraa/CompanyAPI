package org.company.api.service;


import jakarta.annotation.Nullable;
import org.company.api.common.MemberId;
import org.company.api.common.NoteId;
import org.springframework.util.Assert;

public class Note {
    private NoteId id;
    private MemberId memberId;
    private String note;
    private MemberId author;

    // todo: last updated at?


    public Note(@Nullable NoteId noteId,  MemberId memberId, String note, MemberId author) {
        Assert.notNull(memberId, "User cannot be null");
        Assert.notNull(note, "Note cannot be null");
        Assert.notNull(author, "Author cannot be null");


        this.id = (noteId == null) ? new NoteId() : noteId;
        this.memberId = memberId;
        this.note = note;
        this.author = author;

    }

    public NoteId getNoteId() {
        return id;
    }

    public MemberId getUserId() {
        return memberId;
    }

    public String getNote() {
        return note;
    }

    public MemberId getAuthorId() {
        return author;
    }

}
