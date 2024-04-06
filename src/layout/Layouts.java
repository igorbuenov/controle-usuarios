package layout;

import model.controllers.QuestionsController;
import model.controllers.UsersController;
import model.exception.DomainException;
import model.helpers.FilesHelpers;

import java.io.FileNotFoundException;
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

            if (choice == 2) {
                readUser();
            }

            if (choice == 3) {
                createQuestion(scan);
            }

            if (choice == 4) {
                deleteQuestion(scan);
            }

            if (choice == 5) {
                showUser(scan);
            }

        } catch (InputMismatchException e) {
            System.out.println("Dado de entrada inválido");
        } catch (DomainException | FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void createUser(Scanner scanner) throws DomainException {

        List<String> questions = FilesHelpers.fileReader(QUESTIONS);
        QuestionsController.read(questions);
        System.out.println();

        List<String> answers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            answers.add(scanner.nextLine());
        }
        System.out.println();

        UsersController.create(answers);
    }

    public static void readUser() throws FileNotFoundException {
        System.out.println("..:: USUÁRIOS CADASTRADOS ::..");
        UsersController.read();
    }

    public static void showUser(Scanner scanner) {

        System.out.print("Digite o dado a ser pesquisado: ");
        String data = scanner.nextLine();
        System.out.println();

        System.out.println("..:: USUÁRIOS ENCONTRADOS ::..");
        System.out.println();
        UsersController.show(data);
    }

    public static void createQuestion(Scanner scanner) {

        System.out.println("Digite a pergunta para ser salva no formulário: ");
        String question = scanner.nextLine();

        QuestionsController.create(question);

    }

    public static void deleteQuestion(Scanner scanner) {

        System.out.println("Digite a ser deletada do formulário: ");
        String question = scanner.nextLine();

        QuestionsController.delete(question);

    }



}
