import java.sql.SQLOutput;

//main nao implementada
//vai estar dentro do gerenciador
public class Main {
    public static void main(String[] args) {
        testes();
    }

    private static void testes() {
        testarBispo();
        testarCavalo();
        testarDama();
        testarPeao();
        testarRei();
        testarTorre();
        testarCasa();
        testarTabuleiro();
        testarCaminho();
    }

    private static void testarBispo() {
        System.out.println("Testando Bispo...");
        Bispo bispoBranco = new Bispo(true);
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
        System.out.println("Testes para desenho:");
        System.out.println(tabuleiro.desenho());
        // Testes para o método noLimite
        System.out.println("Testes para noLimite:");
        System.out.println("Linha 1, coluna 'a', Esperado: true, Obtido: " + tabuleiro.noLimite(1, 'a'));  
        System.out.println("Linha 9, coluna 'a', Esperado: false, Obtido: " + tabuleiro.noLimite(9, 'a')); 
        System.out.println("Linha 1, coluna 'i', Esperado: false, Obtido: " + tabuleiro.noLimite(1, 'i'));  
        System.out.println("Linha -1, coluna 'z', Esperado: false, Obtido: " + tabuleiro.noLimite(-1, 'z'));  
        // Testes para o método getCasa
        System.out.println("\nTestes para getCasa:");
        System.out.println("Casa em linha 2, coluna 'a', Esperado: P, Obtido: " + tabuleiro.getCasa(2, 'a')); 
        System.out.println("Casa em linha 1, coluna 'b', Esperado: C, Obtido: " + tabuleiro.getCasa(1, 'b')); 
        System.out.println("Casa em linha 8, coluna 'h', Esperado: t, Obtido: " + tabuleiro.getCasa(8, 'h')); 
        System.out.println("Casa em linha 9, coluna 'a', Esperado: null, Obtido: " + tabuleiro.getCasa(9, 'a')); 
        System.out.println("Casa em linha 1, coluna 'i', Esperado: null, Obtido: " + tabuleiro.getCasa(1, 'i')); 
        System.out.println("Casa em linha -1, coluna 'z', Esperado: null, Obtido: " + tabuleiro.getCasa(-1, 'z')); 
    }

    private static void testarCaminho() {
        Tabuleiro t = new Tabuleiro();
        // TESTE CAMINHO
        // imprimindo
        System.out.println("\nTeste Caminho:");

        // Teste 1: Caminho Simples
        Caminho caminho1 = new Caminho(t, "2a3a4a");
        System.out.println("Teste 1: " + caminho1.estaLivre());

        // teste 2: Caminho Simples
        Caminho caminho2 = new Caminho(t, "2a1a");
        System.out.println("Teste 2: " + caminho2.estaLivre());

        // Teste 3: Caminho Diagonal
        Caminho caminho3 = new Caminho(t, "1b2c3d");
        System.out.println("Teste 3: " + caminho3.estaLivre());

        // teste 4: Caminho Diagonal
        Caminho caminho4 = new Caminho(t, "1a2b3c");
        System.out.println("Teste 4: " + caminho4.estaLivre());

        // teste 5: Caminho Vertical
        Caminho caminho5 = new Caminho(t, "2h3h4h");
        System.out.println("Teste 5: " + caminho5.estaLivre());

        // teste 6: Caminho Vertical
        Caminho caminho6 = new Caminho(t, "1h2h3h");
        System.out.println("Teste 6: " + caminho6.estaLivre());

        // teste 7: Caminho Horizontal
        Caminho caminho7 = new Caminho(t, "2b2c2d");
        System.out.println("Teste 7: " + caminho7.estaLivre());

        // teste 8: Caminho Horizontal
        Caminho caminho8 = new Caminho(t, "1b1c1d");
        System.out.println("Teste 8: " + caminho8.estaLivre());

        System.out.println("\nteste caminho da parte de cima do tabuleiro");
        // teste 1: caminho simples
        Caminho caminho9 = new Caminho(t, "7a6a5a");
        System.out.println("teste 1: " + caminho9.estaLivre());

        // teste 2: caminho simples
        Caminho caminho10 = new Caminho(t, "8a7a6a");
        System.out.println("teste 2: " + caminho10.estaLivre());

        // teste 3: caminho diagonal
        Caminho caminho11 = new Caminho(t, "8b7c6d");
        System.out.println("teste 3: " + caminho11.estaLivre());

        // teste 4: caminho diagonal
        Caminho caminho12 = new Caminho(t, "8a7b6c");
        System.out.println("teste 4: " + caminho12.estaLivre());

        // teste 5: caminho vertical
        Caminho caminho13 = new Caminho(t, "8h7h6h");
        System.out.println("teste 5: " + caminho13.estaLivre());

        // teste 6: caminho vertical
        Caminho caminho14 = new Caminho(t, "7h6h5h");
        System.out.println("teste 6: " + caminho14.estaLivre());

        // teste 7: caminho horizontal
        Caminho caminho15 = new Caminho(t, "7b7c7d");
        System.out.println("teste 7: " + caminho15.estaLivre());

        // teste 8: caminho horizontal
        Caminho caminho16 = new Caminho(t, "8b8c8d");
        System.out.println("teste 8: " + caminho16.estaLivre());
    }
}
