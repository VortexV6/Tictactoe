/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Axel y Mario
 */
public class jugador {
    public int valor;
    public String name;

    public jugador(int valor, String name) {
        this.valor = valor;
        this.name = name;
        if (this.name.equals("")) {
            this.name = "Jugador " + valor;
        }
    }

    

    public int getValor() {
        return valor;
    }

    public String getName() {
        return name;
    }
    
}
