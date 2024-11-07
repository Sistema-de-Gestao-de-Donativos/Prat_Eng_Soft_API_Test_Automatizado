package prat_eng_soft_api_test_automatizado.TestCase.models;

import java.util.Locale;

import com.github.javafaker.Faker;

public class CentroDistribuicao {
    private String name;
    private String phone;
    private String email;
    private CentroDistribuicaoAddress address;

    public CentroDistribuicao(String name, String phone, String email, CentroDistribuicaoAddress address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public static CentroDistribuicao criarCentroDistribuicao() {
        Faker faker = new Faker(new Locale("pt-BR"));
        return new CentroDistribuicao(faker.address().cityName()+'_'+faker.address().zipCode(), faker.phoneNumber().cellPhone(), faker.internet().emailAddress(),
                CentroDistribuicaoAddress.criarEndereco());
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CentroDistribuicaoAddress getAddress() {
        return address;
    }

    public void setAddress(CentroDistribuicaoAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CentroDistribuicaoDTO [address=" + address + ", email=" + email + ", name=" + name + ", phone=" + phone + "]";
    }

}
