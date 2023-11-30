import java.sql.*;

public class CadastroDAO {
    private static final String URL = "jdbc:sqlite:cadastro.db";

    public static void criarTabela() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS pessoas " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "endereco TEXT NOT NULL, " +
                    "cpf TEXT, " +
                    "cargo TEXT)";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cadastrarPessoa(Pessoa pessoa) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement statement = conn.prepareStatement("INSERT INTO pessoas (nome, endereco, cpf, cargo) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getEndereco());
            if (pessoa instanceof Cliente) {
                statement.setString(3, ((Cliente) pessoa).getCpf());
            } else {
                statement.setString(4, ((Funcionario) pessoa).getCargo());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Pessoa> consultarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pessoas");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                String cpf = resultSet.getString("cpf");
                String cargo = resultSet.getString("cargo");

                if (cpf != null) {
                    pessoas.add(new Cliente(id, nome, endereco, cpf));
                } else {
                    pessoas.add(new Funcionario(id, nome, endereco, cargo));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public static void atualizarPessoa(int id, Pessoa pessoa) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement statement = conn.prepareStatement("UPDATE pessoas SET nome=?, endereco=?, cpf=?, cargo=? WHERE id=?")) {
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getEndereco());
            if (pessoa instanceof Cliente) {
                statement.setString(3, ((Cliente) pessoa).getCpf());
                statement.setString(4, null); // Set cargo to null for generic update
            } else {
                statement.setString(3, null); // Set cpf to null for generic update
                statement.setString(4, ((Funcionario) pessoa).getCargo());
            }
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void excluirPessoa(int id) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement statement = conn.prepareStatement("DELETE FROM pessoas WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
