import java.io.Serializable;

public class Cliente implements Serializable {
    private String id;
    private String nome;
    private String email;

    // Construtor
    public Cliente(String id, String nome, String email) {
        if (id == null || nome == null || email == null) {
            throw new IllegalArgumentException("Nenhum dos parâmetros pode ser nulo");
        }
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email != null ? email : "Email não cadastrado";
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString
    @Override
    public String toString() {
        return id + ";" + nome + ";" + email;
    }

    // Criar Cliente a partir de String
    public static Cliente fromString(String linha) {
        if (linha == null || linha.isEmpty()) {
            throw new IllegalArgumentException("A linha de entrada não pode ser nula ou vazia");
        }

        String[] partes = linha.split(";");
        if (partes.length != 3) {
            throw new IllegalArgumentException("Formato inválido: " + linha);
        }

        return new Cliente(partes[0], partes[1], partes[2]);
    }
}
