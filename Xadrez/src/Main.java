import java.sql.SQLOutput;

//main nao implementada
//vai estar dentro do gerenciador
public class Main {
    public static void main(String[] args) {
        //TESTE TABULEIRO
        Tabuleiro t = new Tabuleiro();
        System.out.println(t.desenho());


        // TESTE CAMINHO
        //  "a2" ate "a4")
        String sequencia = "2a3a4a";
        Caminho caminho = new Caminho(t, sequencia);

        // imprimindo
        System.out.println("Teste Caminho:");
        System.out.println("Casa Inicial: " + caminho.casaInicial());
        System.out.println("Casa Final: " + caminho.casaFinal());
        System.out.println("Caminho está livre? " + caminho.estaLivre());
    }
}
