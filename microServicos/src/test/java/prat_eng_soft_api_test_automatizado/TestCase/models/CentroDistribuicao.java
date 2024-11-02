package prat_eng_soft_api_test_automatizado.TestCase.models;

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
