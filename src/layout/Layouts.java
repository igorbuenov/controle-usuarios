package layout;

import model.helpers.Helpers;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Layouts {
    public static final String QUESTIONS = "C:\\ws-intellij\\cadastro-usuarios\\src\\model\\files\\questions.txt";
    public static void index() {

        Scanner scan = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("..:: CONTROLE DE USUÁRIOS ::..");

        try {
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar todos os usuários cadastrados");
            System.out.println("3 - Cadastrar nova pergunta no formulário");
            System.out.println("4 - Deletar pergunta do formulário");
            System.out.println("5 - Pesquisar usuário por nome, idade ou email");
            System.out.println();
            System.out.print("Opção escolhida: ");
            int choice = scan.nextInt();
            System.out.println();

            if (choice == 1) {
                createUser();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input data");
        }




    }

    public static void createUser() {

        List<String> questions = Helpers.fileReader(QUESTIONS);

        for (String question : questions) {
            System.out.println(question);
        }



    }



}
