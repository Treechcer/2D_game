package MainFunctions;

import javax.swing.*;
import java.awt.*;

class Game extends JPanel implements Runnable{
    boolean running = false;
    Thread gameThread;
    keyboard k = new keyboard();
    public int posx = 0;
    public int posy = 0;
    public int speed = 3;
    public static void main(String[] args){
        Game game = new Game();
        JFrame window = new JFrame("game maybe");
        game.setBackground(Color.BLACK);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(game);
        window.setVisible(true);
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.addKeyListener(game.k);
        game.start();
    }

    public void start(){
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        int FPS = 60;
        int frame = 1000000000/FPS;
        long lastTime = System.nanoTime();
        long thistime;
        int framecount = 0;
        long lastsecframes = System.nanoTime();
        long timemeasure;
        while (running){
            timemeasure = System.nanoTime();
            thistime = System.nanoTime();
            if (timemeasure >= lastsecframes + 1000000000){
                System.out.println(framecount);
                framecount = 0;
                lastsecframes = timemeasure;
            }
            if ((thistime - lastTime) >= frame){
                update();
                lastTime = thistime;
                framecount++;
            }
        }
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        if (k.upPressed == true){
            posy -= speed;
        }
        else if (k.downPressed == true){
            posy += speed;
        }
        else if (k.leftPressed == true){
            posx -= speed;
        }
        else if (k.rightPressed == true){
            posx += speed;
        }
        g2d.fillRect(posx, posy, 50, 50);
    }

}