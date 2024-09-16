import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Pecas.Peca;
import Pecas.Peao;
import Pecas.Torre;
import Pecas.Cavalo;
import Pecas.Bispo;
import Pecas.Dama;
import Pecas.Rei;


public class Jogo {
    private Tabuleiro tabuleiro;
    protected Jogador jogador1;
    protected Jogador jogador2;
    protected Jogador jogadorAtual;
    private List<String> historicoJogadas;
    private boolean jogoAtivo;

    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.historicoJogadas = new ArrayList<>();
        this.jogoAtivo = true;
    }

    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do Jogador 1: ");
        String nomeJogador1 = scanner.nextLine();
        System.out.println("Digite o nome do Jogador 2: ");
        String nomeJogador2 = scanner.nextLine();

        jogador1 = new Jogador(nomeJogador1, inicializarPecas(true));
        jogador2 = new Jogador(nomeJogador2, inicializarPecas(false));
        jogadorAtual = jogador1;  // Jogador 1 começa

        // Loop principal do jogo
        while (jogoAtivo) {
            mostrarTabuleiro();
            String jogada = jogadorAtual.informaJogada();
            if (jogada.equalsIgnoreCase("parar")) {
                encerrarJogo();
                break;
            }

            if (processarJogada(jogada)) {
                alternarJogador();
            }
        }
    }

    public void iniciarJogoArmazenado() {
    

        // Loop principal do jogo
        while (jogoAtivo) {
            mostrarTabuleiro();
            String jogada = jogadorAtual.informaJogada();
            if (jogada.equalsIgnoreCase("parar")) {
                encerrarJogo();
                break;
            }

            if (processarJogada(jogada)) {
                

                alternarJogador();
            }
        }
    }

    // private void verificaXeque(Jogada jogada ) {
    //     // Implementar lógica para verificar se o rei do jogador atual está em xeque
    //     if (jogada.ehXeque(tabuleiro, jogadorAtual)) {
    //         System.out.println("Xeque no " + (jogadorAtual == jogador1 ? "branco" : "preto"));

    //         if (jogada.ehXequeMate(tabuleiro, jogadorAtual)) {
    //             System.out.println("Xeque-mate! " + (jogadorAtual == jogador1 ? "Branco" : "Preto") + " venceu!");
    //             break; // Termina o jogo
    //         }
    //     }
    // }





    protected List<Peca> inicializarPecas(boolean ehBranca) {
        // Inicializa as peças do jogador
        List<Peca> pecas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            pecas.add(new Peao(ehBranca));  // Adiciona peões, por exemplo
        }
        // Adicionar outras peças (torres, cavalos, bispos, etc.) conforme necessário
        for (int i = 0; i < 2; i++) {
            pecas.add(new Torre(ehBranca));
            pecas.add(new Cavalo(ehBranca));
            pecas.add(new Bispo(ehBranca));
            pecas.add(new Dama(ehBranca));
            pecas.add(new Rei(ehBranca));
        }

        
        return pecas;
    }

    public boolean jogadaValida(int linhaO, char colunaO, int linhaD, char colunaD) {
        Casa casaOrigem = tabuleiro.getCasa(linhaO, colunaO);
        if (casaOrigem == null || !casaOrigem.estaOcupada()) {
            return false;
        }

        Peca peca = casaOrigem.getPeca();
        if (!peca.movimentoValido(linhaO, colunaO, linhaD, colunaD)) {
            return false;
        }

        Jogada jogada = new Jogada(jogadorAtual, peca, linhaO, colunaO, linhaD, colunaD, tabuleiro);

        if (jogada.ehXeque(tabuleiro, jogadorAtual)) {
            System.out.println("Xeque no " + (jogadorAtual == jogador1 ? "branco" : "preto"));

            if (jogada.ehXequeMate(tabuleiro, jogadorAtual)) {
                System.out.println("Xeque-mate! " + (jogadorAtual == jogador1 ? "Branco" : "Preto") + " venceu!");

                encerrarJogo();
            }
        }

        return jogada.ehValida(tabuleiro);
    }

    public void realizarJogada(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (jogadaValida(linhaO, colunaO, linhaD, colunaD)) {
            Casa casaOrigem = tabuleiro.getCasa(linhaO, colunaO);
            Casa casaDestino = tabuleiro.getCasa(linhaD, colunaD);
            Peca pecaMovida = casaOrigem.getPeca();

            if (casaDestino.estaOcupada()) {
                Peca pecaCapturada = casaDestino.getPeca();
                if (pecaCapturada.isBranca() != pecaMovida.isBranca()) {
                    //System.out.println("Peça capturada: " + pecaCapturada);
                    jogadorAtual.capturarPeca(pecaCapturada);
                }
            }

            // Atualiza o tabuleiro
            casaDestino.setPeca(pecaMovida);
            casaOrigem.setPeca(null);

            historicoJogadas.add("" + linhaO + colunaO + linhaD + colunaD);



           // mostrarTabuleiro();
        } else {
            System.out.println("Jogada inválida.");
        }
    }

    public String registroJogo() {
        StringBuilder registro = new StringBuilder();
        registro.append(jogador1.getNome()).append("\n");
        registro.append(jogador2.getNome()).append("\n");
        for (String jogada : historicoJogadas) {
            registro.append(jogada).append("\n");
        }
        return registro.toString();
    }

    protected void alternarJogador() {
        jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
    }

    public boolean processarJogada(String jogada) {
        if (jogada.length() != 4) {
            System.out.println("Formato de jogada inválido.");
            return false;
        }

        int linhaO = jogada.charAt(0) - '0';
        char colunaO = jogada.charAt(1);
        int linhaD = jogada.charAt(2) - '0';
        char colunaD = jogada.charAt(3);

        realizarJogada(linhaO, colunaO, linhaD, colunaD);



        return true;
    }

    private void mostrarTabuleiro() {
        System.out.println(tabuleiro.desenho());
        //Verificar se está aparecendo certo
        System.out.println("Capturadas pelo " + jogador1.getNome() + ": " + jogador1.pecasCapturadas());
        System.out.println("Capturadas pelo " + jogador2.getNome() + ": " + jogador2.pecasCapturadas());
    }


    public void setJogadores(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogadorAtual = jogador1;  // Começa sempre com o jogador 1, ou pode-se usar alguma lógica para decidir de quem é a vez.
    }

    private void encerrarJogo() {
        System.out.println("Jogo encerrado.");
        this.jogoAtivo = false;
    }
}
