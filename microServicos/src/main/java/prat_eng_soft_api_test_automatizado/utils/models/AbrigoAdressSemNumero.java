package prat_eng_soft_api_test_automatizado.utils.models;

import java.util.Locale;

import com.github.javafaker.Faker;

public class AbrigoAdressSemNumero {
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;

    public AbrigoAdressSemNumero(String country, String state, String city, String neighborhood, String street) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
    }

    public static AbrigoAdressSemNumero criarEndereco() {
        Faker faker = new Faker(new Locale("pt-BR"));

        return new AbrigoAdressSemNumero("Brasil", faker.address().state(),
                faker.address().city(), faker.address().cityName(), faker.address().streetName());
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

    @Override
    public String toString() {
        return "AbrigoAddressDTO [city=" + city + ", country=" + country + ", neighborhood=" + neighborhood + ", state="
                + state + ", street=" + street + "]";
    }

}