package Pecas;

public class Dama extends Peca {

    public Dama(boolean ehBranca) {
        super(ehBranca);
    }

    @Override
    public String desenho() {
        return isBranca() ? "D" : "d"; //msm padrao do rei
    }

    @Override
    public boolean movimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        int diferencaLinha = Math.abs(linhaDestino - linhaOrigem);
        int diferencaColuna = Math.abs(colunaDestino - colunaOrigem);

        // dama pode se mover na horizontal, vertical ou diagonal
        return diferencaLinha == diferencaColuna || linhaOrigem == linhaDestino || colunaOrigem == colunaDestino;
    }

    @Override
    public String caminho(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
//        retorna o caminho que a Dama percorreria, casa por casa, do ponto de origem ao ponto de destino.
//        Usa incrementos para linha e coluna baseados na direcao do movimento e constroi uma string com todas
//        as posicoes intermediarias.

        if (movimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
            StringBuilder caminho = new StringBuilder();// stringBuilder para concatenar strings
             //retorna -1, 0 ou 1, dependendo se o destino e menor, igual ou maior que a origem, respectivamente.
            int incrementoLinha = Integer.compare(linhaDestino, linhaOrigem);
            int incrementoColuna = Integer.compare(colunaDestino, colunaOrigem);

            int linhaAtual = linhaOrigem;
            int colunaAtual = colunaOrigem;

            while (linhaAtual != linhaDestino || colunaAtual != colunaDestino) {
                caminho.append(linhaAtual).append(colunaAtual);//add o valor da var colunaAtual ao StringBuilder caminho imediatamente após linhaAtual.
                linhaAtual += incrementoLinha;
                colunaAtual += incrementoColuna;
            }
            caminho.append(linhaDestino).append(colunaDestino);

            return caminho.toString();
        }
        return "";
    }
}