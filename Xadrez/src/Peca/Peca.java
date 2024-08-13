package Peca;

public abstract class Peca {
    protected boolean corBranca; // true se a peça for branca, false se for preta
    protected boolean capturada; // true se a peça foi capturada

    public Peca(boolean corBranca) {
        this.corBranca = corBranca;
        this.capturada = false;
    }

    public boolean CorBranca() {
        return corBranca;
    }

    public boolean Capturada() {
        return capturada;
    }

    public void setCapturada(boolean capturada) {
        this.capturada = capturada;
    }

    public abstract String desenho();
    public abstract boolean movimentoValido(int linhaO, int colunaO, int linhaD, int colunaD);
    public abstract String caminho(int linhaO, int colunaO, int linhaD, int colunaD);
}
