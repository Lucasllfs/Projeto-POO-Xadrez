package Pecas;

public class Peao extends Peca {

    public Peao(boolean ehBranca) {
        super(ehBranca);
    }

    @Override
    public String desenho() {
        return isBranca() ? "P" : "p"; // Representação do Peão: 'P' para branco, 'p' para preto
    }

    @Override
    public boolean movimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        int direcao = isBranca() ? 1 : -1; // Brancos movem-se para cima (linha+1), Pretos para baixo (linha-1)

        if (colunaOrigem == colunaDestino) {
            // Movimento simples
            if (linhaDestino - linhaOrigem == direcao) {
                return true;
            }
            // Primeiro movimento do Peão
            if ((linhaOrigem == 1 && isBranca()) || (linhaOrigem == 6 && !isBranca())) {
                return linhaDestino - linhaOrigem == 2 * direcao;
            }//as peças brancas começam na linha 1 e as pretas na linha 6.
        }
        // Captura na diagonal
        return Math.abs(colunaDestino - colunaOrigem) == 1 && linhaDestino - linhaOrigem == direcao;
    }

    @Override
    public String caminho(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        if (movimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
            return linhaOrigem + "" + colunaOrigem + linhaDestino + "" + colunaDestino; // Peão não possui caminho intermediário
        }
        return "";
    }
}
