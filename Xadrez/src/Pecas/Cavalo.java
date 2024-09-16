// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
package Pecas;

public class Cavalo extends Peca {

    public Cavalo(boolean Branca) {
        super(Branca);
    }

    @Override
    public String desenho() {
        return isBranca() ? "C" : "c"; // Usando o método isBranca()
    }

    @Override
    public boolean movimentoValido(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diferencaLinha = Math.abs(linhaDestino - linhaOrigem);
        int diferencaColuna = Math.abs(colunaDestino - colunaOrigem);

        // cavalo se move em forma de L
        return (diferencaLinha == 2 && diferencaColuna == 1) || (diferencaLinha == 1 && diferencaColuna == 2);
    }

    @Override
    public String caminho(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (movimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
            return linhaOrigem + "" + colunaOrigem + linhaDestino + "" + colunaDestino; // n tem caminho intermediario para o aavalo
        }
        return "";
    }
}
