public class Cliente extends Pessoa {
    private String cpf;

    public Cliente(int id, String nome, String endereco, String cpf) {
        super(id, nome, endereco);
        this.cpf = cpf;
    }

    // Getter e Setter para CPF

    @Override
    public void exibirDetalhes() {
        System.out.println("Cliente - ID: " + getId() + ", Nome: " + getNome() + ", CPF: " + cpf);
    }
}
