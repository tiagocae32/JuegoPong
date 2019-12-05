package main.java.juego;

import java.awt.*;
import javax.swing.*;


public class ImagenDeFondo {

    private int posicionX;
    private int posicionY;

    public ImagenDeFondo() {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }


    public void dibujarse( Graphics g) {
        ImageIcon imagen = new ImageIcon ( new ImageIcon (getClass().getResource("/imagenes/tenis.jpg")).getImage());
        g.drawImage(imagen.getImage(),0,0,600,400,null);
    }
}



