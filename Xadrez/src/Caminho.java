public class Caminho {
    private Tabuleiro tabuleiro;
    private Casa[] sequenciaCasas;

    public Caminho(Tabuleiro tabuleiro, String sequencia) {
        this.tabuleiro = tabuleiro;
        // Constrói um array de Casa a partir da string de sequência
        construirSequenciaCasas(sequencia);
    }

    private void construirSequenciaCasas(String sequencia) {
        // Calcula o número de casas no caminho
        int numeroCasas = sequencia.length() / 2;
        sequenciaCasas = new Casa[numeroCasas];

        for (int i = 0; i < sequencia.length(); i += 2) {
            int linha = sequencia.charAt(i) - '0';
            char coluna = sequencia.charAt(i + 1);
            Casa casa = tabuleiro.getCasa(linha, coluna);
            if (casa != null) {
                sequenciaCasas[i / 2] = casa;
            }
        }
    }

    public boolean estaLivre() {
        // Verifica todas as casas do caminho, exceto a inicial e a final
        for (int i = 1; i < sequenciaCasas.length - 1; i++) {
            if (sequenciaCasas[i].estaOcupada()) {
                return false; // Caso encontre casa ocupada
            }
        }
        return true; // Caso todas as casas do meio estiverem livres
    }

    public Casa casaInicial() {
        // Retorna a primeira casa no array
        if (sequenciaCasas.length > 0) {
            return sequenciaCasas[0];
        }
        return null; // Caso o array esteja vazio
    }

    public Casa casaFinal() {
        // Retorna a última casa no array
        if (sequenciaCasas.length > 0) {
            return sequenciaCasas[sequenciaCasas.length - 1];
        }
        return null; // Caso o array esteja vazio
    }
}
