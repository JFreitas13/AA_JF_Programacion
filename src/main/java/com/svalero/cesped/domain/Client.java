package com.svalero.cesped.domain;

public class Client {

    private int idClient;
    private String name;
    private String surname;
    private String dni;
    private String phone;
    private String email;

    public Client() {

    }

    public Client(String name, String surname, String dni, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.phone = phone;
        this.email = email;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
