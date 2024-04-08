package model.controllers;

import model.entities.User;
import model.exception.DomainException;
import model.helpers.FilesHelpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static model.helpers.FilesHelpers.countFiles;

public class UsersController {

    private static final String DATABASE = "C:\\ws-intellij\\cadastro-usuarios\\src\\model\\database\\";

    public static void create(List<String> request) throws DomainException {

        String[] data = request.toArray(new String[0]);
        String name = data[0];
        String email = data[1];
        Integer age = Integer.parseInt(data[2]);
        Double height = Double.parseDouble(data[3]);

        //  VALIDATIONS RULES
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

        // Utilizando a contagem de arquivos para adicionar a numeração em um novo arquivo de usuário
        // Using file count to add numbering to a new file user

        int usersInDB = countFiles(DATABASE);
        String userFile = DATABASE + usersInDB + "-" + user.getName().replace("ç", "c").toUpperCase().replaceAll("\\s+", "").replaceAll("[^\\p{ASCII}]", "") + ".txt";
        if (usersInDB < 10) {
            userFile = DATABASE + "0" + usersInDB + "-" + user.getName().toUpperCase().replaceAll("\\s+", "").replaceAll("[^\\p{ASCII}]", "") + ".txt";
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

    public static void read() throws FileNotFoundException {

        File folder = new File(DATABASE);
        File[] files = folder.listFiles(File::isFile);
        List<String> userNames = new ArrayList<>();

        for (File file : files) {
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line = bufferedReader.readLine();
                userNames.add(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < userNames.size(); i++) {
            if (i < 9) {
                System.out.println("0" + (i+1) + " - " + userNames.get(i));
            } else {
                System.out.println((i+1) + " - " + userNames.get(i));
            }
        }

    }

    public static void show(String asLike) {
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
                usersDB.add(new User(atributes.get(0), atributes.get(1), Integer.parseInt(atributes.get(2)), Double.parseDouble(atributes.get(3))));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        usersDB.stream()
                .filter(user -> user.getName().toLowerCase().contains(asLike.toLowerCase()) ||
                        user.getEmail().toLowerCase().contains(asLike.toLowerCase()) ||
                        user.convertAgeToString().contains(asLike))
                .forEach(user -> System.out.println(user.toString() +"\n"));
    }


}
