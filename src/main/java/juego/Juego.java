package main.java.juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class Juego extends JComponent implements KeyListener, Runnable {
    private static final long serialVersionUID = 1L;
    private int anchoJuego;
    private int largoJuego;
    private int tiempoDeEsperaEntreActualizaciones;
    private  ImagenDeFondo imagen;
    private ElementoBasico pelota;
    private ElementoBasico paletaUno;
    private ElementoBasico paletaDos;
    private ElementoBasico paletaTres;
    private ElementoBasico paletacuatro;
    private Puntaje puntajeUno;
    private Puntaje puntajeDos;
    private boolean jugadorGanadorUno;
    private boolean jugadorGanadorDos;
    private boolean pararJuego;
    private boolean juegoCorriendo;
    private Sonidos sonidos;


    public Juego(int anchoJuego , int largoJuego, int tiempoDeEsperaEntreActualizaciones){
        this.anchoJuego = anchoJuego;
        this.largoJuego = largoJuego;
        this.tiempoDeEsperaEntreActualizaciones = tiempoDeEsperaEntreActualizaciones;
        this.pelota = createPelota();
        this.paletaUno = new PaletaUno(0, largoJuego - 450, 0, 0, 10, 45, Color.BLUE);
        this.paletaTres = new PaletaTres(0, largoJuego - 150, 0, 0, 10, 45, Color.YELLOW);
        this.paletaDos = new PaletaDos(790, largoJuego - 450, 0, 0, 10, 45, Color.RED);
        this.paletacuatro = new PaletaCuatro(790, largoJuego - 150, 0, 0, 10, 45, Color.CYAN);
        this.puntajeUno = new Puntaje(20, 20, new Font("Arial", 8, 20), Color.WHITE);
        this.puntajeDos = new Puntaje(680, 20, new Font("Arial", 8, 20), Color.WHITE);
        this.imagen = new ImagenDeFondo();
        this.pararJuego = false;
        this.juegoCorriendo = true;

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(anchoJuego, largoJuego);
    }

    /*
     * Actualizar la actualizacion y el dibujado del juego de esta forma no es
     * recomendable dado que tendra distintas velocidades en distinto hardware Se
     * hizo asi por simplicidad para facilitar el aprendizaje Lo recomendado es
     * separar la parte de dibujado de la de actualizacion y usar interpolation
     */
    @Override
    public void run() {
        while (juegoCorriendo) {
            actualizarJuego();
            dibujarJuego();
            esperar(tiempoDeEsperaEntreActualizaciones);
        }
    }

    @Override
    public void keyPressed(KeyEvent arg0) {

        if (arg0.getKeyCode() == 87) {
            paletaUno.setVelocidadY(-1);
            paletaTres.setVelocidadY(-1);
        }

        if (arg0.getKeyCode() == 83) {
            paletaUno.setVelocidadY(1);
            paletaTres.setVelocidadY(1);
        }

        if (arg0.getKeyCode() == 38) {
            paletaDos.setVelocidadY(-1);
            paletacuatro.setVelocidadY(-1);
        }

        if (arg0.getKeyCode() == 40) {
            paletaDos.setVelocidadY(1);
            paletacuatro.setVelocidadY(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // si suelto la tecla 39 o la 37 se asigna velocidad 0 a la paleta
        if (arg0.getKeyCode() == 87 || arg0.getKeyCode() == 83) {
            paletaUno.setVelocidadY(0);
            paletaTres.setVelocidadY(0);
        }

        if(arg0.getKeyCode() == 40 || arg0.getKeyCode() == 38){
            paletaDos.setVelocidadY(0);
            paletacuatro.setVelocidadY(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    // Este metodo se llama cuando se hace un this.repaint() automaticamente
    // Aca se dibujan a todos los elementos, para ello cada elemento implementa el metodo dibujarse
    protected void paintComponent(Graphics g) {
        limpiarPantalla(g);
        // si el juego no esta parado entonces dibujar todos los elementos y los enemigos
        if (!pararJuego) {
            //imagen.dibujarse(g);
            paletaUno.dibujarse(g);
            paletaDos.dibujarse(g);
            paletaTres.dibujarse(g);
            paletacuatro.dibujarse(g);
            puntajeUno.dibujarse(g);
            puntajeDos.dibujarse(g);
            pelota.dibujarse(g);
        } else {
            // si el juego esta parado entonces dibujar el fin del juego y cambiar el atributo juegoCorriendo a false
            dibujarFinJuego(g);
            juegoCorriendo = false;
        }
    }

    // En este metodo se actualiza el estado de todos los elementos del juego
    private void actualizarJuego() {
        verificarEstadoAmbiente();
        pelota.moverse();
        moverPaleta(paletaUno);
        moverPaleta(paletaDos);
        moverPaleta(paletaTres);
        moverPaleta(paletacuatro);
    }

    private void dibujarJuego() {
        this.repaint();
    }

    // En este metodo verifico las colisiones, los rebotes de la pelota contra las paredes, la colision entre enemigos y el fin de juego
    private void verificarEstadoAmbiente() {
        verificarReboteEntrePelotaYPaletaUnoTres();
        verificarReboteEntrePelotaYPaletaDosCuatro();
        verificarSiPelotaTocaElPiso();
        verificarGolJugador1();
        verificarGolJugador2();
        verificarRebotePelotaContraLaParedSuperior();
        verificarFinDeJuego();
    }

    //verifico si la paleta del jugador de la izquierda hace un gol
    private void verificarGolJugador1() {
        if (pelota.getPosicionX() + pelota.getAncho() >= anchoJuego) {
            puntajeUno.sumarPunto();
            this.pelota = createPelota();
            this.pelota.setVelocidadY(1.7);
            this.pelota.setVelocidadX(1.7);
        }
    }

    //verifico si la paleta de la derecha hace un gol
    private void verificarGolJugador2() {
        if (pelota.getPosicionX() <= 0) {
            puntajeDos.sumarPunto();
            this.pelota = createPelota();
            this.pelota.setVelocidadY(1.7);
            this.pelota.setVelocidadX(1.7);
        }
    }

    //en funcion de que si las paletas no sobrepasan los limites de la ventana, muevo las pelotas hacia arriba o hacia abajo
    private void moverPaleta(Elemento elemento) {
        if (!hayColisionConBordeYendoHaciaAbajo(elemento) && !hayColisionConBordeYendoHaciaArriba(elemento) ) {
            elemento.moverse();
        }
    }

/*
    private boolean hayColisionEntrePaletas(){
        return true;
    }

 */
    private boolean hayColisionConBordeYendoHaciaAbajo(Elemento elemento) {
        if (elemento.getPosicionY() + elemento.getLargo() >= largoJuego && elemento.getVelocidadY() == 1) {
            return true;
        }
        return false;
    }

    private boolean hayColisionConBordeYendoHaciaArriba(Elemento elemento) {
        if (elemento.getPosicionY()  <= 0 && elemento.getVelocidadY() == -1) {
            return true;
        }
        return false;
    }

    // se verifica si hay colision entre la paleta y la pelota. Si hay colision se cambia la direccion de la pelota en el eje Y
    private void verificarReboteEntrePelotaYPaletaUnoTres() {
        if (paletaUno.hayColision(pelota) || paletaTres.hayColision(pelota)) {
            pelota.rebotarEnEjeX();
            //sonidos.tocarSonido("toc");
        }
    }

    // se verifica si hay colision entre la paleta y la pelota. Si hay colision se cambia la direccion de la pelota en el eje Y
    private void verificarReboteEntrePelotaYPaletaDosCuatro() {
        if (paletaDos.hayColision(pelota) || paletacuatro.hayColision(pelota)) {
            pelota.rebotarEnEjeX();
            //sonidos.tocarSonido("tic");
        }
    }

    private void verificarSiPelotaTocaElPiso() {
        if (pelota.getPosicionY() + pelota.getLargo() >= largoJuego) {
            pelota.rebotarEnEjeY();
        }
    }

    // se verifica si la pelota colisiona contra la pared superior, si es asi se hace rebotar la pelota en el eje Y
    private void verificarRebotePelotaContraLaParedSuperior() {
        if (pelota.getPosicionY() <= 0) {
            pelota.rebotarEnEjeY();
        }
    }

    private void verificarFinDeJuego(){
        if(puntajeUno.getPuntaje() == 3){
            jugadorGanadorUno = true;
            pararJuego = true;
        }
        if(puntajeDos.getPuntaje() == 3){
            jugadorGanadorDos = true;
            pararJuego = true;
        }
    }

    // Este metodo se usa para mostrar un mensaje
    private void mostrarMensaje(Graphics g,String mensaje) {
        this.limpiarPantalla(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 8, 30));
        g.drawString(mensaje, 150, 300);
    }

    // dibujar el fin del juego
    private void dibujarFinJuego(Graphics g) {
        if(jugadorGanadorUno){
            mostrarMensaje(g, "Fin del juego, ganador Jugador uno! ");
        }
        if(jugadorGanadorDos){
            mostrarMensaje(g, "Fin del juego, ganador Jugador dos!");
        }
    }

    private ElementoBasico createPelota() {
        return new Pelota(anchoJuego / 2, largoJuego - 50, 1.2, 1.2, 15, 15, Color.LIGHT_GRAY);
    }

    // En ese metodo se cargan los sonidos que estan es src/main/resources
    private void cargarSonidoUno() {
        /*try {
            sonidos = new Sonidos();
            sonidos.agregarSonido("toc", "/sonidos/toc.wav");
            sonidos.agregarSonido("tic", "/sonidos/tic.wav");
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
         */
    }

    // metodo para limpiar la pantalla
    private void limpiarPantalla(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, anchoJuego, largoJuego);
    }

    // metodo para esperar una cantidad de milisegundos
    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
}