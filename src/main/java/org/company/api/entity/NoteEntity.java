package org.company.api.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class NoteEntity {
    @Id
    private UUID id;
    private UUID userId;
    private String note;
    private UUID authorId;

    public NoteEntity() {
    }

    public NoteEntity(UUID id, UUID userId, String note, UUID authorId) {
        this.id = id;
        this.userId = userId;
        this.note = note;
        this.authorId = authorId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public UUID getAuthorId() {
        return authorId;
    }
    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }





}
