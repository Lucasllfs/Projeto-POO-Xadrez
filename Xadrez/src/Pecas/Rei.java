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
    public boolean movimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        int diferencaLinha = Math.abs(linhaDestino - linhaOrigem);
        int diferencaColuna = Math.abs(colunaDestino - colunaOrigem);// Armzena valor absoluto

        // rei se move apenas uma casa em qualquer direção
        return diferencaLinha <= 1 && diferencaColuna <= 1;
    }

    @Override
    public String caminho(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        if (movimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
            return linhaOrigem + "" + colunaOrigem + linhaDestino + "" + colunaDestino; // Apenas a origem e o destino, sem caminho intermediário
        }
        return "";
    }
}
