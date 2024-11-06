/* Participantes
 * Petrus Ermerson B. de Melo
 * Hugo Ian de Melo Soares
 */

public class ListaEncadeada {
    private Node head;
    private Node tail;

    private class Node {
        String valor;
        Node next;

        Node(String valor) {
            this.valor = valor;
        }
    }

    /*
     * Adicionar Elemento em uma Posição Específica
     * Complexidade Temporal: O(n).
     */
    public void adicionarEmPosicao(String valor, int posicao) {
        Node novoNode = new Node(valor);

        if (posicao == 0) {
            novoNode.next = head;
            head = novoNode;
            if (tail == null) {
                tail = novoNode;
            }
            return;
        }

        Node atual = head;
        for (int i = 0; i < posicao - 1 && atual != null; i++) {
            atual = atual.next;
        }

        if (atual == null)
            throw new IndexOutOfBoundsException("Posição inválida");

        novoNode.next = atual.next;
        atual.next = novoNode;

        if (novoNode.next == null) {
            tail = novoNode; // Atualiza a cauda
        }
    }

    /*
     * Remover um Elemento
     * Complexidade Temporal: O(n).
     */
    public boolean remover(String valor) {
        if (head == null)
            return false;

        if (head.valor.equals(valor)) {
            head = head.next;
            if (head == null) {
                tail = null; // Atualiza a cauda
            }
            return true;
        }

        Node atual = head;
        while (atual.next != null && !atual.next.valor.equals(valor)) {
            atual = atual.next;
        }

        if (atual.next == null)
            return false;

        atual.next = atual.next.next;
        if (atual.next == null) {
            tail = atual; // Atualiza a cauda
        }
        return true;
    }

    /*
     * Inverter a Lista
     * Complexidade Temporal: O(n).
     */
    public void inverter() {
        Node anterior = null, atual = head, proximo;
        tail = head;

        while (atual != null) {
            proximo = atual.next;
            atual.next = anterior;
            anterior = atual;
            atual = proximo;
        }
        head = anterior;
    }

    /**
     * Retornar o Nó que Está no Meio da Lista
     * Complexidade Temporal: O(n).
     */
    public Node obterNoDoMeio() {
        if (head == null)
            return null;
        Node rapido = head, lento = head;
        while (rapido != null && rapido.next != null) {
            rapido = rapido.next.next;
            lento = lento.next;
        }
        return lento;
    }

    /*
     * Remover Nós Duplicados
     * Complexidade Temporal: O(n²).
     */
    public void removerDuplicados() {
        Node atual = head;
        while (atual != null) {
            Node verificador = atual;
            while (verificador.next != null) {
                if (verificador.next.valor.equals(atual.valor)) {
                    verificador.next = verificador.next.next;
                } else {
                    verificador = verificador.next;
                }
            }
            atual = atual.next;
        }
    }

    public void exibirLista() {
        Node atual = head;
        while (atual != null) {
            System.out.print(atual.valor + " -> ");
            atual = atual.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListaEncadeada lista = new ListaEncadeada();

        lista.adicionarEmPosicao("Petrus", 0);
        lista.adicionarEmPosicao("Hugo", 1);
        lista.adicionarEmPosicao("Caio", 2);
        lista.adicionarEmPosicao("Gabriel", 3);

        System.out.print("Lista inicial: ");
        lista.exibirLista();

        lista.remover("Caio");
        System.out.print("Após remoção: ");
        lista.exibirLista();

        lista.inverter();
        System.out.print("Após inverter a lista: ");
        lista.exibirLista();

        ListaEncadeada.Node meio = lista.obterNoDoMeio();
        System.out.println("Node do meio: " + (meio != null ? meio.valor : "Lista vazia"));

        lista.adicionarEmPosicao("Hugo", 1);
        lista.adicionarEmPosicao("Petrus", 2);
        System.out.print("Com duplicados: ");
        lista.exibirLista();

        lista.removerDuplicados();
        System.out.print("Após remover duplicados: ");
        lista.exibirLista();
    }
}
