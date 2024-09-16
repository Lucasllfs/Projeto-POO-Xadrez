
// Trabalho POO - Grupo 8: Anne Mari Suenaga Sakai, Eline Vieira, Gabrielle Caram, Kauê Almeida Gonçalves de Oliveira, Lucas Lima Felix da Silva
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Pecas.Peca;
public class Jogador {
    private String nome;
    private List<Peca> pecas;
    private List<Peca> pecasCapturadas;

    public Jogador(String nome, List<Peca> pecas) {
        this.nome = nome;
        this.pecas = pecas;
        this.pecasCapturadas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public void capturarPeca(Peca peca) {
        if (!peca.capturada()) {
            peca.setCapturada(true);
            pecasCapturadas.add(peca);
        }
    }

    public List<Peca> getPecasAtivas() {
        List<Peca> ativas = new ArrayList<>();
        for (Peca peca : pecas) {
            if (!peca.capturada()) {
                ativas.add(peca);
            }
        }
        return ativas;
    }

    public String informaJogada() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a jogada (ou 'parar' para interromper): ");
        return scanner.nextLine();
    }

    public String pecasCapturadas() {
        StringBuilder capturadas = new StringBuilder();
        for (Peca peca : pecasCapturadas) {
            capturadas.append(peca.desenho()).append(" ");
        }
        return capturadas.toString().trim();
    }
}
