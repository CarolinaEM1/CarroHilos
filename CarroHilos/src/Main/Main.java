/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

/**
 *
 * @author luis2
 */
import javax.swing.*;

public class Main extends JFrame {
    private JButton botonIniciar, botonDetener, botonReiniciar, botonEliminar;
    private JLabel carro;
    private Hilo hilo;
    private int posInicial;

    public Main() {
        super("Carro con hilos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/carro.jpg"));
        carro = new JLabel(imagen);
        hilo = new Hilo(carro);
        posInicial = carro.getX();

        botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(e -> hilo.iniciar());
        botonDetener = new JButton("Detener");
        botonDetener.addActionListener(e -> hilo.detener());
        botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.addActionListener(e -> hilo.reiniciar());
        botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(e -> hilo.eliminar());

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonIniciar);
        panelBotones.add(botonDetener);
        panelBotones.add(botonReiniciar);
        panelBotones.add(botonEliminar);
        panelBotones.add(carro);
        getContentPane().add(panelBotones);

        setVisible(true);
    }

    public static void main(String[] args) {
        Main interfaz = new Main();
    }

    private class Hilo extends Thread {
        private JLabel carro;
        private boolean detener = false;

        public Hilo(JLabel carro) {
            this.carro = carro;
        }

        public void detener() {
            detener = true;
        }

        public void reiniciar() {
            detener = false;
            carro.setLocation(posInicial, carro.getY());
        }

        public void eliminar() {
            detener = true;
            carro.setVisible(false);
        }

        private void moverCarro() {
            int x = carro.getX();
            int y = carro.getY();

            x += 5;
            if (x > getWidth()) {
                x = -carro.getWidth();
            }

            carro.setLocation(x, y);
        }

        @Override
        public void run() {
            while (true) {
                if (!detener) {
                    moverCarro();
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public void iniciar() {
            if (!this.isAlive()) {
                this.start();
            }
            else{
                detener = false;
                if (!carro.isVisible()) {
                    carro.setVisible(true);
                    moverCarro();
                }
            }
        }
    }



}

