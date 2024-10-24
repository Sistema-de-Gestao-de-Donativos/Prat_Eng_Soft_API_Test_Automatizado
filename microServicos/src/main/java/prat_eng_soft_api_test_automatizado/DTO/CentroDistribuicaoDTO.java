package prat_eng_soft_api_test_automatizado.DTO;

public class CentroDistribuicaoDTO {
    private String name;
    private String phone;
    private String email;
    private CentroDistribuicaoAddressDTO address;

    public CentroDistribuicaoDTO(String name, String phone, String email, CentroDistribuicaoAddressDTO address) {
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

    public CentroDistribuicaoAddressDTO getAddress() {
        return address;
    }

    public void setAddress(CentroDistribuicaoAddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CentroDistribuicaoDTO [address=" + address + ", email=" + email + ", name=" + name + ", phone=" + phone + "]";
    }

}
