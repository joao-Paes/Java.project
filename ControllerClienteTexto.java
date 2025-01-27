import java.io.*;
import java.util.*;

public class ControllerClienteTexto {
    private List<Cliente> clientes = new ArrayList<>();
    private static final String ARQUIVO = "clientes.txt";

    public void carregarDados() throws IOException {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    try {
                        clientes.add(Cliente.fromString(linha));
                    } catch (Exception e) {
                        System.out.println("Erro ao carregar cliente: " + linha);
                    }
                }
            }
        }
    }

    public void salvarDados() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Cliente c : clientes) {
                bw.write(c.toString());
                bw.newLine();
            }
        }
    }

    public void adicionarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("cliente - nulo");
        }
        clientes.add(cliente);
    }

    public void removerCliente(String id) {
        if (id != null) {
            clientes.removeIf(c -> c.getId().equals(id));
        } else {
            System.out.println("ID nulo não pode ser removido");
        }
    }

    public Cliente buscarCliente(String id) {
        if (id != null) {
            return clientes.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
        } else {
            System.out.println("ID nulo não pode ser buscado");
            return null;
        }
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes); // Retornar uma cópia da lista para evitar modificações externas
    }
}