package prat_eng_soft_api_test_automatizado.utils.models;

import java.util.Locale;

import com.github.javafaker.Faker;

public class CentroDistribuicaoSemNumero {
    private String name;
    private String phone;
    private String email;
    private CentroDistribuicaoAddressSemNumero address;

    public CentroDistribuicaoSemNumero(String name, String phone, String email, CentroDistribuicaoAddressSemNumero address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public static CentroDistribuicaoSemNumero criarCentroDistribuicaoSemNumero() {
        Faker faker = new Faker(new Locale("pt-BR"));
        return new CentroDistribuicaoSemNumero(faker.address().cityName()+'_'+faker.address().zipCode(), faker.phoneNumber().cellPhone(), faker.internet().emailAddress(),
        CentroDistribuicaoAddressSemNumero.criarEndereco());
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

    public CentroDistribuicaoAddressSemNumero getAddress() {
        return address;
    }

    public void setAddress(CentroDistribuicaoAddressSemNumero address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CentroDistribuicaoDTO [address=" + address + ", email=" + email + ", name=" + name + ", phone=" + phone + "]";
    }

}
