// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
import java.sql.SQLOutput;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Pecas.Peca;
import Pecas.Bispo;
import Pecas.Cavalo;
import Pecas.Dama;
import Pecas.Peao;
import Pecas.Rei;
import Pecas.Torre;



//main nao implementada
//vai estar dentro do gerenciador
public class Gerenciador {
    private Jogo jogo;
    private Scanner scanner;

    public Gerenciador() {
        this.jogo = new Jogo();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        //Intecalar os comentarios para testar o executar ;)
        Gerenciador gerenciador = new Gerenciador();
        gerenciador.menuPrincipal();

        //testes();
    }

    public void menuPrincipal() {
        System.out.println("Bem-vindo ao jogo de tabuleiro!");
        System.out.println("1. Iniciar novo jogo");
        System.out.println("2. Carregar jogo");
        System.out.println("3. Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        switch (opcao) {
            case 1:
                iniciarNovoJogo();
                break;
            case 2:
                carregarJogo();
                break;
            case 3:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void iniciarNovoJogo() {
        jogo.iniciarJogo();
        //perguntar se deseja salvar se nao apenas encerra
        System.out.println("Deseja salvar o jogo? (Sim/Nao)");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("Sim")) {
            salvarJogo();
        } else {
            System.out.println("Deseja iniciar outro jogo? (Sim/Nao)");
            String resposta2 = scanner.nextLine();
            if (resposta2.equalsIgnoreCase("Sim")) {
                
                iniciarNovoJogo();
            } else {
                System.out.println("Jogo não salvo.");
            }
        }
    
    }

    private void carregarJogo() {
        System.out.println("Digite o nome do arquivo para carregar o jogo:");
        String nomeArquivo = scanner.nextLine();
    
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            // Leitura dos nomes dos jogadores
            String nomeJogador1 = leitor.readLine();
            String nomeJogador2 = leitor.readLine();

            System.out.println("nomeJogador1: " + nomeJogador1);
            System.out.println("nomeJogador2: " + nomeJogador2);
    
            // Inicializa o jogo e recria os jogadores com suas peças
            jogo = new Jogo();
            jogo.jogador1 = new Jogador(nomeJogador1, jogo.inicializarPecas(true));  // Peças brancas
            jogo.jogador2 = new Jogador(nomeJogador2, jogo.inicializarPecas(false));  // Peças pretas
            jogo.jogadorAtual = jogo.jogador1;  // Supondo que o jogador 1 começa
    
            // Processa as jogadas do arquivo
            String jogada;
            while ((jogada = leitor.readLine()) != null) {
                System.out.println("Jogada: " + jogada);
                jogo.processarJogada(jogada);
               
                jogo.alternarJogador();
            }
    
            // Inicia o jogo após o carregamento
            jogo.iniciarJogoArmazenado();

            System.out.println("Deseja salvar o jogo? (Sim/Nao)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("Sim")) {
                salvarJogo();
            } else {
                System.out.println("Deseja iniciar outro jogo? (Sim/Nao)");
            String resposta2 = scanner.nextLine();
            if (resposta2.equalsIgnoreCase("Sim")) {
                iniciarNovoJogo();
            } else {
                System.out.println("Jogo não salvo.");
            }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
            carregarJogo();
        }
    }
    
    

    private void salvarJogo() {
        System.out.println("Digite o nome do arquivo para salvar o jogo:");
        String nomeArquivo = scanner.nextLine();

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {
            escritor.write(jogo.registroJogo());
            System.out.println("Jogo salvo com sucesso.");
            System.out.println("Deseja iniciar outro jogo? (Sim/Nao)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("Sim")) {
                iniciarNovoJogo();
            } 



        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    private static void testes() {
        //testarBispo();
        //testarCavalo();
        //testarDama();
        //testarPeao();
        //testarRei();
        //estarTorre();
        //testarCasa();
        //testarTabuleiro();
        //testarCaminho();
        //testarJogador();
        //testarJogada();
        testarJogo();
    }
    

    private static void testarBispo() {
        System.out.println("Testando Bispo...");
        Bispo bispoBranco = new Bispo(true);

        System.out.println("Testes dos métodos padrão de Peca:");
        System.out.println("É Branco (esperado true): " + bispoBranco.isBranca());
        System.out.println("Está capturada (esperado false): " + bispoBranco.capturada());
        bispoBranco.setCapturada(true);
        System.out.println("Está capturada (após setCapturada(true)): " + bispoBranco.capturada());
        
        System.out.println("Desenho (esperado 'B'): " + bispoBranco.desenho());
        System.out.println("Movimento válido (esperado true): " + bispoBranco.movimentoValido(2, 'a', 4, 'c'));
        System.out.println("Movimento inválido (esperado false): " + bispoBranco.movimentoValido(2, 'a', 5, 'b'));
        System.out.println("Caminho (esperado '1a2b3c4d' para mover de (1,a) a (4,d)): " + bispoBranco.caminho(1, 'a', 4, 'd'));
        System.out.println("Caminho (esperado '' para movimento inválido): " + bispoBranco.caminho(1, 'a', 4, 'b'));
        System.out.println();
    }

    private static void testarCavalo() {
        System.out.println("Testando Cavalo...");
        Cavalo cavaloPreto = new Cavalo(false);

        System.out.println("Desenho (esperado 'c'): " + cavaloPreto.desenho());
        System.out.println("Movimento válido (esperado true): " + cavaloPreto.movimentoValido(1, 'b', 3, 'c'));
        System.out.println("Movimento inválido (esperado false): " + cavaloPreto.movimentoValido(2, 'a', 3, 'b'));
        System.out.println("Caminho (esperado '1b3c' para mover de (1,b) a (3,c)): " + cavaloPreto.caminho(1, 'b', 3, 'c'));
        System.out.println("Caminho (esperado '' para movimento inválido): " + cavaloPreto.caminho(2, 'a', 3, 'b'));
        System.out.println();
    }

    private static void testarDama() {
        System.out.println("Testando Dama...");
        Dama damaBranca = new Dama(true);

        System.out.println("Desenho (esperado 'D'): " + damaBranca.desenho());
        System.out.println("Movimento válido (esperado true): " + damaBranca.movimentoValido(1, 'a', 3, 'c'));
        System.out.println("Movimento inválido (esperado false): " + damaBranca.movimentoValido(1, 'a', 2, 'c'));
        System.out.println("Caminho (esperado '1a2b3c' para mover de (1,a) a (3,c)): " + damaBranca.caminho(1, 'a', 3, 'c'));
        System.out.println("Caminho (esperado '1a1b1c' para mover de (1,a) a (1,c)): " + damaBranca.caminho(1, 'a', 1, 'c'));
        System.out.println("Caminho (esperado '1d2d3d' para mover de (1,a) a (1,c)): " + damaBranca.caminho(1, 'd', 3, 'd'));
        System.out.println("Caminho (esperado '' para movimento inválido): " + damaBranca.caminho(1, 'a', 2, 'c'));
        System.out.println();
    }

    private static void testarPeao() {
        System.out.println("Testando Peão...");
        Peao peaoBranco = new Peao(true);

        System.out.println("Desenho (esperado 'P'): " + peaoBranco.desenho());
        System.out.println("Movimento válido (esperado true): " + peaoBranco.movimentoValido(2, 'a', 3, 'a'));
        System.out.println("Movimento inválido (esperado false): " + peaoBranco.movimentoValido(1, 'a', 3, 'b'));
        System.out.println("Caminho (esperado '2a3a' para mover de (2,a) a (3,a)): " + peaoBranco.caminho(2, 'a', 3, 'a'));
        System.out.println("Caminho (esperado '2a3a4a' para mover de (2,a) a (4,a)): " + peaoBranco.caminho(2, 'a', 4, 'a'));
        System.out.println("Caminho (esperado '' para movimento inválido): " + peaoBranco.caminho(1, 'a', 3, 'b'));
        System.out.println();
    }

    private static void testarRei() {
        System.out.println("Testando Rei...");
        Rei reiPreto = new Rei(false);

        System.out.println("Desenho (esperado 'r'): " + reiPreto.desenho());
        System.out.println("Movimento válido (esperado true): " + reiPreto.movimentoValido(2, 'd', 3, 'e'));
        System.out.println("Movimento inválido (esperado false): " + reiPreto.movimentoValido(4, 'd', 6, 'f'));
        System.out.println("Caminho (esperado '2d3e' para mover de (2,d) a (3,e)): " + reiPreto.caminho(2, 'd', 3, 'e'));
        System.out.println("Caminho (esperado '' para movimento inválido): " + reiPreto.caminho(4, 'd', 6, 'f'));
        System.out.println();
    }

    private static void testarTorre() {
        System.out.println("Testando Torre...");
        Torre torrePreta = new Torre(false);

        System.out.println("Desenho (esperado 't'): " + torrePreta.desenho());
        System.out.println("Movimento válido (esperado true): " + torrePreta.movimentoValido(1, 'a', 1, 'c'));
        System.out.println("Movimento inválido (esperado false): " + torrePreta.movimentoValido(1, 'a', 5, 'f'));
        System.out.println("Caminho (esperado '1a1b1c' para mover de (1,a) a (1,c)): " + torrePreta.caminho(1, 'a', 1, 'c'));
        System.out.println("Caminho (esperado '' para movimento inválido): " + torrePreta.caminho(1, 'a', 5, 'f'));
        System.out.println();
    }
    
    private static void testarCasa() {
        System.out.println("Testando Casa...");
        Casa casaBranca = new Casa("branco", 2, 'a');
        Casa casaPreta = new Casa("preto", 7, 'h');
        Peca peaoBranco = new Peao(true);
        Peca peaoPreto = new Peao(false);

        // Teste getCor
        System.out.println("Teste getCor:");
        System.out.println("Esperado: branco, obtido: " + casaBranca.getCor());
        System.out.println("Esperado: preto, obtido: " + casaPreta.getCor());

        // Teste getLinha
        System.out.println("\nTeste getLinha:");
        System.out.println("Esperado: 2, obtido: " + casaBranca.getLinha());
        System.out.println("Esperado: 7, obtido: " + casaPreta.getLinha());

        // Teste getColuna
        System.out.println("\nTeste getColuna:");
        System.out.println("Esperado: a, obtido: " + casaBranca.getColuna());
        System.out.println("Esperado: h, obtido: " + casaPreta.getColuna());

        // Teste setPeca, getPeca, estaOcupada e toString
        //Antes de definir peca
        System.out.println("\nTeste getPeca:");
        System.out.println("Esperado: null, obtido: " + casaBranca.getPeca());
        System.out.println("Esperado: null, obtido: " + casaPreta.getPeca());
        System.out.println("Teste estaOcupada:");
        System.out.println("Esperado: false, obtido: " + casaBranca.estaOcupada());
        System.out.println("Esperado: false, obtido: " + casaPreta.estaOcupada());
        System.out.println("Teste toString:");
        System.out.println("Esperado: □, obtido: " + casaBranca.toString());
        System.out.println("Esperado: ■, obtido: " + casaPreta.toString());

        casaBranca.setPeca(peaoBranco);
        casaPreta.setPeca(peaoPreto);

        System.out.println("\nApós definir peças:");
        System.out.println("Esperado: true, obtido: " + casaBranca.estaOcupada());
        System.out.println("Esperado: true, obtido: " + casaPreta.estaOcupada());
        System.out.println("Esperado: " + peaoBranco.desenho() + ", obtido: " + casaBranca.toString());
        System.out.println("Esperado: " + peaoPreto.desenho() + ", obtido: " + casaPreta.toString());
    }
    
    private static void testarTabuleiro() {
        //TESTE TABULEIRO
        System.out.println("\nTestando Tabuleiro...");
        Tabuleiro tabuleiro = new Tabuleiro();
        System.out.println(tabuleiro.desenho());
        // Testes para o método noLimite
        System.out.println("Testes para noLimite:");
        System.out.println("Linha 1, coluna 'a', Esperado: true, Obtido: " + tabuleiro.noLimite(1, 'a'));  
        System.out.println("Linha 9, coluna 'a', Esperado: false, Obtido: " + tabuleiro.noLimite(9, 'a')); 
        System.out.println("Linha 1, coluna 'i', Esperado: false, Obtido: " + tabuleiro.noLimite(1, 'i'));  
        System.out.println("Linha -1, coluna 'z', Esperado: false, Obtido: " + tabuleiro.noLimite(-1, 'z'));  

        // Testes para o método getCasa
        System.out.println("\nTestes para getCasa:");
        System.out.println("Casa em linha 2, coluna 'a', Esperado: P, Obtido: " + tabuleiro.getCasa(2, 'a').getPeca().desenho()); // Deve exibir P
        System.out.println("Casa em linha 1, coluna 'b', Esperado: C, Obtido: " + tabuleiro.getCasa(1, 'b').getPeca().desenho()); // Deve exibir C
        System.out.println("Casa em linha 8, coluna 'h', Esperado: t, Obtido: " + tabuleiro.getCasa(8, 'h').getPeca().desenho()); // Deve exibir t
        System.out.println("Casa em linha 9, coluna 'a', Esperado: null, Obtido: " + tabuleiro.getCasa(9, 'a')); // Deve ser null
        System.out.println("Casa em linha 1, coluna 'i', Esperado: null, Obtido: " + tabuleiro.getCasa(1, 'i')); // Deve ser null
        System.out.println("Casa em linha -1, coluna 'z', Esperado: null, Obtido: " + tabuleiro.getCasa(-1, 'z')); // Deve ser null
    }

    
    
    
    
    private static void testarCaminho() {
        System.out.println("\nTestando Caminho...");
        Tabuleiro t = new Tabuleiro();
        /*System.out.println("Testes para método privado ConstruirSequenciaCasas:\n (mudar temporariamente para tipo public Casa[]):");

        Caminho caminho1 = new Caminho(t, "2a3a4a");
        Casa[] sequencia1 = caminho1.construirSequenciaCasas("2a3a4a");
        System.out.print("Teste 1 (caminho vertical) - Esperado: 2a 3a 4a, Obtido: ");
        for (Casa casa : sequencia1) {
            System.out.print(casa.getLinha() + "" + casa.getColuna() + " ");
        }
        System.out.println();

        Caminho caminho2 = new Caminho(t, "1b2c3d");
        Casa[] sequencia2 = caminho2.construirSequenciaCasas("1b2c3d");
        System.out.print("Teste 2 (caminho diagonal) - Esperado: 1b 2c 3d, Obtido: ");
        for (Casa casa : sequencia2) {
            System.out.print(casa.getLinha() + "" + casa.getColuna() + " ");
        }
        System.out.println();

        Caminho caminho3 = new Caminho(t, "2b2c2d");
        Casa[] sequencia3 = caminho3.construirSequenciaCasas("2b2c2d");
        System.out.print("Teste 3 (caminho horizontal) - Esperado: 2b 2c 2d, Obtido: ");
        for (Casa casa : sequencia3) {
            System.out.print(casa.getLinha() + "" + casa.getColuna() + " ");
        }
        System.out.println();
        
        Caminho caminho18 = new Caminho(t, "");
        Casa[] sequencia4 = caminho18.construirSequenciaCasas("");
        System.out.print("Teste 3 (recebe string vazia) - Esperado: , Obtido: ");
        for (Casa casa : sequencia4) {
            System.out.print(casa.getLinha() + "" + casa.getColuna() + " ");
        }
        System.out.println();
*/

        System.out.println("\nTestes do método estaLivre:");
        Caminho caminho0 = new Caminho(t, "1b3c");
        System.out.println("Teste 0 (cavalo pula peça, sempre tem caminho livre) - Esperado: true, Obtido: " + caminho0.estaLivre());

        Caminho caminho4 = new Caminho(t, "2a3a4a");
        System.out.println("Teste 1 (caminho vertical) - Esperado: true, Obtido: " + caminho4.estaLivre());

        Caminho caminho5 = new Caminho(t, "1a2a3a");
        System.out.println("Teste 2 (caminho vertical) - Esperado: false, Obtido: " + caminho5.estaLivre());

        Caminho caminho6 = new Caminho(t, "3d4e5f");
        System.out.println("Teste 3 (caminho diagonal) - Esperado: true, Obtido: " + caminho6.estaLivre());

        Caminho caminho7 = new Caminho(t, "1a2b3c");
        System.out.println("Teste 4 (caminho diagonal) - Esperado: false, Obtido: " + caminho7.estaLivre());

        Caminho caminho8 = new Caminho(t, "3b3c3d");
        System.out.println("Teste 5 (caminho horizontal) - Esperado: true, Obtido: " + caminho8.estaLivre());

        Caminho caminho9 = new Caminho(t, "1b1c1d");
        System.out.println("Teste 6 (caminho vertical) - Esperado: false, Obtido: " + caminho9.estaLivre());

        Caminho caminho10 = new Caminho(t, "7a6a5a");
        System.out.println("Teste 7 (caminho vertical) - Esperado: true, Obtido: " + caminho10.estaLivre());

        Caminho caminho11 = new Caminho(t, "8a7a6a");
        System.out.println("Teste 8 (caminho vertical) - Esperado: false, Obtido: " + caminho11.estaLivre());

        Caminho caminho12 = new Caminho(t, "7b6c5d");
        System.out.println("Teste 9 (caminho diagonal) - Esperado: true, Obtido: " + caminho12.estaLivre());

        Caminho caminho13 = new Caminho(t, "8a7b6c");
        System.out.println("Teste 10 (caminho diagonal) - Esperado: false, Obtido: " + caminho13.estaLivre());

        Caminho caminho14 = new Caminho(t, "6b6c6d");
        System.out.println("Teste 11 (caminho horizontal) - Esperado: true, Obtido: " + caminho14.estaLivre());

        Caminho caminho15 = new Caminho(t, "8b8c8d");
        System.out.println("Teste 12 (caminho horizontal) - Esperado: false, Obtido: " + caminho15.estaLivre());

       System.out.println("\nTestes dos métodos CasaInicial e CasaFinal:");
        Caminho caminho16 = new Caminho(t, "3a2a1a");
        Casa casaInicial = caminho16.casaInicial();
        System.out.println("Teste casaInicial - Esperado: 3a, Obtido: " + casaInicial.getLinha() + casaInicial.getColuna());
        Casa casaFinal = caminho16.casaFinal();
        System.out.println("Teste casaFinal - Esperado: 1a, Obtido: " + casaFinal.getLinha() + casaFinal.getColuna());

        Caminho caminho17 = new Caminho(t, "");
        Casa casaInicial2 = caminho17.casaInicial();
        System.out.println("Teste casaInicial (String vazia) - Esperado: null , Obtido: " + casaInicial2);
        Casa casaFinal2 = caminho17.casaFinal();
        System.out.println("Teste casaFinal (String vazia) - Esperado: , Obtido: " + casaFinal2);
    }
    
    
    
    
    
    public static void testarJogador() {
        System.out.println("\nTestando Jogador...");
        // Criar peças para os testes
        Peao peao1 = new Peao(true);   // Peão branco: P
        Peao peao2 = new Peao(true);   // Peão branco: P
        Dama dama = new Dama(true);    // Dama branca: D
        Cavalo cavalo = new Cavalo(false); // Cavalo preto: c
        Bispo bispo = new Bispo(false);   // Bispo preto: b
        Torre torre = new Torre(true);    // Torre branca: T

        // Criar jogadores
        Jogador jogador1 = new Jogador("Jogador1", Arrays.asList(peao1, peao2, dama));
        Jogador jogador2 = new Jogador("Jogador2", Arrays.asList(cavalo, bispo, torre));

        System.out.println("\nTestes do método getNome");
        System.out.println("Teste 1 - Esperado: Jogador1, Obtido: " + jogador1.getNome());
        System.out.println("Teste 2 - Esperado: Jogador2, Obtido: " + jogador2.getNome());

        System.out.println("\nTestes do método getPecas");
        System.out.print("Teste 3 - Esperado: P P D, Obtido: ");
        for (Peca peca : jogador1.getPecas()) {
            System.out.print(peca.desenho() + " ");
        }

        System.out.print("\nTeste 4 - Esperado: c b T, Obtido: ");
        for (Peca peca : jogador2.getPecas()) {
            System.out.print(peca.desenho() + " ");
        }

        System.out.println("\n\nTestes dos métodos capturarPeca e getPecasAtivas");
        jogador1.capturarPeca(peao1); // Captura um Peão
        System.out.print("Teste 5 - Esperado: P D, Obtido: ");
        for (Peca peca : jogador1.getPecasAtivas()) {
            System.out.print(peca.desenho() + " ");
        }

        jogador2.capturarPeca(cavalo); // Captura um Cavalo
        jogador2.capturarPeca(bispo);  // Captura um Bispo
        System.out.print("\nTeste 6 - Esperado: T, Obtido: ");
        for (Peca peca : jogador2.getPecasAtivas()) {
            System.out.print(peca.desenho() + " ");
        }

        System.out.println("\n\nTestes do método pecasCapturadas");
        System.out.print("Teste 7 - Esperado: P, Obtido: " + jogador1.pecasCapturadas());
        System.out.print("\nTeste 8 - Esperado: c b, Obtido: " + jogador2.pecasCapturadas());

        System.out.println("\n\nTestes do método informaJogada");
        System.out.println("Teste 9 - Esperado: [entrada do usuário], Obtido:" + jogador1.informaJogada());
    }

    
    
    
    
    
    
    public static void testarJogada() {
        System.out.println("\nTestando Jogada...");
        // Inicializa o tabuleiro
        Tabuleiro tabuleiro = new Tabuleiro();

        // Criar peças para os testes
        Peao peaoBranco = new Peao(true);
        Peao peaoPreto = new Peao(false);
        Dama damaBranca = new Dama(true);
        Cavalo cavaloPreto = new Cavalo(false);
        Bispo bispoBranco = new Bispo(true);
        Torre torrePreta = new Torre(false);

        // Criar jogadores
        Jogador jogadorBrancas = new Jogador("JogadorBrancas", Arrays.asList(peaoBranco, damaBranca, bispoBranco));
        Jogador jogadorPretas = new Jogador("JogadorPretas", Arrays.asList(peaoPreto, cavaloPreto, torrePreta));

        System.out.println("\nTestes do método ehValida");
        Jogada jogada0 = new Jogada(jogadorBrancas, peaoBranco, 2, 'e', 4, 'e', tabuleiro); // Movimento válido para um Peão na posição inicial
        System.out.println("Teste 0 (movimento válido: Peão na posição inicial move duas casas) - Esperado: true, Obtido: " + jogada0.ehValida(tabuleiro));
        Jogada jogada1 = new Jogada(jogadorBrancas, peaoBranco, 2, 'a', 3, 'a', tabuleiro);
        System.out.println("Teste 1 (movimento válido Peão) - Esperado: true, Obtido: " + jogada1.ehValida(tabuleiro));
        Jogada jogada2 = new Jogada(jogadorBrancas, peaoBranco, 2, 'a', 3, 'b', tabuleiro);
        System.out.println("Teste 2 (Jogada inválida para Peão: movimento diagonal sem captura) - Esperado: false, Obtido: " + jogada2.ehValida(tabuleiro));
        Jogada jogada3 = new Jogada(jogadorBrancas, peaoBranco, 2, 'a', 2, 'b', tabuleiro);
        System.out.println("Teste 3 (Peão move horizontalmente) - Esperado: false, Obtido: " + jogada3.ehValida(tabuleiro));
        tabuleiro.getCasa(4, 'a').setPeca(new Peao(true)); // Coloca um Peão branco em a4
        Jogada jogada4 = new Jogada(jogadorBrancas, peaoBranco, 4, 'a', 6, 'a', tabuleiro); // Movimento inválido porque o peão não está na posição inicial
        System.out.println("Teste 4 (Peão tenta mover 2 casas, mas não está na posição inicial) - Esperado: false, Obtido: " + jogada4.ehValida(tabuleiro));

        tabuleiro.getCasa(5, 'd').setPeca(new Dama(true));
        Jogada jogada5 = new Jogada(jogadorBrancas, damaBranca, 5, 'd', 7, 'd', tabuleiro);
        System.out.println("\nTeste 5 (Jogada válida para Dama) - Esperado: true, Obtido: " + jogada5.ehValida(tabuleiro));
        Jogada jogada6 = new Jogada(jogadorBrancas, damaBranca, 5, 'd', 7, 'e', tabuleiro);
        System.out.println("Teste 6 (Jogada inválida para Dama: movimento inválido) - Esperado: false, Obtido: " + jogada6.ehValida(tabuleiro));

        Jogada jogada7 = new Jogada(jogadorPretas, cavaloPreto, 8, 'b', 6, 'c', tabuleiro);
        System.out.println("\nTeste 7 (Jogada válida para Cavalo) - Esperado: true, Obtido: " + jogada7.ehValida(tabuleiro));
        Jogada jogada8 = new Jogada(jogadorPretas, cavaloPreto, 8, 'b', 7, 'd', tabuleiro);
        System.out.println("Teste 8 (Jogada inválida para Cavalo: movimento não permitido) - Esperado: false, Obtido: " + jogada8.ehValida(tabuleiro));

        tabuleiro.getCasa(6, 'b').setPeca(new Torre(false));
        Jogada jogada9 = new Jogada(jogadorPretas, torrePreta, 6, 'b', 4, 'b', tabuleiro);
        System.out.println("\nTeste 9 (Jogada válida para Torre) - Esperado: true, Obtido: " + jogada9.ehValida(tabuleiro));
        Jogada jogada10 = new Jogada(jogadorPretas, torrePreta, 6, 'b', 4, 'd', tabuleiro);
        System.out.println("Teste 10 (Jogada inválida para Torre: movimento diagonal) - Esperado: false, Obtido: " + jogada10.ehValida(tabuleiro));

        tabuleiro.getCasa(3, 'a').setPeca(new Bispo(true));
        Jogada jogada11 = new Jogada(jogadorBrancas, bispoBranco, 3, 'a', 5, 'c', tabuleiro);
        System.out.println("\nTeste 11 (Jogada válida para Bispo) - Esperado: true, Obtido: " + jogada11.ehValida(tabuleiro));
        Jogada jogada12 = new Jogada(jogadorBrancas, bispoBranco, 3, 'a', 5, 'e', tabuleiro);
        System.out.println("Teste 12 (Jogada inválida para Bispo: movimento inválido) - Esperado: false, Obtido: " + jogada12.ehValida(tabuleiro));

        Jogada jogada13 = new Jogada(jogadorBrancas, damaBranca, 1, 'd', 4, 'd', tabuleiro);
        System.out.println("\nTeste 13 (Caminho não está livre) - Esperado: false, Obtido: " + jogada13.ehValida(tabuleiro));

        Jogada jogada14 = new Jogada(jogadorBrancas, damaBranca, 1, 'd', 9, 'd', tabuleiro); // Linha final fora dos limites
        System.out.println("\nTeste 14 (Movimento fora dos limites do tabuleiro) - Esperado: false, Obtido: " + jogada14.ehValida(tabuleiro));
    
        Jogada jogada15 = new Jogada(jogadorBrancas, damaBranca, 3, 'a', 4, 'a', tabuleiro);
        System.out.println("\nTeste 15 (Casa inicial não está ocupada) - Esperado: false, Obtido: " + jogada15.ehValida(tabuleiro));

        tabuleiro.getCasa(1, 'd').setPeca(new Dama(false)); // Coloca uma Dama preta em d1
        Jogada jogada16 = new Jogada(jogadorBrancas, damaBranca, 1, 'd', 3, 'd', tabuleiro);
        System.out.println("\nTeste 16 (Casa inicial ocupada por peça que não é da cor do jogador) - Esperado: false, Obtido: " + jogada16.ehValida(tabuleiro));

        tabuleiro.getCasa(5, 'c').setPeca(new Dama(true)); // Coloca uma Dama branca em c5
        Jogada jogada17 = new Jogada(jogadorBrancas, damaBranca, 3, 'a', 5, 'c', tabuleiro);
        System.out.println("\nTeste 17 (Casa final do caminho é da mesma cor do jogador) - Esperado: false, Obtido: " + jogada17.ehValida(tabuleiro));
        
        System.out.println(tabuleiro.desenho());
    }
    
    
    
    
    
    
    
    public static void testarJogo() {
        System.out.println("\nTestando Jogo\n...");
        // Inicializando o jogo e jogadores
        Jogo jogo = new Jogo();
        Jogador jogador1 = new Jogador("Jogador1", jogo.inicializarPecas(true));
        Jogador jogador2 = new Jogador("Jogador2", jogo.inicializarPecas(false));
        jogo.setJogadores(jogador1, jogador2);

        System.out.println("Testes do método jogadaValida");
        System.out.println("Teste 1: Jogada válida - Esperado: true, Obtido: " + jogo.jogadaValida(2, 'a', 3, 'a'));  // Jogada de peão, linha 1, coluna 'a' para linha 3
        System.out.println("Teste 2: jogada inválida - Esperado: false, Obtido: " + jogo.jogadaValida(1, 'a', 4, 'a'));  // Jogada inválida de peão, linha 1, coluna 'a' para linha 4

        System.out.println("\nTestes dos métodos realizarJogada e registroJogo");
        jogo.realizarJogada(2, 'a', 3, 'a');  // Movimentar peão
        jogo.realizarJogada(1, 'b', 3, 'c');  // Movimentar cavalo
        jogo.realizarJogada(3, 'b', 3, 'c');  // jogada inválida
        System.out.println("Teste 3: Realizar jogada válida - Esperado:\nJogador1\nJogador2\n2a3a\n1b3c\nObtido:\n" + jogo.registroJogo());

        System.out.println("\nTestes do método alternarJogador");
        System.out.println("Teste 4: Alternar jogador - Jogador atual: " + jogo.jogadorAtual.getNome());
        jogo.alternarJogador();
        System.out.println("Jogador após alternar - Esperado: Jogador2, Obtido: " + jogo.jogadorAtual.getNome());
        jogo.realizarJogada(8, 'b', 6, 'c');  // Movimentar cavalo

        System.out.println("\nTestes do método processarJogada");
        System.out.println("Teste 5: Processar jogada com formato inválido");
        System.out.println("Esperado: false, Obtido: " + jogo.processarJogada("a1"));
        System.out.println("Teste 6: Processar jogada válida (brancas) ERRO RIMPRIMINDO JOGADA INVALIDA");
        System.out.println("Esperado: true, Obtido: " + jogo.processarJogada("2e3e"));
        System.out.println("Teste 10: Processar jogada válida (pretas)");
        System.out.println("Esperado: true, Obtido: " + jogo.processarJogada("7e6e"));

        System.out.println("\nTestes do método mostrarTabuleiro\n Mudar acesso do método para public temporariamente");
        System.out.println("Teste 7: Mostrar tabuleiro");
        jogo.mostrarTabuleiro();
        
        System.out.println("\nTestes do método inicializarPecas ERRO ADICIONANDO 2 DAMAS e 2 REIS");
        List<Peca> pecasPretas = jogo.inicializarPecas(false);
        System.out.println("Teste 8: Peças Pretas:");
        for (Peca peca : pecasPretas) {
            System.out.println(peca.desenho());
        }
        
        System.out.println("\nTestes do método encerrarJogo\n Mudar acesso do método para public temporaiamente");
        jogo.encerrarJogo();
        
        System.out.println("\nTestes do método iniciarJogoArmazenado");
        jogo.iniciarJogoArmazenado();
        
        System.out.println("\nTestes do método iniciarJogo");
        Jogo novoJogo = new Jogo();
        novoJogo.iniciarJogo();
        
      
      
    
    }
}
