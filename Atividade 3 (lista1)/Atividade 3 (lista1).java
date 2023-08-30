import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Item {
    private String nome;
    private int quantidade;

    public Item(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Item: " + nome + " - Quantidade: " + quantidade;
    }
}

class Estoque {
    private List<Item> itens;

    public Estoque() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(String nome, int quantidade) {
        Item novoItem = new Item(nome, quantidade);
        itens.add(novoItem);
        System.out.println("Item adicionado ao estoque.");
    }

    public void removerItem(String nome) {
        Item itemRemover = null;
        for (Item item : itens) {
            if (item.getNome().equals(nome)) {
                itemRemover = item;
                break;
            }
        }

        if (itemRemover != null) {
            itens.remove(itemRemover);
            System.out.println("Item removido do estoque.");
        } else {
            System.out.println("Item não encontrado no estoque.");
        }
    }

    public void atualizarQuantidade(String nome, int novaQuantidade) {
        for (Item item : itens) {
            if (item.getNome().equals(nome)) {
                item.setQuantidade(novaQuantidade);
                System.out.println("Quantidade atualizada.");
                return;
            }
        }
        System.out.println("Item não encontrado no estoque.");
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            for (Item item : itens) {
                System.out.println(item);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();

        while (true) {
            System.out.println("\nSistema de Gerenciamento de Estoque");
            System.out.println("1. Adicionar Item");
            System.out.println("2. Remover Item");
            System.out.println("3. Atualizar Quantidade");
            System.out.println("4. Listar Itens");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();  // Consumir o caractere de nova linha

            switch (escolha) {
                case 1:
                    System.out.print("Digite o nome do item: ");
                    String nomeItem = scanner.nextLine();
                    System.out.print("Digite a quantidade: ");
                    int quantidadeItem = scanner.nextInt();
                    estoque.adicionarItem(nomeItem, quantidadeItem);
                    break;
                case 2:
                    System.out.print("Digite o nome do item para remover: ");
                    String itemParaRemover = scanner.nextLine();
                    estoque.removerItem(itemParaRemover);
                    break;
                case 3:
                    System.out.print("Digite o nome do item para atualizar a quantidade: ");
                    String itemParaAtualizar = scanner.nextLine();
                    System.out.print("Digite a nova quantidade: ");
                    int novaQuantidade = scanner.nextInt();
                    estoque.atualizarQuantidade(itemParaAtualizar, novaQuantidade);
                    break;
                case 4:
                    estoque.listarItens();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        }
    }
}
