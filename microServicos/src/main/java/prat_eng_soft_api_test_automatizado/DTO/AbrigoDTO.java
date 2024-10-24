package prat_eng_soft_api_test_automatizado.DTO;

public class AbrigoDTO {
    private String name;
    private String phone;
    private String email;
    private AbrigoAddressDTO address;

    public AbrigoDTO(String name, String phone, String email, AbrigoAddressDTO address) {
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

    public AbrigoAddressDTO getAddress() {
        return address;
    }

    public void setAddress(AbrigoAddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AbrigoDTO [address=" + address + ", email=" + email + ", name=" + name + ", phone=" + phone + "]";
    }

    
}
