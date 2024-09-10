package MainFunctions;

import java.awt.event.*; 
import java.awt.*; 

public class Klavesnice extends Frame implements KeyListener { 
    
    //addKeyListener(this);
    public boolean upPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

}
