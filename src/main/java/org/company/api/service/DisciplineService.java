package org.company.api.service;


import org.company.api.common.NoteId;
import org.company.api.entity.NoteEntity;
import org.company.api.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DisciplineService {

    @Autowired
    private NoteRepository noteRepository;


    public NoteEntity saveNote(Note note){
        NoteEntity entity = new NoteEntity(note.getNoteId().uuid(), note.getUserId().uuid(), note.getNote(), note.getAuthorId().uuid());
        noteRepository.save(entity);
        return entity;
    }


    public Optional<NoteEntity> findNoteById(NoteId id) {
        return noteRepository.findById(id.uuid());
    }

}
