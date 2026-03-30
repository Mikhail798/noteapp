package com.noteapp;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        List<Note> notes;

        try {
            notes = NoteManager.loadNotes();
            System.out.println("Загружено " + notes.size() + " заметок");
        } catch (IOException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
            notes = new java.util.ArrayList<>();
        }

        while (true) {
            String choose = console.nextLine();
            switch (choose) {
                case "list":
                    new NoteManager().list(notes);
                    continue;
                case "add":
                    new NoteManager().add(notes, console);
                    try {
                        NoteManager.saveNotes(notes);
                    } catch (IOException e) {
                        System.out.println("Не удалось добавить заметку: " + e.getMessage());
                    }
                    continue;
                case "remove":
                    new NoteManager().remove(notes, console);
                    continue;
                case "find":
                    new NoteManager().find(notes, console);
                    continue;
            }
            if (choose.equals("exit")) break;
        } console.close();
    }
}
