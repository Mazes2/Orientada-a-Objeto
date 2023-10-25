// Classe Pessoa representa uma pessoa genérica
class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}

// Classe Funcionario herda de Pessoa e representa um funcionário genérico
class Funcionario extends Pessoa {
    private int numeroFuncionario;
    private double salario;

    public Funcionario(String nome, int idade, int numeroFuncionario, double salario) {
        super(nome, idade);
        this.numeroFuncionario = numeroFuncionario;
        this.salario = salario;
    }

    public int getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public double getSalario() {
        return salario;
    }
}

// Classe Gerente herda de Funcionario e representa um gerente
class Gerente extends Funcionario {
    private String departamento;

    public Gerente(String nome, int idade, int numeroFuncionario, double salario, String departamento) {
        super(nome, idade, numeroFuncionario, salario);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }
}

public class Main {
    public static void main(String[] args) {
        // Criando um objeto Gerente
        Gerente gerente = new Gerente("João", 35, 101, 6000.0, "TI");

        // Acessando os atributos usando os métodos getters
        System.out.println("Nome do Gerente: " + gerente.getNome());
        System.out.println("Idade do Gerente: " + gerente.getIdade());
        System.out.println("Número do Funcionário: " + gerente.getNumeroFuncionario());
        System.out.println("Salário do Gerente: $" + gerente.getSalario());
        System.out.println("Departamento do Gerente: " + gerente.getDepartamento());
    }
}
