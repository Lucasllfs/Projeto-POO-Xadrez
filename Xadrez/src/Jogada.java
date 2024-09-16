// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
import Pecas.Peca;
import Pecas.Rei;
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


    public boolean ehXeque(Tabuleiro tabuleiro, Jogador jogadorOponente) {
        // Localiza o rei do oponente no tabuleiro
        Peca reiOponente = null;
        
        for (int linha = 1; linha <= 8; linha++) {
            for (char coluna = 'A'; coluna <= 'H'; coluna++) {
                Peca peca = tabuleiro.getCasa(linha, coluna).getPeca();
                if (peca instanceof Rei && peca.isBranca() == jogadorOponente.getPecas().get(0).isBranca()) {
                    reiOponente = peca;
                    
                    break;
                }
            }
            if (reiOponente != null) break;
        }
    
        if (reiOponente == null) return false; // Se não há rei (erro)
    
        // Verifica se alguma peça do jogador atual pode capturar o rei do oponente
        for (int linha = 1; linha <= 8; linha++) {
            for (char coluna = 'A'; coluna <= 'H'; coluna++) {
                Peca peca = tabuleiro.getCasa(linha, coluna).getPeca();
                if (peca != null && peca.isBranca() != jogadorOponente.getPecas().get(0).isBranca()) {
                    if (peca.movimentoValido(linha, coluna, tabuleiro.localizaPeca(reiOponente).getLinha(), tabuleiro.localizaPeca(reiOponente).getColuna())) {
                        return true; // O rei está sob ataque
                    }
                }
            }
        }
    
        return false; // O rei não está sob ataque
    }
    



    public boolean ehXequeMate(Tabuleiro tabuleiro, Jogador jogadorOponente) {
        if (!ehXeque(tabuleiro, jogadorOponente)) {
            return false; // Se não está em xeque, não pode ser xeque-mate
        }
    
        Peca reiOponente = null;
    
        // Localiza o rei do oponente
        for (int linha = 1; linha <= 8; linha++) {
            for (char coluna = 'A'; coluna <= 'H'; coluna++) {
                Peca peca = tabuleiro.getCasa(linha, coluna).getPeca();
                if (peca instanceof Rei && peca.isBranca() == jogadorOponente.getPecas().get(0).isBranca()) {
                    reiOponente = peca;
                    break;
                }
            }
            if (reiOponente != null) break;
        }
    
        if (reiOponente == null) return false;
    
        int linhaRei =  tabuleiro.localizaPeca(reiOponente).getLinha();
        char colunaRei =  tabuleiro.localizaPeca(reiOponente).getColuna();
    
        // Testa todos os movimentos possíveis do rei
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int novaLinha = linhaRei + i;
                char novaColuna = (char) (colunaRei + j);
    
                if (tabuleiro.noLimite(novaLinha, novaColuna)) {
                    // Se o movimento é válido e não deixa o rei em xeque, não é xeque-mate
                    if (reiOponente.movimentoValido(linhaRei, colunaRei, novaLinha, novaColuna)
                        && !ehXeque(tabuleiro, jogadorOponente)) {
                        return false;
                    }
                }
            }
        }
    
        return true; // Se nenhum movimento é possível sem xeque, é xeque-mate
    }
    
}
