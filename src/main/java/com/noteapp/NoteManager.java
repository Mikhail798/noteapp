package com.noteapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NoteManager {
    private static final File FILE = new File("data/notes.json");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void saveNotes(List<Note> notes) throws IOException {
        File parentDir = FILE.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        MAPPER.writeValue(FILE, notes);
    }

    public static List<Note> loadNotes() throws IOException {
        if (!FILE.exists()) {
            return new ArrayList<>();
        }

        CollectionType type = MAPPER.getTypeFactory()
                .constructCollectionType(List.class, Note.class);
        List<Note> notes = MAPPER.readValue(FILE, type);

        return notes;
    }

    public void list(List<Note> notes) {
        System.out.println("\nВаши заметки");

        List<Note> filteredNotes = notes.stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());

        for (Note note : filteredNotes) {
            System.out.println(note.getCreatedAt() + " | " + note.getText());
        }
    }

    public void add(List<Note> notes, Scanner console) {
        System.out.println("Введите заметку: ");

        String addNote = console.nextLine();
        LocalDateTime time = LocalDateTime.now();

        if (!addNote.isBlank() && !addNote.equals("exit")) {
            notes.add(new Note(addNote, time.toString()));

            System.out.println("Заметка успешно добавлена");
        }
    }

    public void remove(List<Note> notes, Scanner console) {
        System.out.println("Введите какую заметку хотите удалить: ");

        String removeNote = console.nextLine();

        notes.removeIf(a -> a.getText().equalsIgnoreCase(removeNote));

        try {
            NoteManager.saveNotes(notes);
        } catch (IOException e) {
            System.out.println("Не получилось удалить файл по причине: " + e.getMessage());
        }

        System.out.println("Элемент успешно удалён");
    }

    public void find(List<Note> notes, Scanner console) {
        System.out.println("Введите название заметки, которую хотите найти: ");

        String findWord = console.nextLine();

        notes.stream()
                .filter(n -> n.getText().equalsIgnoreCase(findWord))
                .forEach(n -> System.out.println("Найден: " + n.getCreatedAt() + " | " + n.getText()));
    }
}
