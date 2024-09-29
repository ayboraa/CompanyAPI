package org.company.api.service.impl;

import org.company.api.common.MemberId;
import org.company.api.common.NoteId;
import org.company.api.entity.NoteEntity;
import org.company.api.service.Mapper;
import org.company.api.service.Note;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteMapper implements Mapper<Note, NoteEntity> {
    @Override
    public NoteEntity toEntity(Note note) {
        return new NoteEntity(note.getNoteId().uuid(), note.getUserId().uuid(), note.getNote(), note.getAuthorId().uuid());
    }

    @Override
    public Note toDTO(NoteEntity entity) {
        return new Note(new NoteId(entity.getId()), new MemberId(entity.getUserId()), entity.getNote(), new MemberId(entity.getAuthorId()));
    }

    @Override
    public List<Note> toDTOList(List<NoteEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }
}
