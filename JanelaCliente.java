import javax.swing.*;

import java.io.IOException;

public class JanelaCliente extends JFrame {
    private JTextField txtId, txtNome, txtEmail;
    private JButton btnSalvar, btnConsultar, btnExcluir;
    private ControllerClienteTexto controller;

    public JanelaCliente() {
        controller = new ControllerClienteTexto();
        try {
            controller.carregarDados();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Cadastro de Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 100, 30);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(120, 20, 200, 30);
        add(txtId);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 100, 30);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 60, 200, 30);
        add(txtNome);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 100, 100, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 100, 200, 30);
        add(txtEmail);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 150, 100, 30);
        btnSalvar.addActionListener(e -> salvarCliente());
        add(btnSalvar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(140, 150, 100, 30);
        btnConsultar.addActionListener(e -> consultarCliente());
        add(btnConsultar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(260, 150, 100, 30);
        btnExcluir.addActionListener(e -> excluirCliente());
        add(btnExcluir);
    }

    private void salvarCliente() {
        String id = txtId.getText().trim();
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();

        if (id.isEmpty() || nome.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        try {
            Cliente cliente = new Cliente(id, nome, email);
            controller.adicionarCliente(cliente);
            controller.salvarDados();
            JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Erro: Cliente nulo.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados.");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado.");
            e.printStackTrace();
        }
    }

    private void consultarCliente() {
        String id = txtId.getText().trim();
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID n o pode ser nulo ou vazio.");
            return;
        }

        try {
            Cliente cliente = controller.buscarCliente(id);
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente n o encontrado.");
                return;
            }

            txtNome.setText(cliente.getNome());
            txtEmail.setText(cliente.getEmail());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Erro: Cliente nulo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado.");
            e.printStackTrace();
        }
    }

    private void excluirCliente() {
        String id = txtId.getText();
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID n o pode ser nulo ou vazio.");
            return;
        }

        try {
            if (controller.buscarCliente(id) == null) {
                JOptionPane.showMessageDialog(this, "Cliente n o encontrado.");
                return;
            }

            controller.removerCliente(id);
            controller.salvarDados();
            JOptionPane.showMessageDialog(this, "Cliente exclu do com sucesso!");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Erro: Cliente nulo.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados.");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaCliente().setVisible(true));
    }
}
