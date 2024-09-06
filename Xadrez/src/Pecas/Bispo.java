package Pecas;

public class Bispo extends Peca {

    public Bispo(boolean Branca) {
        super(Branca);
    }

    @Override
    public String desenho() {
        return isBranca() ? "B" : "b"; // Representação do Bispo: 'B' para branco, 'b' para preto
    }

    @Override
    public boolean movimentoValido(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int diferencaLinha = Math.abs(linhaDestino - linhaOrigem);
        int diferencaColuna = Math.abs(colunaDestino - colunaOrigem);

        // O Bispo se move apenas na diagonal
        return diferencaLinha == diferencaColuna;
    }//verifica se a diferença de linha e igual a diferença de coluna. Se for, o movimento eh diagonal

    @Override
    public String caminho(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (movimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
            StringBuilder caminho = new StringBuilder();
            int incrementoLinha = Integer.compare(linhaDestino, linhaOrigem);
            int incrementoColuna = Integer.compare(colunaDestino, colunaOrigem);

            int linhaAtual = linhaOrigem;
            char colunaAtual = colunaOrigem;

            while (linhaAtual != linhaDestino && colunaAtual != colunaDestino) {
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
