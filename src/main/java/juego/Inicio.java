package main.java.juego;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Inicio extends JPanel  {
    private static final long serialVersionUID = 1L;
    private int anchoJuego;
    private int largoJuego;

    public Inicio(int anchoJuego, int largoJuego) {
        this.anchoJuego=(anchoJuego);
        this.largoJuego = largoJuego;
    }

    public Dimension getPreferredSize() {
        return new Dimension(anchoJuego, largoJuego);
    }

}