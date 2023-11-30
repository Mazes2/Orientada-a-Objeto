import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CadastroDAO.criarTabela(); 

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar Pessoa");
            System.out.println("2. Consultar Pessoas");
            System.out.println("3. Atualizar Pessoa");
            System.out.println("4. Excluir Pessoa");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarPessoa(scanner);
                    break;
                case 2:
                    consultarPessoas();
                    break;
                case 3:
                    atualizarPessoa(scanner);
                    break;
                case 4:
                    excluirPessoa(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarPessoa(Scanner scanner) {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Cliente (C) ou Funcionário (F)? ");
        char tipo = scanner.next().charAt(0);
        scanner.nextLine(); 

        if (tipo == 'C' || tipo == 'c') {
            System.out.print("Digite o CPF: ");
            String cpf = scanner.nextLine();
            Cliente cliente = new Cliente(0, nome, endereco, cpf);
            CadastroDAO.cadastrarPessoa(cliente);
        } else if (tipo == 'F' || tipo == 'f') {
            System.out.print("Digite o cargo: ");
            String cargo = scanner.nextLine();
            Funcionario funcionario = new Funcionario(0, nome, endereco, cargo);
            CadastroDAO.cadastrarPessoa(funcionario);
        } else {
            System.out.println("Tipo inválido. Operação de cadastro cancelada.");
        }
    }

    private static void consultarPessoas() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:cadastro.db");
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM pessoas")) {

            List<Pessoa> pessoas = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                String cpf = resultSet.getString("cpf");
                String cargo = resultSet.getString("cargo");

                if (cpf != null) {
                    pessoas.add(new Cliente(id, nome, endereco, cpf));
                } else if (cargo != null) {
                    pessoas.add(new Funcionario(id, nome, endereco, cargo));
                }
            }

            System.out.println("Pessoas cadastradas:");
            for (Pessoa pessoa : pessoas) {
                pessoa.exibirDetalhes();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void atualizarPessoa(Scanner scanner) {
        System.out.print("Digite o ID da pessoa que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:cadastro.db");
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM pessoas WHERE id = ?")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                String cpf = resultSet.getString("cpf");
                String cargo = resultSet.getString("cargo");

                System.out.println("Dados atuais:");
                System.out.println("Nome: " + nome);
                System.out.println("Endereço: " + endereco);
                if (cpf != null) {
                    System.out.println("CPF: " + cpf);
                } else if (cargo != null) {
                    System.out.println("Cargo: " + cargo);
                }

                System.out.println("Digite os novos dados:");

                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();

                System.out.print("Novo endereço: ");
                String novoEndereco = scanner.nextLine();

                if (cpf != null) {
                    Cliente cliente = new Cliente(id, novoNome, novoEndereco, cpf);
                    CadastroDAO.cadastrarPessoa(cliente);
                } else if (cargo != null) {
                    System.out.print("Novo cargo: ");
                    String novoCargo = scanner.nextLine();
                    Funcionario funcionario = new Funcionario(id, novoNome, novoEndereco, novoCargo);
                    CadastroDAO.cadastrarPessoa(funcionario);
                }

                System.out.println("Pessoa atualizada com sucesso!");
            } else {
                System.out.println("Pessoa não encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void excluirPessoa(Scanner scanner) {
        System.out.print("Digite o ID da pessoa que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:cadastro.db");
             PreparedStatement statement = conn.prepareStatement("DELETE FROM pessoas WHERE id = ?")) {

            statement.setInt(1, id);
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pessoa excluída com sucesso!");
            } else {
                System.out.println("Pessoa não encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
