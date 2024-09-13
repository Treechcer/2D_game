package MainFunctions;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Game extends JPanel implements Runnable{
    boolean running = false;
    Thread gameThread;
    static keyboard k = new keyboard();
    public static int posx = 30;
    public static int posy = 30;
    public static int speed = 3;
    public static float velocity = 0;
    public static String lastdirection; 
    public static int framecount;
    public static int enemyX, enemyY;
    public static boolean enemyMovement = true;
    public static int moveCount = 0;
    public static int colissionCount = 0;
    public static boolean caught = false;
    public static int enemyWH = 75;
    public static int playerWH = 50;
    public static int score = 0;

    public static void main(String[] args){
        Game game = new Game();
        Random random = new Random();
        int randomNumber = random.nextInt(100,750);
        enemyX = randomNumber;
        int anotherrandomNumber = random.nextInt(100,750);
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
                if (framecount % 12 == 1){
                    score += 1;
                }
            }
        }
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g3d = (Graphics2D) g;
        g3d.setColor(Color.RED);
        playerMovement();
        if (colissionCount == 2){
            collision();
        }
        enemyAI();
        colissionCount++;
        g3d.fillRect(enemyX, enemyY, enemyWH, enemyWH);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(posx, posy, playerWH, playerWH);
        g2d.setColor(Color.gray);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 10, 30);
    }

    public static void collision(){
        if (posx < enemyX + enemyWH && posx + playerWH > enemyX && posy < enemyY + enemyWH && posy + playerWH > enemyY) {
            caught = true;
        }
        colissionCount = 0;
        if (caught == true){
            System.out.println("you lost");
            posx = 0;
            posy = 0;
            Random random = new Random();
            int randomNumber = random.nextInt(100,500);
            enemyX = randomNumber;
            int anotherrandomNumber = random.nextInt(100,500);
            enemyY = anotherrandomNumber;
            caught = false;
        }
    }

    public static void enemyAI() {
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
            else {
                moveCount++;
                if (moveCount == 5){
                    moveCount = 0;
                    enemyMovement = true;
                }
            }
            if (enemyX >= 1270){
                enemyX = 2;
            }
            else if (enemyX <= 0){
                enemyX = 1260;
            }
            else if (enemyY >= 710){
                enemyY = 2;
            }
            else if (enemyY <= 5){
                enemyY  = 705;
            }
        }

    public static void playerMovement(){
        if (posx >= 1270){
            posx = 2;
        }
        else if (posx <= 0){
            posx = 1260;
        }
        else if (posy >= 710){
            posy = 2;
        }
        else if (posy <= 5){
            posy = 705;
        }
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
    }
}