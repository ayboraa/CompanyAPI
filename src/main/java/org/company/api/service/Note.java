package org.company.api.service;


import org.company.api.common.MemberId;
import org.company.api.common.NoteId;
import org.springframework.util.Assert;

public class Note {
    private NoteId noteId;
    private MemberId memberId;
    private String note;
    private MemberId author;

    public Note(MemberId memberId, String note, MemberId author) {
        Assert.notNull(memberId, "User cannot be null");
        Assert.notNull(note, "Note cannot be null");
        Assert.notNull(author, "Author cannot be null");

        this.noteId = new NoteId();
        this.memberId = memberId;
        this.note = note;
        this.author = author;

    }

    public NoteId getNoteId() {
        return noteId;
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
