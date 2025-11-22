

package com.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class PlayerComponent implements GameComponent, InteractiveComponent {

    private int x = 0, y = 0;
    private Color color = Color.BLACK;
    private char keys[] = null;
    private boolean up = false, down = false;
    private int SPEED = 8;
    private int SIZE = 160;
    private int score = 0;
    private Player playerData;

    public PlayerComponent(Color color, char[] keys, Player playerData) {
        this.color = color;
        this.keys = keys;
        this.playerData = playerData;
    }

    @Override
    public void draw(Graphics g, int screenWidth, int screenHeight) {
        if (up) {
            y -= SPEED;
            if (y < 0) {
                y = 0;
            }
        }

        if (down) {
            y += SPEED;
            if (y + SIZE > screenHeight) {
                y = screenHeight - SIZE;
            }
        }

        g.setColor(color);
        g.fillRect(x, y, 25, SIZE);
    }

    @Override
    public void notifyKeyEvent(KeyEvent evt) {
        if (keys != null) {
            switch (evt.getID()) {
                case KeyEvent.KEY_PRESSED -> {
                    if (evt.getKeyChar() == keys[0]) {
                        up = true;
                    } else if (evt.getKeyChar() == keys[1]) {
                        down = true;
                    }
                }
                case KeyEvent.KEY_RELEASED -> {
                    if (evt.getKeyChar() == keys[0]) {
                        up = false;
                    } else if (evt.getKeyChar() == keys[1]) {
                        down = false;
                    }
                }
            }
        } else {
            up = false;
            down = false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player getPlayerData() {
        return playerData;
    }

    public void setPlayerData(Player playerData) {
        this.playerData = playerData;
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    public char[] getKeys() {
        return keys;
    }

    public void setKeys(char[] keys) {
        this.keys = keys;
    }
}
