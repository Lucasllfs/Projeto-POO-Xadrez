// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
import Pecas.Peca;
import Pecas.Cavalo;
import Pecas.Peao;

public class Jogada {
    private Jogador jogador;
    private Peca peca;
    private int linhaInicial;
    private char colunaInicial;
    private int linhaFinal;
    private char colunaFinal;
    private Caminho caminho;

    public Jogada(Jogador jogador, Peca peca, int linhaInicial, char colunaInicial, int linhaFinal, char colunaFinal, Tabuleiro tabuleiro) {
        this.jogador = jogador;
        this.peca = peca;
        this.linhaInicial = linhaInicial;
        this.colunaInicial = colunaInicial;
        this.linhaFinal = linhaFinal;
        this.colunaFinal = colunaFinal;
        this.caminho = new Caminho(tabuleiro, peca.caminho(linhaInicial, colunaInicial, linhaFinal, colunaFinal));
    }

    public boolean ehValida(Tabuleiro tabuleiro) {
        if (!tabuleiro.noLimite(linhaInicial, colunaInicial) || !tabuleiro.noLimite(linhaFinal, colunaFinal)) {
            return false;
        }

        Casa casaInicial = tabuleiro.getCasa(linhaInicial, colunaInicial);
        Casa casaFinal = tabuleiro.getCasa(linhaFinal, colunaFinal);

        if (!casaInicial.estaOcupada() || casaInicial.getPeca().isBranca() != jogador.getPecas().get(0).isBranca()) {
            return false;
        }

        if (casaFinal.estaOcupada() && casaFinal.getPeca().isBranca() == jogador.getPecas().get(0).isBranca()) {
            return false;
        }
       
        if (!caminho.estaLivre()) {
            return false;
        }
        
        if (peca instanceof Peao) {
            boolean movimentoPeao = peca.movimentoValido(linhaInicial, colunaInicial, linhaFinal, colunaFinal);
            if (movimentoPeao) {
                if (colunaFinal != colunaInicial) {
                    if(casaFinal.estaOcupada()){
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if(casaFinal.estaOcupada()) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } else {
                return false;
            }

        } else {
           
            return peca.movimentoValido(linhaInicial, colunaInicial, linhaFinal, colunaFinal);
        }
    }

    public boolean ehXeque() {
        // Implementar lógica para verificar xeque
        return false;
    }

    public boolean ehXequeMate() {
        // Implementar lógica para verificar xeque mate
        return false;
    }
}
