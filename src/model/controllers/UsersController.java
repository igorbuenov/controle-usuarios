package model.controllers;

import model.entities.User;
import model.exception.DomainException;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static model.helpers.FilesHelpers.countFiles;
import static model.helpers.FilesHelpers.regexStringFormatting;

public class UsersController {
    private static final String DATABASE = "C:\\ws-intellij\\cadastro-usuarios\\src\\model\\database\\";

    public static void create(List<String> request) throws DomainException {

        try {
            //  VALIDATIONS RULES
            for (int i = 0; i < request.size(); i++) {
                if (request.get(i) == "") {
                    throw new DomainException("Preencha todos os campos");
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
            } else {
                List<User> usersDB = usersDB();
                if (usersDB.stream().anyMatch(user -> user.getEmail().equals(email))) {
                    throw new DomainException("Email já cadastrado!");
                }
            }

            if (age < 18) {
                throw new DomainException("Para ser cadastrado, o usuário deve ter 18 anos ou mais");
            }

            User user = new User(name, email, age, height);
            System.out.println(user);

            // Utilizando a contagem de arquivos para adicionar a numeração em um novo arquivo de usuário
            // Using file count to add numbering to a new file user

            int numUser = countFiles(DATABASE);
            String userFile = DATABASE + numUser + "-" + regexStringFormatting(user.getName()).toUpperCase() + ".txt";
            if (numUser < 10) {
                userFile = DATABASE + "0" + numUser + "-" + regexStringFormatting(user.getName()).toUpperCase() + ".txt";
            }

            try(BufferedWriter br = new BufferedWriter(new FileWriter(userFile, true))) {
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

    public static void read() {

        List<User> usersDB = usersDB();
        for (int i = 0; i < usersDB.size(); i++) {
            if (i < 9) {
                System.out.println("0" + (i+1) + " - " + usersDB.get(i).getName());
            } else {
                System.out.println((i+1) + " - " + usersDB.get(i).getName());
            }
        }

    }

    public static void show(String asLike) {

        List<User> usersDB = usersDB();
        usersDB.stream()
                .filter(user -> user.getName().toLowerCase().contains(asLike.toLowerCase()) ||
                        user.getEmail().toLowerCase().contains(asLike.toLowerCase()) ||
                        user.convertAgeToString().contains(asLike))
                .sorted(Comparator.comparing(User::getName))
                .forEach(user -> System.out.println(user.toString() +"\n"));
    }

    public static List<User> usersDB() {

        File folder = new File(DATABASE);
        File[] files = folder.listFiles(File::isFile);
        List<User> usersDB = new ArrayList<>();

        for (File file : files) {
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                List<String> atributes = new ArrayList<>();
                String line = br.readLine();
                while (line != null) {
                    atributes.add(line);
                    line = br.readLine();
                }
                usersDB.add(new User(atributes.get(0), atributes.get(1), Integer.parseInt(atributes.get(2)), Double.parseDouble(atributes.get(3).replace(",","."))));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return usersDB;
    }


}
