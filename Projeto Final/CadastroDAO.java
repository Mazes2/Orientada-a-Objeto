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
}
