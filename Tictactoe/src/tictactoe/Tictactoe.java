/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Mario y Axel
 */
public class Tictactoe {

    int tablero[][] = new int[3][3];
    /**
     * @param args the command line arguments
     */
    public static int jugados = 1;
    public static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Bienvenido a Tictactoe de Axel & Mario");
        Tictactoe nuestro_juego = new Tictactoe();

        char opcion = '1';
        
        System.out.println("1- Automático");
        System.out.println("2- Modo manual");
        opcion = teclado.readLine().charAt(0);

        while ((opcion == '1') || (opcion == '2')) {
            System.out.println("Vas a empezar la " + jugados + " partida");
            switch (opcion) {
                case '1':
                    nuestro_juego.inicializa();
                    nuestro_juego.dibuja_tres_en_raya();
                    nuestro_juego.comenzar_a_jugar_auto();
                    jugados = jugados + 1;
                    break;

                case '2':
                    nuestro_juego.inicializa();
                    nuestro_juego.dibuja_tres_en_raya();
                    nuestro_juego.comenzar_a_jugar();
                    jugados = jugados + 1;
                    break;

                default:
                    System.out.println("Opcion incorrecta");
                    opcion = '1';

            }

        }

    }

    public void dibuja_tres_en_raya() {
        int filas_tablero = 0;
        int columnas_tablero;

        System.out.println("-------------");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {

                if (tablero[i][j] == 0) {
                    System.out.print("| " + tablero[i][j] + " ");
                }
                if (tablero[i][j] == 1) {
                    System.out.print("| " + "X" + " ");

                }
                if (tablero[i][j] == 3) {
                    System.out.print("| " + "  ");

                }
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("-------------");

    }

    public void inicializa() throws IOException {
        String texto;
        Boolean jugar = false;
        if (jugados > 1) {
            char quiere;
            System.out.println("Quieres jugar otra partida");
            System.out.println("1. Si");
            System.out.println("2. No");
            quiere = teclado.readLine().charAt(0);
            if (Character.getNumericValue(quiere) == 1) {
                jugar = true;
            }
            if (jugar == true) {
                for (int i = 0; i < tablero.length; i++) {

                    for (int j = 0; j < tablero.length; j++) {
                        tablero[i][j] = 3;
                    }
                }
            } else {
                System.out.println("Gracias por jugar, hasta pronto");
                System.exit(0);
            }
        }else{
            for (int i = 0; i < tablero.length; i++) {

                    for (int j = 0; j < tablero.length; j++) {
                        tablero[i][j] = 3;
                    }
                }
        }

    }

    public void comenzar_a_jugar() {
        ArrayList<jugador> jugador_list = new ArrayList<jugador>();
        try {
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            String introduce0;
            String introduceX;
            String nombre1, nombre2;
            boolean fin = false;
            System.out.println("Cual es el nombre del jugador 1 ?");
            nombre1 = teclado.readLine().toUpperCase();
            System.out.println("Cual es el nombre del jugador 2 ?");
            nombre2 = teclado.readLine().toUpperCase();
            System.out.println("¿ "+ nombre1 +" cómo quieres jugar X/0?");
            String juego = teclado.readLine().toUpperCase();
            int valor_juego = 0;
            if (juego.equals("0")) {
                valor_juego = 0;
                
                jugador jugador1 = new jugador(valor_juego, nombre1);
                jugador jugador2 = new jugador(valor_juego + 1, nombre2);
                jugador_list.add(jugador1);
                jugador_list.add(jugador2);
                
            }
            if (juego.equals("X")) {

                valor_juego = 1;
                jugador jugador1 = new jugador(valor_juego, nombre1);
                jugador jugador2 = new jugador(valor_juego - 1, nombre2);
                jugador_list.add(jugador1);
                jugador_list.add(jugador2);

            }
            if (juego.equals("X") || (juego.equals("0"))) {

               

                while (fin == false) {
                    dibuja_tres_en_raya();
                    if (valor_juego == 1) {
                        System.out.println("Te toca X");
                        if (jugador_list.get(0).getValor() == 1) {
                            System.out.println("te toca " + jugador_list.get(0).getName());
                        }else{
                            System.out.println("te toca " + jugador_list.get(1).getName());
                        }
                        
                    }
                    if (valor_juego == 0) {
                        System.out.println("Te toca 0");
                        if (jugador_list.get(0).getValor() == 0) {
                            System.out.println("te toca " + jugador_list.get(0).getName());
                        }else{
                            System.out.println("te toca " + jugador_list.get(1).getName());
                        }

                    }
                    System.out.println("Introduce coordenadas de posición solicitada ");
                    System.out.println("Introduce la fila");
                    int fila = Integer.parseInt(teclado.readLine()) - 1;
                    System.out.println("Introduce la columna");
                    int columna = Integer.parseInt(teclado.readLine()) - 1;
                    if (tablero[fila][columna] == 3) {
                        boolean ganador;
                        tablero[fila][columna] = valor_juego;
                        dibuja_tres_en_raya();
                        ganador = comprobar_ganador(valor_juego);

                        if (ganador) {
                            System.out.println("Ha ganado " + valor_juego);
                            fin = true;
                            inicializa();
                        } else {
                            int estado_empate = 0;
                            for (int i = 0; i <= 2; i++) {
                                for (int j = 0; j <= 2; j++) {
                                    if ((tablero[i][j] == 0) || (tablero[i][j] == 1)) {
                                        estado_empate = estado_empate + 1;
                                    }
                                }
                            }
                            if (estado_empate == 9) {
                                System.out.println("Se ha empatado");
                                fin = true;
                            }
                            if (valor_juego == 1) {
                                valor_juego = 0;
                            } else {
                                if (valor_juego == 0) {
                                    valor_juego = 1;
                                }
                            }
                        }
                    } else {
                        System.out.println("No puede jugar, ya está usado");
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Tictactoe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void comenzar_a_jugar_auto() {
        try {
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            String introduce0;
            String introduceX;
            boolean fin = false;

            System.out.println("Vas a Jugar con la x");
            String juego = "X";
            int valor_juego = 0;
            if (juego.equals("0")) {
                valor_juego = 0;
            }
            if (juego.equals("X")) {

                valor_juego = 1;

            }
            if (juego.equals("X") || (juego.equals("0"))) {

                System.out.println("");

                while (fin == false) {

                    if (valor_juego == 1) {
                        System.out.println("Te toca X");
                        System.out.println("Introduce coordenadas de posición: (0,0) ");
                        System.out.println("Introduce la fila");
                        int fila = Integer.parseInt(teclado.readLine()) - 1;
                        System.out.println("Introduce la columna");
                        int columna = Integer.parseInt(teclado.readLine()) - 1;
                        if (tablero[fila][columna] == 3) {
                            boolean ganador;
                            tablero[fila][columna] = valor_juego;
                            dibuja_tres_en_raya();
                            ganador = comprobar_ganador(valor_juego);
                            if (ganador) {
                                if (valor_juego == 1) {
                                    System.out.println("Has ganadoo!!!");
                                } else {
                                    System.out.println("Has perdido , te ha ganado la maquina");
                                }

                                fin = true;
                                inicializa();
                            } else {
                                int estado_empate = 0;
                                for (int i = 0; i <= 2; i++) {
                                    for (int j = 0; j <= 2; j++) {
                                        if ((tablero[i][j] == 0) || (tablero[i][j] == 1)) {
                                            estado_empate = estado_empate + 1;
                                        }
                                    }
                                }
                                if (estado_empate == 9) {
                                    System.out.println("Se ha empatado");
                                    fin = true;
                                }
                                if (valor_juego == 1) {
                                    valor_juego = 0;
                                } else {
                                    if (valor_juego == 0) {
                                        valor_juego = 1;
                                    }
                                }
                            }
                        } else {
                            System.out.println("No puede jugar, ya está usado");
                        }
                    } else if (valor_juego == 0) {
                        System.out.println("Te toca 0");
                        int numeroAleatorio = (int) (Math.random() * 2 + 0);

                        int fila = numeroAleatorio;
                        numeroAleatorio = (int) (Math.random() * 2 + 0);
                        int columna = numeroAleatorio;

                        if (tablero[fila][columna] == 3) {
                            boolean ganador;
                            tablero[fila][columna] = valor_juego;
                            dibuja_tres_en_raya();
                            ganador = comprobar_ganador(valor_juego);

                            if (ganador) {
                                if (valor_juego == 1) {
                                    System.out.println("Has ganadoo!!!");
                                } else {
                                    System.out.println("Has perdido , te ha ganado la maquina");
                                }

                                fin = true;
                                inicializa();
                            } else {
                                int estado_empate = 0;
                                for (int i = 0; i <= 2; i++) {
                                    for (int j = 0; j <= 2; j++) {
                                        if ((tablero[i][j] == 0) || (tablero[i][j] == 1)) {
                                            estado_empate = estado_empate + 1;
                                        }
                                    }
                                }
                                if (estado_empate == 9) {
                                    System.out.println("Se ha empatado");
                                    fin = true;
                                }
                                if (valor_juego == 1) {
                                    valor_juego = 0;
                                } else {
                                    if (valor_juego == 0) {
                                        valor_juego = 1;
                                    }
                                }
                            }
                        } else {
                            System.out.println("No puede jugar, ya está usado");
                        }

                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Tictactoe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean comprobar_ganador(int valor_juego) {

        if ((tablero[0][0] == valor_juego) && (tablero[0][1] == valor_juego) && (tablero[0][2] == valor_juego)) {
            return true;
        }
        if ((tablero[1][0] == valor_juego) && (tablero[1][1] == valor_juego) && (tablero[1][2] == valor_juego)) {
            return true;
        }
        if ((tablero[2][0] == valor_juego) && (tablero[2][1] == valor_juego) && (tablero[2][2] == valor_juego)) {
            return true;
        }
        if ((tablero[0][0] == valor_juego) && (tablero[2][0] == valor_juego) && (tablero[3][0] == valor_juego)) {
            return true;
        }
        if ((tablero[0][1] == valor_juego) && (tablero[1][1] == valor_juego) && (tablero[2][1] == valor_juego)) {
            return true;
        }
        if ((tablero[0][2] == valor_juego) && (tablero[1][2] == valor_juego) && (tablero[2][2] == valor_juego)) {
            return true;
        }
        if ((tablero[0][0] == valor_juego) && (tablero[1][1] == valor_juego) && (tablero[2][2] == valor_juego)) {
            return true;
        }
        if ((tablero[0][2] == valor_juego) && (tablero[1][1] == valor_juego) && (tablero[2][0] == valor_juego)) {
            return true;
        }
        return false;
    }

}
