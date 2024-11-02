package prat_eng_soft_api_test_automatizado.TestCase.models;

public class Abrigo {
    private String name;
    private String phone;
    private String email;
    private AbrigoAddress address;

    public Abrigo(String name, String phone, String email, AbrigoAddress address) {
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

    public AbrigoAddress getAddress() {
        return address;
    }

    public void setAddress(AbrigoAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AbrigoDTO [address=" + address + ", email=" + email + ", name=" + name + ", phone=" + phone + "]";
    }

    
}
