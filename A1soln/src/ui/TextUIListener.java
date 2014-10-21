package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextUIListener extends KeyAdapter {
    private TextUI window;

    public TextUIListener(TextUI window) {
        this.window = window;
    }
  
    public void keyPressed(KeyEvent event) {
    }
  
    public void keyTyped(KeyEvent event) {
        char nextMove = event.getKeyChar();
        this.window.getGame().move(nextMove);
        this.window.updateLabels();
    }


}
