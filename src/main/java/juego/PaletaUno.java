package main.java.juego;

import java.awt.Color;
import java.awt.Graphics;


public class PaletaUno extends ElementoBasico {



    public PaletaUno(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo, Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
    }

    @Override
    public void dibujarse(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.fillRect(getPosicionX(), getPosicionY(), getAncho(), getLargo());
    }



}
