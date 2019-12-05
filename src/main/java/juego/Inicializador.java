package main.java.juego;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Inicializador extends JPanel  {
    //private static final long serialVersionUID = 1L;
    public static void main(String[] args) {
        // Propiedades del Juego
        int anchoVentana = 800;
        int largoVentana = 600;
        int tiempoDeEsperaEntreActualizaciones = 4;

        // Activar aceleracion de graficos en 2 dimensiones
        System.setProperty("sun.java2d.opengl", "true");

        // Crear un objeto de tipo JFrame que es la ventana donde va estar el juego
        JFrame ventana = new JFrame("Juego Pong");

        // Cerrar la aplicacion cuando el usuario hace click en la 'X'
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Abrir la ventana en el centro de la pantalla
        //ventana.setLocation(300,10);
        ventana.setLocationRelativeTo(null);

        //no dejar que el usuario pueda agrandar la ventana
        ventana.setResizable(false);

        // Mostrar la ventana
        ventana.setVisible(true);

        Inicio  pantallaInicio = new Inicio (anchoVentana, largoVentana);
        //Nivel pantallaNivel = new Nivel(anchoVentana, largoVentana);
        Juego juego = new Juego(anchoVentana, largoVentana, tiempoDeEsperaEntreActualizaciones);

        ventana.add(pantallaInicio);
        ventana.pack();

           //Boton para entrar al juego
           JButton button = new JButton();
           button.setText("START");
           button.setForeground(Color.black);
           button.setBackground(Color.blue);
           pantallaInicio.add(button);

           //Boton para salir de la ventana
           JButton button2 = new JButton();
           button2.setText("E X I T");
           button2.setForeground(Color.black);
           button2.setBackground(Color.red);
           pantallaInicio.add(button2);
           //accion del boton de inicio, agrega la pantalla del juego, cierra la pantalla de inicio, y prepara el teclado
           button.addActionListener(e -> {
               ventana.add(juego);
               pantallaInicio.setVisible(false);
               juego.setVisible(true);
               ventana.addKeyListener(juego);
               ventana.pack();

               // Crear un thread y pasarle como parametro al juego que implementa la interfaz
               // "Runnable"
               Thread thread = new Thread(juego);
               // Arrancar el juego

               thread.start();
           });
        button.setFocusable(false);
           //accion del boton de salida, cierra toda la ventana
           button2.addActionListener( e1 -> {
               Window w1 = SwingUtilities.getWindowAncestor(pantallaInicio);
               w1.setVisible(false);
           });
       }
}