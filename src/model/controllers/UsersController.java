package model.controllers;

import model.entities.User;
import model.exception.DomainException;
import model.helpers.FilesHelpers;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static model.helpers.FilesHelpers.regexStringFormatting;

public class UsersController {
    private static final String DATABASE = "C:\\ws-intellij\\cadastro-usuarios\\src\\model\\database\\";

    public void create(List<String> request) throws DomainException {

        try {
            //  VALIDATIONS RULES
            for (int i = 0; i < 4; i++) {
                if (request.get(i) == "") {
                    throw new DomainException("Campo obrigatório!");
                }
            }

            String name = request.get(0);
            String email = request.get(1);
            Integer age = Integer.parseInt(request.get(2));
            Double height = Double.parseDouble(request.get(3).replace(",","."));

            if (name.length() < 10) {
                throw new DomainException("O nome do usuário deve ter 10 ou mais caracteres!");
            }

            if (!email.matches("^[^@]+@[^@]+$")) {
                throw new DomainException("Email informado incorretamente");
            } else if (usersDB().stream().anyMatch(user -> user.getEmail().equals(email))) {
                    throw new DomainException("Email já cadastrado!");
            }

            if (age < 18) {
                throw new DomainException("Para ser cadastrado, o usuário deve ter 18 anos ou mais");
            }

            User user = new User(name, email, age, height);
            System.out.println(user);

            String userNameFile = DATABASE + (usersDB().size() + 1) + "-" + regexStringFormatting(user.getName()).toUpperCase() + ".txt";
            if (usersDB().size() < 10) {
                userNameFile = DATABASE + "0" + (usersDB().size() + 1) + "-" + regexStringFormatting(user.getName()).toUpperCase() + ".txt";
            }

            try(BufferedWriter br = new BufferedWriter(new FileWriter(userNameFile))) {
                for (String line : request) {
                    br.write(line);
                    br.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }

    }

    public void read() {

        if (!usersDB().isEmpty()) {
            for (int i = 0; i < usersDB().size(); i++) {
                if (i < 9) {
                    System.out.println("0" + (i+1) + " - " + usersDB().get(i).getName());
                } else {
                    System.out.println((i+1) + " - " + usersDB().get(i).getName());
                }
            }
        } else {
            System.out.print("Não há usuários cadastrados!");
        }
    }

    public void show(String asLike) {

        if (!usersDB().isEmpty()) {
            usersDB().stream()
                    .filter(user -> user.getName().toLowerCase().contains(asLike.toLowerCase()) ||
                            user.getEmail().toLowerCase().contains(asLike.toLowerCase()) ||
                            user.convertAgeToString().contains(asLike))
                    .sorted(Comparator.comparing(User::getName))
                    .forEach(user -> System.out.println(user.toString()));
        } else {
            System.out.println("Nenhum usuário foi encontrado com o dado informado!");
        }

    }

    public List<User> usersDB() {

        File folder = new File(DATABASE);
        File[] files = folder.listFiles(File::isFile);
        List<User> usersDB = new ArrayList<>();

        for (File file : files) {

            List<String> atributes = FilesHelpers.fileReader(file.getPath());

            //Add to User additional informations
            if (atributes.size() > 4) {
                List<String> additionalInformations = new ArrayList<>();
                for (int i = 4; i < atributes.size(); i++) {
                    additionalInformations.add(atributes.get(i));
                }
                usersDB.add(new User(atributes.get(0), atributes.get(1), Integer.parseInt(atributes.get(2)), Double.parseDouble(atributes.get(3).replace(",",".")), additionalInformations));
            } else {
                usersDB.add(new User(atributes.get(0), atributes.get(1), Integer.parseInt(atributes.get(2)), Double.parseDouble(atributes.get(3).replace(",","."))));
            }
        }

        return usersDB;
    }


}
