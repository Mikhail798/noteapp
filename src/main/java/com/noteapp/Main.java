package com.noteapp;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        List<Note> notes;

        notes = NoteManager.loadNotes();
        System.out.println("Загружено " + notes.size() + " заметок");

        while (true) {
            String choose = console.nextLine();
            switch (choose) {
                case "ls":
                    NoteManager.printNotes(notes);
                    continue;
                case "add":
                    String text = console.nextLine();

                    NoteManager.add(new Note(text, LocalDateTime.now().toString()));
                    continue;
            }
            if (choose.equals("exit")) break;
        } console.close();
    }
}
