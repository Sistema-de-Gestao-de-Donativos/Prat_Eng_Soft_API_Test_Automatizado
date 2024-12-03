package prat_eng_soft_api_test_automatizado.utils.models;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DoacaoDoador {
    private String name;
    private String document;
    private String email;
    private String phone;

    public DoacaoDoador(String name, String document, String email, String phone) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.phone = phone;
    }

    public static DoacaoDoador criarDoador() {
        Faker faker = new Faker(new Locale("pt-BR"));
        return new DoacaoDoador(
                faker.name().fullName(),
                faker.number().digits(11),
                faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
