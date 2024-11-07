package prat_eng_soft_api_test_automatizado.TestCase.models;

import java.util.Locale;

import com.github.javafaker.Faker;

public class UsuariosAddress {
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private int number;

    public UsuariosAddress(String country, String state, String city, String neighborhood, String street, int number) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
    }

    public static UsuariosAddress criarEndereco() {
        Faker faker = new Faker(new Locale("pt-BR"));

        return new UsuariosAddress("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName(),
                faker.number().numberBetween(1, 1000));
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "UsuariosAddressDTO [city=" + city + ", country=" + country + ", neighborhood=" + neighborhood
                + ", number="
                + number + ", state=" + state + ", street=" + street + "]";
    }

}
