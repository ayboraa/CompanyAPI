package org.company.api.service;


import jakarta.transaction.Transactional;
import org.company.api.common.NoteId;
import org.company.api.controller.exception.ResourceNotFoundException;
import org.company.api.entity.NoteEntity;
import org.company.api.repository.NoteRepository;
import org.company.api.service.impl.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {

    @Autowired
    private NoteRepository noteRepository;

    private final NoteMapper _mapper = new NoteMapper();

    public Note saveNote(Note note){
        NoteEntity entity = new NoteEntity(new NoteId().uuid(), note.getUserId().uuid(), note.getNote(), note.getAuthorId().uuid());
        noteRepository.save(entity);
        return _mapper.toDTO(entity);
    }


    public Note findNoteById(NoteId id) {
        Optional<NoteEntity> opt = noteRepository.findById(id.uuid());
        if(opt.isEmpty())
            throw new ResourceNotFoundException("Member with ID " + id.uuid() + " not found.");
        else
            return _mapper.toDTO(opt.get());
    }

    public void deleteNote(NoteId id) {

        if(!noteRepository.existsById(id.uuid())){
            throw new ResourceNotFoundException("Note with ID " + id.uuid() + " not found.");
        }

        noteRepository.deleteById(id.uuid());
    }

    public List<Note> getAllNotes() {
        List<NoteEntity> entities = noteRepository.findAll();
        return _mapper.toDTOList(entities);
    }


    @Transactional
    public Note updateNote(NoteId id, Note newNote) {
        return noteRepository.findById(id.uuid())
                .map(noteEntity -> {
                    noteEntity.setNote(newNote.getNote());
                    return _mapper.toDTO(noteRepository.save(noteEntity));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with ID: " + id.uuid()));
    }


}


