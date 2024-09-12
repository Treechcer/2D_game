package MainFunctions;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Game extends JPanel implements Runnable{
    boolean running = false;
    Thread gameThread;
    keyboard k = new keyboard();
    public int posx = 0;
    public int posy = 0;
    public int speed = 3;
    public float velocity = 0;
    public String lastdirection; 
    public int framecount;
    public static int enemyX, enemyY;
    public boolean enemyMovement = true;
    public int moveCount = 0;
    public int colissionCount = 0;
    public boolean caught = false;
    public int enemyWH = 75;
    public int playerWH = 50;

    public static void main(String[] args){
        Game game = new Game();
        Random random = new Random();
        int randomNumber = random.nextInt(100,500);
        enemyX = randomNumber;
        int anotherrandomNumber = random.nextInt(100,500);
        enemyY = anotherrandomNumber;
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
        int max = 10;
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g3d = (Graphics2D) g;
        if (k.upPressed == true){
            if (velocity <= 5 && framecount % 5 == 0){
                velocity += 0.1f;
            }
            posy -= speed + velocity;
            lastdirection = "up";
        }
        else if (k.downPressed == true){
            if (velocity <= 5 && framecount % 5 == 0){
                velocity += 0.1f;
            }
            posy += speed + velocity;
            lastdirection = "down";
        }
        else if (k.leftPressed == true){
            if (velocity <= 5 && framecount % 5 == 0){
                velocity += 0.1f;
            }
            posx -= speed + velocity;
            lastdirection = "left";
        }
        else if (k.rightPressed == true){
            if (velocity <= 5 && framecount % 5 == 0){
                velocity += 0.1f;
            }
            posx += speed + velocity;
            lastdirection = "right";
        }
        else if (k.rightPressed == false && k.leftPressed == false && k.downPressed == false && k.upPressed == false){
            velocity = 0;
        }
        g3d.setColor(Color.RED);
        if (enemyMovement == true){
            if (enemyX + ((posx / 100) * 1) < 5 && enemyX <= posx && enemyX + ((posx / 100) * 1) >= 1){
                enemyX = enemyX + ((posx / 100) * 1);
            }
            else if (enemyX + ((posx / 100) * 1) <= 1){
                enemyY += 1;
            }
            else if (enemyX <= posx){
                enemyX += 5;
            }
            if (enemyX - ((posx / 100) * 1) < 5 && enemyX >= posx && enemyX - ((posx / 100) * 1) >= 1){
                enemyX = enemyX - ((posx / 100) * 1);
            }
            else if (enemyX + ((posx / 100) * 1) <= 1){
                enemyY -= 1;
            }
            else if (enemyX >= posx){
                enemyX -= 5;
            }
            if (enemyY + ((posx / 100) * 1) < 5 && enemyY <= posy && enemyY + ((posx / 100) * 1) >= 1){
                enemyY = enemyY + ((posx / 100) * 1);
            }
            else if (enemyY <= 1){
                enemyY += 1;
            }
            else if (enemyY <= posy){
                enemyY += 5;
            }
            if (enemyY - ((posy / 100) * 1) < 5 && enemyY >= posy && enemyY - ((posx / 100) * 1)>= 1){
                enemyY = enemyY - ((posy / 100) * 1);
            }
            else if (enemyY <= 1){
                enemyY -= 1;
            }
            else if (enemyY >= posy){
                enemyY -= 5;
            }
            enemyMovement = false;
        }
        else{
            moveCount++;
            if (moveCount == 5){
                moveCount = 0;
                enemyMovement = true;
            }
        }
        if (colissionCount == 5){
            if (posx < enemyX + enemyWH && posx + playerWH > enemyX && posy < enemyY + enemyWH && posy + enemyWH > enemyY) {
                caught = true;
            }
            colissionCount = 0;
        }
        if (caught == true){
            System.out.println("you lost, not really yet added restar sry");
        }
        colissionCount++;
        g3d.fillRect(enemyX, enemyY, enemyWH, enemyWH);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(posx, posy, playerWH, playerWH);
    }

}