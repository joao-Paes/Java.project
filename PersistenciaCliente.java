import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersistenciaCliente {
    private static final String FILE_NAME = "clientes.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Carregar clientes do arquivo
        List<Cliente> clientes = carregarClientes();

        System.out.println("Clientes carregados:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente por ID");
            System.out.println("4. Excluir Cliente por ID");
            System.out.println("5. Salvar e Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1: // Adicionar Cliente
                    System.out.print("Digite o ID do cliente: ");
                    String id = scanner.nextLine();
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o email do cliente: ");
                    String email = scanner.nextLine();

                    clientes.add(new Cliente(id, nome, email));
                    System.out.println("Cliente adicionado!");
                    break;

                case 2: // Listar Clientes
                    System.out.println("Clientes cadastrados:");
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (Cliente cliente : clientes) {
                            System.out.println(cliente);
                        }
                    }
                    break;

                case 3: // Buscar Cliente por ID
                    System.out.print("Digite o ID do cliente: ");
                    String buscaId = scanner.nextLine();
                    Cliente encontrado = clientes.stream()
                            .filter(c -> c.getId().equals(buscaId))
                            .findFirst()
                            .orElse(null);
                    if (encontrado != null) {
                        System.out.println("Cliente encontrado: " + encontrado);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 4: // Excluir Cliente por ID
                    System.out.print("Digite o ID do cliente a ser excluído: ");
                    String excluiId = scanner.nextLine();
                    boolean removido = clientes.removeIf(c -> c.getId().equals(excluiId));
                    if (removido) {
                        System.out.println("Cliente removido com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 5: // Salvar e Sair
                    salvarClientes(clientes);
                    System.out.println("Clientes salvos. Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Cliente> carregarClientes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void salvarClientes(List<Cliente> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }
}
