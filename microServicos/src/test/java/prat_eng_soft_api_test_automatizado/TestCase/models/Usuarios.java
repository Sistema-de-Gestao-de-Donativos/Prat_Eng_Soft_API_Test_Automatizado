package prat_eng_soft_api_test_automatizado.TestCase.models;

public class Usuarios {
    private String name;
    private UsuariosAddress address;
    private String email;
    private String phone;
    private String role;
    private int codEntidade;
    private String cpf;

    public Usuarios(String name, UsuariosAddress address, String email, String phone, String role, int codEntidade, String cpf) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.codEntidade = codEntidade;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UsuariosAddress getAddress() {
        return address;
    }

    public void setAddress(UsuariosAddress address) {
        this.address = address;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCodEntidade() {
        return codEntidade;
    }

    public void setCodEntidade(int codEntidade) {
        this.codEntidade = codEntidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "UsuariosDTO [address=" + address + ", codEntidade=" + codEntidade + ", cpf=" + cpf + ", email=" + email
                + ", name=" + name + ", phone=" + phone + ", role=" + role + "]";
    }

    

}
