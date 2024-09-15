// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
import Pecas.*;
public class Tabuleiro {
    private Casa[][] casas;

    public Tabuleiro() {
        casas = new Casa[8][8];
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        for (int linha = 1; linha <= 8; linha++) {
            for (char coluna = 'a'; coluna <= 'h'; coluna++) {
                String cor = ((linha + coluna) % 2 == 0) ? "preto" : "branco"; // Preenche as casas com as cores alternadas
                casas[linha-1][coluna-'a'] = new Casa(cor, linha, coluna);
            }
        }
        configurarPecasIniciais();
    }

    private void configurarPecasIniciais(){
        //Configurando Peões
        for (char coluna = 'a'; coluna <= 'h'; coluna++) {
            casas[1][coluna - 'a'].setPeca(new Peao(true));  // Peões brancos na linha 2
            casas[6][coluna - 'a'].setPeca(new Peao(false)); // Peões pretos na linha 7
        }
        //Configurando Torres
        casas[0]['a' - 'a'].setPeca(new Torre(true));  // Torre branca em a1
        casas[0]['h' - 'a'].setPeca(new Torre(true));  // Torre branca em h1
        casas[7]['a' - 'a'].setPeca(new Torre(false)); // Torre preta em a8
        casas[7]['h' - 'a'].setPeca(new Torre(false)); // Torre preta em h8

        //Configurando Cavalos
        casas[0]['b' - 'a'].setPeca(new Cavalo(true)); // Cavalo branco em b1
        casas[0]['g' - 'a'].setPeca(new Cavalo(true)); // Cavalo branco em g1
        casas[7]['b' - 'a'].setPeca(new Cavalo(false)); // Cavalo preto em b8
        casas[7]['g' - 'a'].setPeca(new Cavalo(false)); // Cavalo preto em g8

        //Configurando Bispos
        casas[0]['c' - 'a'].setPeca(new Bispo(true)); // Bispo branco em c1
        casas[0]['f' - 'a'].setPeca(new Bispo(true)); // Bispo branco em f1
        casas[7]['c' - 'a'].setPeca(new Bispo(false)); // Bispo preto em c8
        casas[7]['f' - 'a'].setPeca(new Bispo(false)); // Bispo preto emo f8

        //Configurando Rainhas
        casas[0]['d' - 'a'].setPeca(new Dama(true)); // Rainha branca em d1
        casas[7]['d' - 'a'].setPeca(new Dama(false)); // Rainha preta em d8

        //Configurando Reis
        casas[0]['e' - 'a'].setPeca(new Rei(true)); // Rei branco em e1
        casas[7]['e' - 'a'].setPeca(new Rei(false)); // Rei preto em e8
    }

    public boolean noLimite(int linha, char coluna) {
        return linha >= 1 && linha <= 8 && coluna >= 'a' && coluna <= 'h';
    }

    public Casa getCasa(int linha, char coluna) {
        if (noLimite(linha, coluna)) {
            return casas[linha-1][coluna-'a'];
        } else {
            return null;
        }
    }

    public String desenho() {
        StringBuilder desenho = new StringBuilder();
        desenho.append("  A B C D E F G H\n");
        for (int linha = 8; linha >= 1; linha--) {
            desenho.append(linha).append(" ");
            for (char coluna = 'a'; coluna <= 'h'; coluna++) {
                desenho.append(getCasa(linha, coluna).toString()).append(" ");
            }
            desenho.append("\n");
        }
        desenho.append("  a b c d e f g h\n");
        return desenho.toString();
    }
}
