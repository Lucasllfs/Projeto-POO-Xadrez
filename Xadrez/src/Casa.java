// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
import Pecas.*;

public class Casa {
    private String cor;  // Pode ser "branco" ou "preto"
    private int linha;   // De 1 a 8
    private char coluna; // De 'a' a 'h'
    private Peca peca;   // Referência para a peça que ocupa a casa (pode ser null)

     public Casa(String cor, int linha, char coluna) throws IllegalArgumentException {
        if (!cor.equals("branco") && !cor.equals("preto")) {
            throw new IllegalArgumentException("Cor inválida. Use 'branco' ou 'preto'.");
        }
        if (linha < 1 || linha > 8) {
            throw new IllegalArgumentException("Linha inválida. Deve estar entre 1 e 8.");
        }
        if (coluna < 'a' || coluna > 'h') {
            throw new IllegalArgumentException("Coluna inválida. Deve estar entre 'a' e 'h'.");
        }
        this.cor = cor;
        this.linha = linha;
        this.coluna = coluna;
        this.peca = null; // Inicialmente, a casa está vazia
    }

    public String getCor() {
        return cor;
    }

    public int getLinha() {
        return linha;
    }

    public char getColuna() {
        return coluna;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public boolean estaOcupada() {
        return this.peca != null;
    }

    @Override
    public String toString() {
        if (peca == null) {
            return cor.equals("branco") ? "□" : "■";
        } else {
            return peca.desenho();
        }
    }
}
