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
    public boolean movimentoValido(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int direcao = isBranca() ? 1 : -1; // Brancos movem-se para cima (linha+1), Pretos para baixo (linha-1)

        if (colunaOrigem == colunaDestino) {
            // Movimento simples de uma casa
            if (linhaDestino - linhaOrigem == direcao) {
                return true;
            }
            // Primeiro movimento do Peão, movendo duas casas
            if ((linhaOrigem == 2 && isBranca()) || (linhaOrigem == 7 && !isBranca())) {
                return linhaDestino - linhaOrigem == 2 * direcao;
            } // Brancas começam na linha 2, pretas na linha 7
        }
        // Captura na diagonal
        return Math.abs(colunaDestino - colunaOrigem) == 1 && linhaDestino - linhaOrigem == direcao;
    }

    // public boolean movimentoValido(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino, boolean casaDestinoestaOcupada) {
    //     int direcao = isBranca() ? 1 : -1; // Brancos movem-se para cima (linha+1), Pretos para baixo (linha-1)

    //     if (colunaOrigem == colunaDestino && !casaDestinoestaOcupada) {
    //         // Movimento simples de uma casa
    //         if (linhaDestino - linhaOrigem == direcao && !casaDestinoestaOcupada) {
    //             return true;
    //         } 
    //         // Primeiro movimento do Peão, movendo duas casas
    //         if ((linhaOrigem == 2 && isBranca()) || (linhaOrigem == 7 && !isBranca())) {
    //             return linhaDestino - linhaOrigem == 2 * direcao;
    //         } // Brancas começam na linha 2, pretas na linha 7
    //     }
    //     // Captura na diagonal
    //     if (casaDestinoestaOcupada) {
    //         return Math.abs(colunaDestino - colunaOrigem) == 1 && linhaDestino - linhaOrigem == direcao;
    //     } else {
    //         return false;
    //     }
    // }

    @Override
    public String caminho(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (movimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
            // Se o peão avançar duas casas, inclui a casa intermediária no caminho
            if (Math.abs(linhaDestino - linhaOrigem) == 2) {
                int linhaIntermediaria = linhaOrigem + (isBranca() ? 1 : -1); // Casa intermediária entre origem e destino
                return linhaOrigem + "" + colunaOrigem + linhaIntermediaria + "" + colunaOrigem + linhaDestino + "" + colunaDestino;
            }
            // Movimento de uma casa ou captura
            return linhaOrigem + "" + colunaOrigem + linhaDestino + "" + colunaDestino;
        }
        return "";
    }
}
