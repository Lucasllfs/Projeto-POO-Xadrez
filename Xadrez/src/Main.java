import java.sql.SQLOutput;

//main nao implementada
//vai estar dentro do gerenciador
public class Main {
    public static void main(String[] args) {
        //TESTE TABULEIRO
        Tabuleiro t = new Tabuleiro();
        System.out.println(t.desenho());


        // TESTE CAMINHO
//        // imprimindo
        System.out.println("Teste Caminho:");


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
