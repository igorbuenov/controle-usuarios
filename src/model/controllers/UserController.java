package model.controllers;

import model.entities.User;
import model.exception.DomainException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static model.helpers.FilesMethods.countFiles;

public class UserController {

    private static final String userDB = "C:\\ws-intellij\\cadastro-usuarios\\src\\model\\files\\usersDB\\";

    public static void create(List<String> request) throws DomainException {

        // TODO List:
        // Separar os dados em um array e fazer a verificação de requisitos antes de instanciar um novo objeto
        // Transformar uma lista em array para fazer a operação

        String[] data = request.toArray(new String[0]);
        String name = data[0];
        String email = data[1];
        Integer age = Integer.parseInt(data[2]);
        Double height = Double.parseDouble(data[3]);

        if (name.length() < 10) {
            throw new DomainException("O nome do usuário deve ter 10 ou mais caracteres!");
        }

        if (!email.matches("^[^@]+@[^@]+$")) {
            throw new DomainException("Email informado incorretamente");
        }

        if (age < 18) {
            throw new DomainException("Para ser cadastrado, o usuário deve ter 18 anos ou mais");
        }

        User user = new User(name, email, age, height);
        System.out.println(user);

        int usersInDB = countFiles(userDB);
        String userFile = userDB + usersInDB + "-" + user.getName().toUpperCase().replaceAll("\\s+", "") + ".txt";
        if (usersInDB < 10) {
            userFile = userDB + "0" + usersInDB + "-" + user.getName().toUpperCase().replaceAll("\\s+", "") + ".txt";
        }

        try(BufferedWriter br = new BufferedWriter(new FileWriter(userFile, true))) {

            for (String line : request) {
                br.write(line);
                br.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
