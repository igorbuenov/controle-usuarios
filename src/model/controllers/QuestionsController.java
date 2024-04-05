package model.controllers;

import java.io.*;
import java.util.List;

public class QuestionsController {

    public static final String QUESTIONS = "C:\\ws-intellij\\cadastro-usuarios\\src\\model\\files\\questions.txt";

    public static void create(String question) {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(QUESTIONS, true))) {
             bw.newLine();
             bw.write(question);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void read(List<String> questions) {
        for (int i = 0; i < questions.size(); i++) {
            if (i < 9) {
                System.out.println("0" + (i+1) + " - " + questions.get(i));
            }
        }
    }



}
