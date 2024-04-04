package layout;

import model.controllers.UserController;
import model.helpers.Helpers;

import java.util.*;

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
            scan.nextLine();
            System.out.println();

            if (choice == 1) {
                createUser(scan);
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input data");
        }




    }

    public static void createUser(Scanner scanner) {

        List<String> questions = Helpers.fileReader(QUESTIONS);
        for (String question : questions) {
            System.out.println(question);
        }
        System.out.println();

        List<String> answers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            answers.add(scanner.nextLine());
        }
        System.out.println();

        UserController.create(answers);
    }



}
