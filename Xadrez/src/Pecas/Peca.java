// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
package Pecas;

public abstract class Peca {
    protected boolean Branca; // true se a peça for branca, false se for preta
    protected boolean capturada; // true se a peça foi capturada

    public Peca(boolean Branca) {
        this.Branca = Branca;
        this.capturada = false;
    }

    public boolean isBranca() {
        return Branca;
    }

    public boolean Capturada() {
        return capturada;
    }

    public void setCapturada(boolean capturada) {
        this.capturada = capturada;
    }

    public abstract String desenho();

    public abstract boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD);

    public abstract String caminho(int linhaO, char colunaO, int linhaD, char colunaD);
}
