package main.java.juego;

import java.awt.Color;
import java.awt.Graphics;

public interface Elemento {

    public int getPosicionX();

    public int getPosicionY();

    public int getAncho();

    public int getLargo();

    public double getVelocidadX();

    public double getVelocidadY();

    public Color getColor();

    public void dibujarse(Graphics graphics);

    public void moverse();

    public boolean hayColision(Elemento elemento);

    public void rebotarEnEjeX();

    public void rebotarEnEjeY();



}