package model.helpers;

import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class FilesHelpers {
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

    public static String regexStringFormatting(String string) {

        String formatedString = Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\s+", "") // Remove spaces in String
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "") // Remover acentuação
                .replaceAll("[áàâã]", "a")
                .replaceAll("[éèê]", "e")
                .replaceAll("[íìî]", "i")
                .replaceAll("[óòôõ]", "o")
                .replaceAll("[úùû]", "u")
                .replaceAll("[ÁÀÂÃ]", "A")
                .replaceAll("[ÉÈÊ]", "E")
                .replaceAll("[ÍÌÎ]", "I")
                .replaceAll("[ÓÒÔÕ]", "O")
                .replaceAll("[ÚÙÛ]", "U");

        return formatedString;
    }


}
