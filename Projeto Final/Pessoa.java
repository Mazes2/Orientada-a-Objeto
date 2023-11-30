public abstract class Pessoa {
    private int id;
    private String nome;
    private String endereco;

    public Pessoa(int id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getters e Setters

    public abstract void exibirDetalhes();
}
