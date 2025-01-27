import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private List<Cliente> clientes;

    // Construtor
    public ClienteService(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    // Método para buscar cliente por ID
    public Cliente buscarCliente(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("O ID não pode ser nulo ou vazio");
        }

        return clientes.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Método principal para teste
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        ClienteService clienteService = new ClienteService(clientes);

        // Adicionar alguns clientes
        clientes.add(new Cliente("1", "João", "joao@email.com"));
        clientes.add(new Cliente("2", "Maria", "maria@email.com"));

        // Buscar cliente por ID
        Cliente clienteEncontrado = clienteService.buscarCliente("1");
        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado: " + clienteEncontrado);
        } else {
            System.out.println("Cliente não encontrado");
        }
    }
}
