package jogoDaVelha;

import java.util.Random;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        char[][] jogo = new char[3][3];
        String[] jogador = new String[2];
        int opcao = 0, gamemode, dificuldade;
        while (opcao != 2) {

            System.out.println("JOGO DA VELHA");
            System.out.println("1 - Jogar");
            System.out.println("2 - Sair");
            System.out.println("Digite sua opção.");
            opcao = in.nextInt();
            while (opcao != 1 && opcao != 2) {
                System.out.println("Digite uma opção válida.");
                opcao = in.nextInt();
            }
            if (opcao == 1) {
                System.out.println("JOGO DA VELHA");
                System.out.println("1 - 1 JOGADOR");
                System.out.println("2 - 2 JOGADORES");
                System.out.println("Digite sua opção.");
                gamemode = in.nextInt();
                while (gamemode != 1 && gamemode != 2) {
                    System.out.println("Selecione um modo de jogo válido.");
                    gamemode = in.nextInt();
                }
                switch (gamemode) {
                    case 1:
                        System.out.println("NÍVEL DO JOGO");
                        System.out.println("1 - Fácil");
                        System.out.println("2 - Difícil");
                        dificuldade = in.nextInt();
                        while (dificuldade < 1 || dificuldade > 2) {
                            System.out.println("Dificuldade inválida. Digite uma dificuldade válida.");
                            dificuldade = in.nextInt();
                        }
                        jogo1Prejogo(jogo, dificuldade);
                        break;
                    case 2:
                        jogo2(jogo, jogador);
                        break;
                }
            }
        }
    }

    public static void jogo1Prejogo(char[][] jogo, int dif) {
        Scanner in = new Scanner(System.in);
        Random sorteio = new java.security.SecureRandom();
        int vez;
        String jogador;

        System.out.println("Informe seu nome, jogador.");
        jogador = in.next();
        vez = sorteio.nextInt(2);
        if (vez == 0) {
            System.out.println("Sorteando quem vai começar... O jogador " + jogador + " começa.");
        } else {
            System.out.println("Sorteando quem vai começar... O computador começa.");
        }
        System.out.println("X - " + jogador);
        System.out.println("O - computador");

        novoJogo(jogo);
        if (dif == 1) {
            jogo1Facil(jogo, jogador, vez);
        } else {
            jogo1Dificil(jogo, jogador, vez);
        }

    }

    public static void jogo1Facil(char[][] jogo, String jogador, int vez) {
        Scanner in = new Scanner(System.in);
        Random jogadaComputador = new java.security.SecureRandom();
        int l, c, turnos;
        char simbolo1 = 'X', simbolo2 = 'O';

        for (turnos = 0; turnos < 9; turnos++) {
            mostrar(jogo);
            System.out.println(" ");
            if (vez == 0) {
                System.out.println(jogador + ", é sua vez de jogar.");
                System.out.println("Informe a linha.");
                l = in.nextInt();
                while (l < 1 || l > 3) {
                    System.out.println("Digite valores válidos!");
                    l = in.nextInt();
                }
                System.out.println("Informe a coluna.");
                c = in.nextInt();
                while (c < 1 || c > 3) {
                    System.out.println("Digite valores válidos!");
                    c = in.nextInt();
                }
                while (jogo[l - 1][c - 1] != ' ') {
                    System.out.println("Posição ocupada. Digite outra posição.");
                    l = in.nextInt();
                    c = in.nextInt();
                }
                jogo[l - 1][c - 1] = 'X';
                if (vitoria(jogo, simbolo1)) {
                    mostrar(jogo);
                    System.out.println(jogador + " venceu!");
                    System.out.println(" ");
                    break;
                }

                vez = 1;
            } else {

                l = jogadaComputador.nextInt(3);
                c = jogadaComputador.nextInt(3);

                while (jogo[l][c] != ' ') {
                    l = jogadaComputador.nextInt(3);
                    c = jogadaComputador.nextInt(3);
                }
                jogo[l][c] = 'O';
                System.out.println("Turno do computador encerrado.");
                if (vitoria(jogo, simbolo2)) {
                    mostrar(jogo);
                    System.out.println("O computador venceu!");
                    System.out.println(" ");
                    break;
                }

                vez = 0;
            }
        }
        if (turnos == 9) {
            System.out.println("Jogo empatado. Tabuleiro cheio.");
        }
    }

    public static void jogo1Dificil(char[][] jogo, String jogador, int vez) {
        Scanner in = new Scanner(System.in);
        Random jogadaComputador = new java.security.SecureRandom();
        int l, c, turnos;
        boolean jogou;
        char simbolo1 = 'X', simbolo2 = 'O';

        for (turnos = 0; turnos < 9; turnos++) {
            mostrar(jogo);
            System.out.println(" ");
            if (vez == 0) {
                System.out.println(jogador + ", é sua vez de jogar.");
                System.out.println("Informe a linha.");
                l = in.nextInt();
                while (l < 1 || l > 3) {
                    System.out.println("Digite valores válidos!");
                    l = in.nextInt();
                }
                System.out.println("Informe a coluna.");
                c = in.nextInt();
                while (c < 1 || c > 3) {
                    System.out.println("Digite valores válidos!");
                    c = in.nextInt();
                }
                while (jogo[l - 1][c - 1] != ' ') {
                    System.out.println("Posição ocupada. Digite outra posição.");
                    l = in.nextInt();
                    c = in.nextInt();
                }
                jogo[l - 1][c - 1] = 'X';
                if (vitoria(jogo, simbolo1)) {
                    mostrar(jogo);
                    System.out.println(jogador + " venceu!");
                    System.out.println(" ");
                    break;
                }

                vez = 1;
            } else {

                jogou = vitoriaImediata(jogo);
                if (jogou) {
                    System.out.println("Turno do computador encerrado.");
                } else {
                    jogou = bloquearVitoria(jogo);
                    if (jogou) {
                        System.out.println("Turno do computador encerrado.");
                    } else {
                        l = jogadaComputador.nextInt(3);
                        c = jogadaComputador.nextInt(3);

                        while (jogo[l][c] != ' ') {
                            l = jogadaComputador.nextInt(3);
                            c = jogadaComputador.nextInt(3);
                        }
                        jogo[l][c] = 'O';
                        System.out.println("Turno do computador encerrado.");
                    }
                }

                if (vitoria(jogo, simbolo2)) {
                    mostrar(jogo);
                    System.out.println("O computador venceu!");
                    System.out.println(" ");
                    break;
                }

                vez = 0;
            }
        }
        if (turnos == 9) {
            System.out.println("Jogo empatado. Tabuleiro cheio.");
        }
    }

    public static boolean bloquearVitoria(char[][] jogo) {

        if (jogo[0][0] == 'X' && jogo[0][1] == 'X' && jogo[0][2] == ' ') {
            jogo[0][2] = 'O';
            return true;
        }
        if (jogo[0][0] == 'X' && jogo[0][2] == 'X' && jogo[0][1] == ' ') {
            jogo[0][1] = 'O';
            return true;
        }
        if (jogo[0][1] == 'X' && jogo[0][2] == 'X' && jogo[0][0] == ' ') {
            jogo[0][0] = 'O';
            return true;
        }
        if (jogo[1][0] == 'X' && jogo[1][1] == 'X' && jogo[1][2] == ' ') {
            jogo[1][2] = 'O';
            return true;
        }
        if (jogo[1][1] == 'X' && jogo[1][2] == 'X' && jogo[1][0] == ' ') {
            jogo[1][0] = 'O';
            return true;
        }
        if (jogo[1][0] == 'X' && jogo[1][2] == 'X' && jogo[1][1] == ' ') {
            jogo[1][1] = 'O';
            return true;
        }
        if (jogo[2][0] == 'X' && jogo[2][1] == 'X' && jogo[2][2] == ' ') {
            jogo[2][2] = 'O';
            return true;
        }
        if (jogo[2][1] == 'X' && jogo[2][2] == 'X' && jogo[2][0] == ' ') {
            jogo[2][0] = 'O';
            return true;
        }
        if (jogo[2][0] == 'X' && jogo[2][2] == 'X' && jogo[2][1] == ' ') {
            jogo[2][1] = 'O';
            return true;
        }
        if (jogo[0][0] == 'X' && jogo[1][0] == 'X' && jogo[2][0] == ' ') {
            jogo[2][0] = 'O';
            return true;
        }
        if (jogo[0][0] == 'X' && jogo[2][0] == 'X' && jogo[1][0] == ' ') {
            jogo[1][0] = 'O';
            return true;
        }
        if (jogo[1][0] == 'X' && jogo[2][0] == 'X' && jogo[0][0] == ' ') {
            jogo[0][0] = 'O';
            return true;
        }
        if (jogo[0][1] == 'X' && jogo[1][1] == 'X' && jogo[2][1] == ' ') {
            jogo[2][1] = 'O';
            return true;
        }
        if (jogo[0][1] == 'X' && jogo[2][1] == 'X' && jogo[1][1] == ' ') {
            jogo[1][1] = 'O';
            return true;
        }
        if (jogo[1][1] == 'X' && jogo[2][1] == 'X' && jogo[0][1] == ' ') {
            jogo[0][1] = 'O';
            return true;
        }
        if (jogo[0][2] == 'X' && jogo[1][2] == 'X' && jogo[2][2] == ' ') {
            jogo[2][2] = 'O';
            return true;
        }
        if (jogo[0][2] == 'X' && jogo[2][2] == 'X' && jogo[1][2] == ' ') {
            jogo[1][2] = 'O';
            return true;
        }
        if (jogo[1][2] == 'X' && jogo[2][2] == 'X' && jogo[0][2] == ' ') {
            jogo[0][2] = 'O';
            return true;
        }
        if (jogo[0][0] == 'X' && jogo[1][1] == 'X' && jogo[2][2] == ' ') {
            jogo[2][2] = 'O';
            return true;
        }
        if (jogo[0][0] == 'X' && jogo[2][2] == 'X' && jogo[1][1] == ' ') {
            jogo[1][1] = 'O';
            return true;
        }
        if (jogo[1][1] == 'X' && jogo[2][2] == 'X' && jogo[0][0] == ' ') {
            jogo[0][0] = 'O';
            return true;
        }
        if (jogo[0][2] == 'X' && jogo[1][1] == 'X' && jogo[2][0] == ' ') {
            jogo[2][0] = 'O';
            return true;
        }
        if (jogo[0][2] == 'X' && jogo[2][0] == 'X' && jogo[1][1] == ' ') {
            jogo[1][1] = 'O';
            return true;
        }
        if (jogo[1][1] == 'X' && jogo[2][0] == 'X' && jogo[0][2] == ' ') {
            jogo[0][2] = 'O';
            return true;
        }
        return false;

    }

    public static boolean vitoriaImediata(char[][] jogo) {

        if (jogo[0][0] == 'O' && jogo[0][1] == 'O' && jogo[0][2] == ' ') {
            jogo[0][2] = 'O';
            return true;
        }
        if (jogo[0][0] == 'O' && jogo[0][2] == 'O' && jogo[0][1] == ' ') {
            jogo[0][1] = 'O';
            return true;
        }
        if (jogo[0][1] == 'O' && jogo[0][2] == 'O' && jogo[0][0] == ' ') {
            jogo[0][0] = 'O';
            return true;
        }
        if (jogo[1][0] == 'O' && jogo[1][1] == 'O' && jogo[1][2] == ' ') {
            jogo[1][2] = 'O';
            return true;
        }
        if (jogo[1][1] == 'O' && jogo[1][2] == 'O' && jogo[1][0] == ' ') {
            jogo[1][0] = 'O';
            return true;
        }
        if (jogo[1][0] == 'O' && jogo[1][2] == 'O' && jogo[1][1] == ' ') {
            jogo[1][1] = 'O';
            return true;
        }
        if (jogo[2][0] == 'O' && jogo[2][1] == 'O' && jogo[2][2] == ' ') {
            jogo[2][2] = 'O';
            return true;
        }
        if (jogo[2][1] == 'O' && jogo[2][2] == 'O' && jogo[2][0] == ' ') {
            jogo[2][0] = 'O';
            return true;
        }
        if (jogo[2][0] == 'O' && jogo[2][2] == 'O' && jogo[2][1] == ' ') {
            jogo[2][1] = 'O';
            return true;
        }
        if (jogo[0][0] == 'O' && jogo[1][0] == 'O' && jogo[2][0] == ' ') {
            jogo[2][0] = 'O';
            return true;
        }
        if (jogo[0][0] == 'O' && jogo[2][0] == 'O' && jogo[1][0] == ' ') {
            jogo[1][0] = 'O';
            return true;
        }
        if (jogo[1][0] == 'O' && jogo[2][0] == 'O' && jogo[0][0] == ' ') {
            jogo[0][0] = 'O';
            return true;
        }
        if (jogo[0][1] == 'O' && jogo[1][1] == 'O' && jogo[2][1] == ' ') {
            jogo[2][1] = 'O';
            return true;
        }
        if (jogo[0][1] == 'O' && jogo[2][1] == 'O' && jogo[1][1] == ' ') {
            jogo[1][1] = 'O';
            return true;
        }
        if (jogo[1][1] == 'O' && jogo[2][1] == 'O' && jogo[0][1] == ' ') {
            jogo[0][1] = 'O';
            return true;
        }
        if (jogo[0][2] == 'O' && jogo[1][2] == 'O' && jogo[2][2] == ' ') {
            jogo[2][2] = 'O';
            return true;
        }
        if (jogo[0][2] == 'O' && jogo[2][2] == 'O' && jogo[1][2] == ' ') {
            jogo[1][2] = 'O';
            return true;
        }
        if (jogo[1][2] == 'O' && jogo[2][2] == 'O' && jogo[0][2] == ' ') {
            jogo[0][2] = 'O';
            return true;
        }
        if (jogo[0][0] == 'O' && jogo[1][1] == 'O' && jogo[2][2] == ' ') {
            jogo[2][2] = 'O';
            return true;
        }
        if (jogo[0][0] == 'O' && jogo[2][2] == 'O' && jogo[1][1] == ' ') {
            jogo[1][1] = 'O';
            return true;
        }
        if (jogo[1][1] == 'O' && jogo[2][2] == 'O' && jogo[0][0] == ' ') {
            jogo[0][0] = 'O';
            return true;
        }
        if (jogo[0][2] == 'O' && jogo[1][1] == 'O' && jogo[2][0] == ' ') {
            jogo[2][0] = 'O';
            return true;
        }
        if (jogo[0][2] == 'O' && jogo[2][0] == 'O' && jogo[1][1] == ' ') {
            jogo[1][1] = 'O';
            return true;
        }
        if (jogo[1][1] == 'O' && jogo[2][0] == 'O' && jogo[0][2] == ' ') {
            jogo[0][2] = 'O';
            return true;
        }
        return false;
    }

    public static void jogo2(char[][] jogo, String[] jogador) {
        Scanner in = new Scanner(System.in);
        Random sorteio = new Random();
        int vez, l, c, turnos;

        System.out.println("Informe o nome do jogador 1:");
        jogador[0] = in.next();
        System.out.println("Informe o nome do jogador 2:");
        jogador[1] = in.next();
        vez = sorteio.nextInt(2);
        System.out.println("Sorteando quem vai começar... " + jogador[vez] + " começa.");
        System.out.println("X - " + jogador[0]);
        System.out.println("O - " + jogador[1]);
        char simbolo1 = 'X';
        char simbolo2 = 'O';
        System.out.println(" ");
        novoJogo(jogo);
        for (turnos = 0; turnos < 9; turnos++) {
            mostrar(jogo);
            System.out.println(" ");
            if (vez == 0) {
                System.out.println(jogador[vez] + ", é sua vez de jogar.");
                System.out.println("Informe a linha.");
                l = in.nextInt();
                while (l < 1 || l > 3) {
                    System.out.println("Digite valores válidos!");
                    l = in.nextInt();
                }
                System.out.println("Informe a coluna.");
                c = in.nextInt();
                while (c < 1 || c > 3) {
                    System.out.println("Digite valores válidos!");
                    c = in.nextInt();
                }
                while (jogo[l - 1][c - 1] != ' ') {
                    System.out.println("Posição ocupada. Digite outra posição.");
                    l = in.nextInt();
                    c = in.nextInt();
                }
                jogo[l - 1][c - 1] = 'X';
                if (vitoria(jogo, simbolo1)) {
                    mostrar(jogo);
                    System.out.println("Jogador " + jogador[0] + " venceu!");
                    System.out.println(" ");
                    break;
                }

                vez = 1;
            } else {
                System.out.println(jogador[vez] + ", é sua vez de jogar.");
                System.out.println("Informe a linha.");
                l = in.nextInt();
                while (l < 1 || l > 3) {
                    System.out.println("Digite valores válidos!");
                    l = in.nextInt();
                }
                System.out.println("Informe a coluna.");
                c = in.nextInt();
                while (c < 1 || c > 3) {
                    System.out.println("Digite valores válidos!");
                    c = in.nextInt();
                }
                while (jogo[l - 1][c - 1] != ' ') {
                    System.out.println("Posição ocupada. Digite outra posição.");
                    l = in.nextInt();
                    c = in.nextInt();
                }
                jogo[l - 1][c - 1] = 'O';
                if (vitoria(jogo, simbolo2)) {
                    mostrar(jogo);
                    System.out.println("Jogador " + jogador[1] + " venceu!");
                    System.out.println(" ");
                    break;
                }

                vez = 0;
            }

        }
        if (turnos == 10) {
            System.out.println("Jogo empatado. Tabuleiro cheio.");
        }
    }

    public static boolean vitoria(char[][] jogo, char simbolo) {


        if (jogo[0][0] == simbolo && jogo[0][1] == simbolo && jogo[0][2] == simbolo) {
            return true;
        } else if (jogo[1][0] == simbolo && jogo[1][1] == simbolo && jogo[1][2] == simbolo) {
            return true;
        } else if (jogo[2][0] == simbolo && jogo[2][1] == simbolo && jogo[2][2] == simbolo) {
            return true;
        } else if (jogo[0][0] == simbolo && jogo[1][0] == simbolo && jogo[2][0] == simbolo) {
            return true;
        } else if (jogo[0][1] == simbolo && jogo[1][1] == simbolo && jogo[2][1] == simbolo) {
            return true;
        } else if (jogo[0][2] == simbolo && jogo[1][2] == simbolo && jogo[2][2] == simbolo) {
            return true;
        } else if (jogo[0][0] == simbolo && jogo[1][1] == simbolo && jogo[2][2] == simbolo) {
            return true;
        } else if (jogo[0][2] == simbolo && jogo[1][1] == simbolo && jogo[2][0] == simbolo) {
            return true;
        } else {
            return false;
        }
    }

    public static void mostrar(char[][] jogo) {
        int l;
        for (l = 0; l < jogo.length; l++) {

            System.out.println(jogo[l][0] + " | " + jogo[l][1] + " | " + jogo[l][2]);
            if (l < 2) {
                System.out.println("- - - - -");
            }

        }
    }

    public static void novoJogo(char[][] jogo) {
        int l, c;
        for (l = 0; l < jogo.length; l++) {
            for (c = 0; c < jogo[0].length; c++) {
                jogo[l][c] = ' ';
            }
        }
    }
}
