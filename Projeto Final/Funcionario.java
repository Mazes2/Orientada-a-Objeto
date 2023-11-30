public class Funcionario extends Pessoa {
    private String cargo;

    public Funcionario(int id, String nome, String endereco, String cargo) {
        super(id, nome, endereco);
        this.cargo = cargo;
    }

    // Getter e Setter para Cargo

    @Override
    public void exibirDetalhes() {
        System.out.println("Funcionário - ID: " + getId() + ", Nome: " + getNome() + ", Cargo: " + cargo);
    }
}
