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
        // Localiza o rei do oponente
        Peca reiOponente = null;
        
        for (Peca peca : jogadorOponente.getPecas()) {
            if (peca instanceof Rei) {
                reiOponente = peca;
                break;
            }
        }
    
        if (reiOponente == null){ 
            return false;} // Não encontrou o rei
    
        // Localiza a posição do rei no tabuleiro
        Casa casaRei = tabuleiro.localizaPeca(reiOponente);
        
        if (casaRei == null) {
            throw new IllegalStateException("O Rei do oponente não foi encontrado no tabuleiro.");
        }
    
        int linhaRei = casaRei.getLinha();
        char colunaRei = casaRei.getColuna();
    
        // Verifica se alguma peça do jogador atual pode capturar o rei do oponente
        for (int linha = 1; linha <= 8; linha++) {
            for (char coluna = 'A'; coluna <= 'H'; coluna++) {
                Casa casa = tabuleiro.getCasa(linha, coluna);
                if (casa != null) {
                    Peca peca = casa.getPeca();
                    if (peca != null && peca.isBranca() != reiOponente.isBranca()) {
                        if (peca.movimentoValido(linha, coluna, linhaRei, colunaRei)) {
                            return true; // O rei está sob ataque
                        }
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
    
        // Localiza o rei do oponente
        Peca reiOponente = null;
        for (Peca peca : jogadorOponente.getPecas()) {
            if (peca instanceof Rei) {
                reiOponente = peca;
                break;
            }
        }
    
        if (reiOponente == null) return false; // Não encontrou o rei
    
        int linhaRei = tabuleiro.localizaPeca(reiOponente).getLinha();
        char colunaRei = tabuleiro.localizaPeca(reiOponente).getColuna();
    
        // Testa todos os movimentos possíveis ao redor do rei
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int novaLinha = linhaRei + i;
                char novaColuna = (char) (colunaRei + j);
    
                if (tabuleiro.noLimite(novaLinha, novaColuna)) {
                    // Testa se o movimento é válido
                    if (reiOponente.movimentoValido(linhaRei, colunaRei, novaLinha, novaColuna)) {
                        // Simula o movimento do rei
                        Casa casaOriginal = tabuleiro.getCasa(linhaRei, colunaRei);
                        Casa casaDestino = tabuleiro.getCasa(novaLinha, novaColuna);
                        Peca pecaOriginal = casaDestino.getPeca(); // Salva a peça que estava na casa destino
    
                        casaDestino.setPeca(reiOponente); // Move temporariamente o rei
                        casaOriginal.setPeca(null);
    
                        // Verifica se o rei ainda está em xeque após o movimento
                        boolean aindaEmXeque = ehXeque(tabuleiro, jogadorOponente);
    
                        // Desfaz o movimento
                        casaOriginal.setPeca(reiOponente);
                        casaDestino.setPeca(pecaOriginal);
    
                        if (!aindaEmXeque) {
                            return false; // Se o movimento retira o xeque, não é xeque-mate
                        }
                    }
                }
            }
        }
    
        return true; // Se nenhum movimento possível tira o xeque, é xeque-mate
    }
    
    
}
