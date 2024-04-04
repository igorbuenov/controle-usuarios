package model.controllers;

import model.entities.User;
import model.exception.DomainException;

import java.util.List;

public class UserController {

    public static void create(List<String> request) throws DomainException {

        // TODO List:
        // Separar os dados em um array e fazer a verificação de requisitos antes de instanciar um novo objeto
        // Transformar uma lista em array para fazer a operação

        String[] datas = request.toArray(new String[0]);
        String name = datas[0];
        String email = datas[1];
        Integer age = Integer.parseInt(datas[2]);
        Double height = Double.parseDouble(datas[3]);

        if (name.length() < 10) {
            throw new DomainException("O nome do usuário deve ter 10 ou mais caracteres!");
        }

        if (!email.contains("@")) {
            throw new DomainException("O email informado deve conter o caractere @");
        }

        if (age < 18) {
            throw new DomainException("Para ser cadastrado, o usuário deve ter 18 anos ou mais");
        }

        User user = new User(name, email, age, height);

        System.out.println("Usuário criado com sucesso!");

    }





}
