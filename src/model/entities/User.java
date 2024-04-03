package model.entities;

public class User {

    private String name;
    private String email;
    private int idade;
    private double altura;

    public User(String name, String email, int idade, double altura) {
        this.name = name;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return name + '\'' +
                email + '\'' +
                idade + '\'' +
                altura;
    }
}
