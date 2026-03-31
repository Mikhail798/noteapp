package com.noteapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NoteManager {
    private static String url = "jdbc:postgresql://localhost:5432/notesdb";
    private static String user = "postgres";
    private static String password = "mysecretpassword";

    public static List<Note> loadNotes() {
        List<Note> notes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, text, created_at FROM notes ORDER BY created_at")) {

            while (rs.next()) {
                String text = rs.getString("text");
                String createdAt = rs.getString("created_at");

                notes.add(new Note(text, createdAt));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }
}
