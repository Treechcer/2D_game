package MainFunctions;

import java.awt.event.*; 
import java.awt.*; 

public class Klavesnice extends Frame implements KeyListener { 
    
    //addKeyListener(this);
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

}
