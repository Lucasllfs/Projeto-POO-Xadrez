package Pecas;

public class Torre extends Peca {

    public Torre(boolean Branca) {
        super(Branca);
    }

    @Override
    public String desenho() {
        return isBranca() ? "T" : "t";
    }

    @Override
    public boolean movimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        // A Torre se move na horizontal ou vertical
        return linhaOrigem == linhaDestino || colunaOrigem == colunaDestino;
    }

    @Override
    public String caminho(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        if (movimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
            StringBuilder caminho = new StringBuilder();
            int incrementoLinha = linhaOrigem == linhaDestino ? 0 : Integer.compare(linhaDestino, linhaOrigem);//em t
            int incrementoColuna = colunaOrigem == colunaDestino ? 0 : Integer.compare(colunaDestino, colunaOrigem);

            int linhaAtual = linhaOrigem + incrementoLinha;
            int colunaAtual = colunaOrigem + incrementoColuna;

            while (linhaAtual != linhaDestino || colunaAtual != colunaDestino) {
                caminho.append(linhaAtual).append(colunaAtual);
                linhaAtual += incrementoLinha;
                colunaAtual += incrementoColuna;
            }
            caminho.append(linhaDestino).append(colunaDestino);

            return caminho.toString();
        }
        return "";
    }
}
