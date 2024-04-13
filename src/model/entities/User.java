package model.entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private int age;
    private double height;
    private List<String> additionalInformation = new ArrayList<>();

    public User(String name, String email, int age, double height) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
    }

    // Constructor to use in the usersDB method in UsersController
    public User(String name, String email, int age, double height, List<String> additionalInformation) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
        this.additionalInformation = additionalInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<String> getAdditionalInformation() {
        return additionalInformation;
    }

    public String convertAgeToString() {
        return String.valueOf(age);
    }

    @Override
    public String toString() {
        StringBuilder printUser = new StringBuilder();

        printUser.append("Nome: " + getName() + "\n");
        printUser.append("Email: " + getEmail() + "\n");
        printUser.append("Idade: " + getAge() + "\n");
        printUser.append("Altura: " + String.format("%.2f", getHeight()) + "\n");

        if (!getAdditionalInformation().isEmpty()) {
            printUser.append("Informações adicionais: " + "\n");
            for (String s : getAdditionalInformation()) {
                printUser.append(s + "\n");
            }
        }

        return printUser.toString();
    }
}
