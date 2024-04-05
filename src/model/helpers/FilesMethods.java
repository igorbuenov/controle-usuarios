package model.helpers;

import model.entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesMethods {
    public static List<String> fileReader(String path) {

        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            while (line != null) {
                list.add(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

    public static int countFiles(String path) {

        File folder = new File(path);
        File[] files = folder.listFiles(File::isFile);
        int cont = 1;

        for (File file : files) {
            if (file != null) {
                cont++;
            }
        }

        return cont;
    }


}
