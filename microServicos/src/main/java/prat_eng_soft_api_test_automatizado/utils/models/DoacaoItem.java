package prat_eng_soft_api_test_automatizado.utils.models;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DoacaoItem {
    private int id;
    private String name;
    private int quantity;

    public DoacaoItem(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public static DoacaoItem criarItem() {
        Faker faker = new Faker(new Locale("pt-BR"));
        return new DoacaoItem(1, faker.food().ingredient(), faker.number().numberBetween(1, 1000));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
