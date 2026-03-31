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
                    System.out.println("Введите название новой заметки: ");

                    String text = console.nextLine();

                    NoteManager.add(new Note(text, LocalDateTime.now().toString()));

                    System.out.println("Заметка успешно добавлена");
                    continue;
                case "del":
                    System.out.println("Введите айди заметки, которую хотите удалить: ");

                    NoteManager.delete(console);

                    System.out.println("Заметка успешно удалена");
                    continue;
                case "delAll":
                    NoteManager.deleteAll();

                    System.out.println("Заметки успешно удалены");
                    continue;
                case "upd":
                    System.out.println("Введите айди заметки имя, которой хотите обновить: ");

                    NoteManager.update(console);

                    System.out.println("Заметка обновлена");
                    continue;
            }
            if (choose.equals("exit")) break;
        } console.close();
    }
}
