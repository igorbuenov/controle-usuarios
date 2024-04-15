package layout;

import model.controllers.QuestionsController;
import model.controllers.UsersController;
import model.exception.DomainException;
import model.helpers.FilesHelpers;

import java.io.FileNotFoundException;
import java.util.*;

public class Layouts {

    private static UsersController usersController = new UsersController();
    private static QuestionsController questionsController = new QuestionsController();

    private static Scanner scan = new Scanner(System.in);
    public static void index() {
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

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    readUser();
                    break;
                case 3:
                    createQuestion();
                    break;
                case 4:
                    deleteQuestion();
                    break;
                case 5:
                    showUser();
                    break;
                default:
                    System.out.println("Escolha inválida!");
                    finishProgram();
            }

        } catch (InputMismatchException e) {
            System.out.println("Dado de entrada inválido");
        } catch (DomainException | FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void createUser() throws DomainException {

        List<String> questions = FilesHelpers.fileReader(QuestionsController.QUESTIONS);
        questionsController.read(questions);
        System.out.println();

        List<String> answers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            answers.add(scan.nextLine());
        }
        System.out.println();

        usersController.create(answers);
        System.out.println();
        finishProgram();
    }

    public static void readUser() throws FileNotFoundException {

        System.out.println("..:: USUÁRIOS CADASTRADOS ::..");
        System.out.println();
        usersController.read();
        System.out.println();
        finishProgram();
    }

    public static void showUser() {

        System.out.print("Digite o dado a ser pesquisado: ");
        String data = scan.nextLine();
        System.out.println();
        System.out.println("..:: USUÁRIOS ENCONTRADOS ::..");
        usersController.show(data);
        System.out.println();
        finishProgram();
    }

    public static void createQuestion() {

        System.out.println("Digite a pergunta para ser salva no formulário: ");
        String question = scan.nextLine();

        questionsController.create(question);
        System.out.println();
        finishProgram();

    }

    public static void deleteQuestion() {

        System.out.println("..:: PERGUNTAS CADASTRADAS ::..");
        List<String> questions = FilesHelpers.fileReader(QuestionsController.QUESTIONS);
        questionsController.read(questions);
        System.out.println();

        System.out.println("Digite o número da pergunta a ser deletada do formulário: ");
        int numQuestion = scan.nextInt();
        scan.nextLine();
        questionsController.delete(numQuestion);
        System.out.println();
        finishProgram();

    }

    public static void finishProgram() {
        System.out.print("Deseja encerrar o programa (s/n)? ");
        String option = scan.nextLine();

        if (option.equalsIgnoreCase("n")) {
            index();
        }
    }


}
