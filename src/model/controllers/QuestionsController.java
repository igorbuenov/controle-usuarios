package model.controllers;

import model.exception.DomainException;
import model.helpers.FilesHelpers;

import java.io.*;
import java.util.List;

public class QuestionsController {
    public static final String QUESTIONS = "C:\\ws-intellij\\cadastro-usuarios\\src\\model\\files\\questions.txt";

    public void create(String question) {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(QUESTIONS, true))) {
             bw.newLine();
             bw.write(question);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void read(List<String> questions) {
        for (int i = 0; i < questions.size(); i++) {
            if (i < 9) {
                System.out.println("0" + (i+1) + " - " + questions.get(i));
            } else {
                System.out.println((i+1) + " - " + questions.get(i));
            }
        }
    }

    public void delete(int numQuestion) {
        try {
            List<String> questions = FilesHelpers.fileReader(QUESTIONS);

            if (numQuestion <= 4) {
               throw new DomainException("A questão é obrigatória e não pode ser deletada");
            }

            questions.remove(numQuestion-1);

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(QUESTIONS))) {
                for (String quest : questions) {
                    bw.write(quest);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Pergunta deletada com sucesso!");

        } catch (DomainException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
