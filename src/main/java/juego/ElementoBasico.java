package main.java.juego;

import java.awt.Color;
import java.awt.Graphics;

public abstract class ElementoBasico implements Elemento {


    private double posicionX;
    private double posicionY;
    private double velocidadX;
    private double velocidadY;
    private int ancho;
    private int largo;
    private Color color;

    public ElementoBasico(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo, Color color) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.ancho = ancho;
        this.largo = largo;
        this.color = color;
    }

    @Override
    public abstract void dibujarse(Graphics graphics);

    // devuelve true si hay colision entre este elemento y el elemento que le pasamos como parametro
    @Override
    public boolean hayColision(Elemento elemento) {
        if (this.getPosicionX() < elemento.getPosicionX() + elemento.getAncho() &&
                this.getPosicionX() + this.getAncho() > elemento.getPosicionX() &&
                this.getPosicionY() < elemento.getPosicionY() + elemento.getLargo() &&
                this.getLargo() + this.getPosicionY() > elemento.getPosicionY()) {
            return true;
        }
        return false;
    }

    @Override
    public void moverse() {
        posicionX = posicionX + velocidadX;
        posicionY = posicionY + velocidadY;
    }

    @Override
    public void rebotarEnEjeX() {
        velocidadX = -velocidadX;
    }

    @Override
    public void rebotarEnEjeY() {
        velocidadY = -velocidadY;
    }

    @Override
    public double getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(double velocidadX) {
        this.velocidadX = velocidadX;
    }

    @Override
    public double getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(double velocidadY) {
        this.velocidadY = velocidadY;
    }

    @Override
    public int getPosicionX() {
        return (int) posicionX;
    }

    @Override
    public int getPosicionY() {
        return (int) posicionY;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    @Override
    public int getAncho() {
        return ancho;
    }

    @Override
    public int getLargo() {
        return largo;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}