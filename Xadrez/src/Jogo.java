
// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        this.jogoAtivo = true;
        Scanner scanner = new Scanner(System.in);
        String nomeJogador1 = null;
        String nomeJogador2 = null;

        // Solicitar nome do jogador 1 até ser válido
        while (nomeJogador1 == null || nomeJogador1.trim().isEmpty()) {
            try {
                System.out.println("Digite o nome do Jogador 1: ");
                nomeJogador1 = scanner.nextLine();
                if (nomeJogador1 == null || nomeJogador1.trim().isEmpty()) {
                    throw new IllegalArgumentException("Nome do jogador 1 inválido. Por favor, insira um nome válido.");
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        // Solicitar nome do jogador 2 até ser válido.
        while (nomeJogador2 == null || nomeJogador2.trim().isEmpty()) {
            try {
                System.out.println("Digite o nome do Jogador 2: ");
                nomeJogador2 = scanner.nextLine();
                if (nomeJogador2 == null || nomeJogador2.trim().isEmpty()) {
                    throw new IllegalArgumentException("Nome do jogador 2 inválido. Por favor, insira um nome válido.");
                }

            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }

        jogador1 = new Jogador(nomeJogador1, inicializarPecas(true));
        jogador2 = new Jogador(nomeJogador2, inicializarPecas(false));
        jogadorAtual = jogador1; // Jogador 1 começa

        // iniciar outro tabuleiro
        tabuleiro = new Tabuleiro();

        // Loop principal do jogo
        while (jogoAtivo) {
            mostrarTabuleiro();
            String jogada = null;
            boolean entradaValida = false;
            while (!entradaValida) {
                try {
                    jogada = jogadorAtual.informaJogada();
                    if (jogada.equalsIgnoreCase("parar")) {
                        encerrarJogo();
                        return;
                    }
                    // Verifica se a jogada está no formato 1a3b e também processa a jogada
                    entradaValida = validarCaracteresJogada(jogada) && processarJogada(jogada);
                    if (entradaValida)
                        alternarJogador();
                } catch (Exception e) {
                    System.err.println("Erro ao processar a jogada: " + e.getMessage());
                }
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

    private boolean validarCaracteresJogada(String jogada) {
        // Verifica se a jogada é do tamanho correto, e se está no formato "1a3b"
        if (jogada.length() != 4) {
            System.out.println("Formato de jogada inválido: tamanho diferente do que o esperado");
            return false;
        }

        String regex = "^[1-8][a-h][1-8][a-h]$";

        boolean match = Pattern.matches(regex, jogada);
        if (!match) {
            System.out.println("Formato de jogada inválido: use o formato '1a3b'.");
        }
        return match;
    }

    protected List<Peca> inicializarPecas(boolean ehBranca) {
        // Inicializa as peças do jogador
        List<Peca> pecas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            pecas.add(new Peao(ehBranca)); // Adiciona peões
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

        Jogada jogada = new Jogada(jogadorAtual, peca, linhaO, colunaO, linhaD, colunaD, tabuleiro);
        Jogador jogadorOponente = (jogadorAtual == jogador1) ? jogador2 : jogador1;

        if (jogada.ehXeque(peca, linhaD, colunaD, tabuleiro, jogadorOponente)) {
            System.out.println("Xeque no " + (jogadorAtual != jogador1 ? "branco" : "preto"));

            if (jogada.ehXequeMate(peca, linhaD, colunaD, tabuleiro, jogadorOponente, jogadorAtual)) {
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
                    jogadorAtual.capturarPeca(pecaCapturada);
                }
            }

            // Atualiza o tabuleiro
            casaDestino.setPeca(pecaMovida);
            casaOrigem.setPeca(null);

            historicoJogadas.add("" + linhaO + colunaO + linhaD + colunaD);

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

    void mostrarTabuleiro() {
        System.out.println("\n");
        System.out.println("Capturadas pelo " + jogador2.getNome() + ": " + jogador2.pecasCapturadas());
        System.out.println(tabuleiro.desenho());
        System.out.println("Capturadas pelo " + jogador1.getNome() + ": " + jogador1.pecasCapturadas());
        System.out.println("\n");

    }

    public void setJogadores(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogadorAtual = jogador1; // Começa sempre com o jogador 1
    }

    public void encerrarJogo() {
        System.out.println("Jogo encerrado.");
        this.jogoAtivo = false;
    }
}
