package main.java.juego;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Tiagocae32
 */
public class Pelota extends ElementoBasico {
    public Pelota(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo, Color color) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
    }

    public void dibujarse(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.fillOval(getPosicionX(), getPosicionY(), getAncho(), getLargo());
    }
}
