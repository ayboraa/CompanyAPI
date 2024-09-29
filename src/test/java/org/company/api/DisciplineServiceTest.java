package org.company.api;


import org.company.api.common.MemberId;
import org.company.api.service.DisciplineService;
import org.company.api.service.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest
public class DisciplineServiceTest {

    @Autowired
    private DisciplineService noteService;

    @Test
    public void testSingleCRUD() {

        // Test create.
        Note note = new Note(null, new MemberId(), "Sample note.", new MemberId());
        Note savedNote = noteService.saveNote(note);
        assertThat(savedNote).isNotNull();
        assertThat(savedNote.getNote()).isEqualTo("Sample note.");

        // Test read.
        Note foundNote = noteService.findNoteById(savedNote.getNoteId());
        assertThat(foundNote).isNotNull();
        assertThat(foundNote.getNote()).isEqualTo("Sample note.");

        // Test update.
        Note toUpdate = new Note(foundNote.getNoteId(), note.getUserId(), "Updated note.", note.getAuthorId());
        Note updatedNote = noteService.updateNote(savedNote.getNoteId(), toUpdate);
        assertThat(updatedNote).isNotNull();
        assertThat(updatedNote.getNote()).isEqualTo("Updated note.");
        // Ensure update is correct.
        updatedNote = noteService.findNoteById(updatedNote.getNoteId());
        assertThat(updatedNote).isNotNull();
        assertThat(updatedNote.getNote()).isEqualTo("Updated note.");

        // Test read.
        List<Note> noteList = noteService.getAllNotes();
        assertThat(noteList).isNotNull();
        assertThat(noteList.size()).isEqualTo(1);

        // Test delete.
        noteService.deleteNote(noteList.getFirst().getNoteId());
        noteList = noteService.getAllNotes();
        assertThat(noteList).isNotNull();
        assertThat(noteList.size()).isEqualTo(0);

    }
}
