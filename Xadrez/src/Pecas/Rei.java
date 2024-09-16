// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
package Pecas;

public class Rei extends Peca {

    public Rei(boolean Branca) {
        super(Branca);
    }

    @Override
    public String desenho() {
        return isBranca()? "R" : "r"; // Representação do Rei: 'R' para branco, 'r' para preto
    }

    @Override
    public boolean movimentoValido(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diferencaLinha = Math.abs(linhaDestino - linhaOrigem);
        int diferencaColuna = Math.abs(colunaDestino - colunaOrigem);// Armzena valor absoluto

        // rei se move apenas uma casa em qualquer direção
        return diferencaLinha <= 1 && diferencaColuna <= 1;
    }

    @Override
    public String caminho(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (movimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
            return linhaOrigem + "" + colunaOrigem + linhaDestino + "" + colunaDestino; // Apenas a origem e o destino, sem caminho intermediário
        }
        return "";
    }
}
