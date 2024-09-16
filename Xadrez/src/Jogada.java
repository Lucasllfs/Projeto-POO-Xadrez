
// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
import Pecas.Peca;
import Pecas.Rei;
import Pecas.Peao;

public class Jogada {
    private Jogador jogador;
    private Peca peca;
    private int linhaInicial;
    private char colunaInicial;
    private int linhaFinal;
    private char colunaFinal;
    private Caminho caminho;

    public Jogada(Jogador jogador, Peca peca, int linhaInicial, char colunaInicial, int linhaFinal, char colunaFinal,
            Tabuleiro tabuleiro) {
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
                    if (casaFinal.estaOcupada()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (casaFinal.estaOcupada()) {
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

    public boolean ehXeque(Peca pecaAlterada, int linhaFinal, char colunaFinal, Tabuleiro tabuleiro, Jogador jogadorOponente) {
        // Localiza o rei do oponente
        Peca reiOponente = null;

        for (Peca peca : jogadorOponente.getPecas()) {
            if (peca instanceof Rei) {
                reiOponente = peca;
                break;
            }
        }

        if (reiOponente == null) {
            return false;
        } // Não encontrou o rei

        // Localiza a posição do rei no tabuleiro
        Casa casaRei = tabuleiro.localizaPeca(reiOponente);

        if (casaRei == null) {
            throw new IllegalStateException("O Rei do oponente não foi encontrado no tabuleiro.");
        }

        int linhaRei = casaRei.getLinha();
        char colunaRei = casaRei.getColuna();


        if(pecaAlterada.movimentoValido(linhaFinal, colunaFinal, linhaRei, colunaRei)){
            return true;
        } else {
            return false;
        }

    }

    public boolean ehXequeMate(Peca pecaAlterada, int linhaFinal, char colunaFinal, Tabuleiro tabuleiro, Jogador jogadorOponente, Jogador jogadorAtual) {

        Peca reiOponente = null;

        for (Peca peca : jogadorOponente.getPecas()) {
            if (peca instanceof Rei) {
                reiOponente = peca;
                break;
            }
        }

        if (reiOponente == null) {
            return false;
        } // Não encontrou o rei

        // Localiza a posição do rei no tabuleiro
        Casa casaRei = tabuleiro.localizaPeca(reiOponente);

        if (casaRei == null) {
            throw new IllegalStateException("O Rei do oponente não foi encontrado no tabuleiro.");
        }

        int linhaRei = casaRei.getLinha();
        char colunaRei = casaRei.getColuna();
  int[] movimentosLinha = {-1, -1, -1, 0, 0, 1, 1, 1};
  int[] movimentosColuna = {-1, 0, 1, -1, 1, -1, 0, 1};

  for (int i = 0; i < movimentosLinha.length; i++) {
      int novaLinha = linhaRei + movimentosLinha[i];
      char novaColuna = (char) (colunaRei + movimentosColuna[i]);

      if (tabuleiro.noLimite(novaLinha, novaColuna) && reiOponente.movimentoValido(linhaRei, colunaRei, novaLinha, novaColuna)) {
          boolean emXeque = false;

          for (Peca peca : jogadorAtual.getPecas()) {
              if (ehXeque(peca, novaLinha, novaColuna, tabuleiro, jogadorOponente)) {
                  emXeque = true;
                  break;
              }
          }

          if (!emXeque) {
              return false; // O rei pode se mover para uma posição onde não está em xeque
          }
      }
  }

  return true; //

        // for (Peca peca : jogadorAtual.getPecas()) {
        //     for (int linha = 1; linha <= 8; linha++) {
        //         for (char coluna = 'a'; coluna <= 'h'; coluna++) {
        //             if (peca.movimentoValido(pecaAlterada.getLinha(), pecaAlterada.getColuna(), linha, coluna)) {
        //                 if (ehValida(tabuleiro)) {
        //                     return false;
        //                 }
        //             }
        //         }
        //     }
        // }

    }

}
