
package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    PlayerDAO playerDAO = new PlayerDAO();
    private int screenHeight = 0;
    private int screenWidth = 0;
    private PlayerComponent player1 = new PlayerComponent(Color.white, new char[] { 'w', 's' },
            playerDAO.readPlayer("Player 1"));
    private PlayerComponent player2 = new PlayerComponent(Color.white, new char[] { 'i', 'k' },
            playerDAO.readPlayer("Player 2"));
    private Score score = new Score(player1, player2);
    private final Ball ball = new Ball(400, 220, Color.white);
    Map<Integer, GamePowers> powers = new HashMap<>();
    private GamePowers crrPower;
    private int maxScore = 3;
    
    private int resetGameCounter = 0;
    private int counter = 0;

    KeyListener kl = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            player1.notifyKeyEvent(ke);
            player2.notifyKeyEvent(ke);
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            player1.notifyKeyEvent(ke);
            player2.notifyKeyEvent(ke);
        }
    };

    Timer t = new Timer(20, (ActionEvent e) -> {
        repaint();
    });

    public void setPlayersPosition() {
        player1.setX(0);
        player1.setY(getHeight() / 3);
        player2.setX(getWidth() - 25);
        player2.setY(getHeight() / 3);
    }

    public void checkBallPosition() {
        int player1Top = player1.getY();
        int player1Bottom = player1.getY() + 160;
        int player2Top = player2.getY();
        int player2Bottom = player2.getY() + 160;

        boolean hitPlayer1 = ball.getX() <= 25 && ball.getY() > player1Top && ball.getY() < player1Bottom;
        boolean hitPlayer2 = ball.getX() >= screenWidth - 65
                && (ball.getY() > player2Top && ball.getY() < player2Bottom);
        boolean outOfFieldLeft = ball.getX() < 0;
        boolean outOfFieldRight = ball.getX() > getWidth() + 80;

        if (outOfFieldLeft) {
            player2.setScore(player2.getScore() + 1);
            System.out.println("player2.getScore " + player2.getScore());
            if(player2.getScore() == maxScore) {
                return;
            }
            resetBallPosition();
            return;
        }
        
        if (outOfFieldRight) {
            player1.setScore(player1.getScore() + 1);
            if(player1.getScore() == maxScore) {
                return;
            }
            resetBallPosition();
            return;
        }

        if (hitPlayer1) {
            ball.setxGrow(true);
            return;
        }

        if (hitPlayer2) {
            ball.setxGrow(false);
        }
    }

    public void resetBallPosition() {
        ball.setX(screenWidth / 2);
        ball.setY(screenHeight / 2);
    }

    public void resetGame() {
        resetBallPosition();
        player1.setScore(0);
        player2.setScore(0);
        setPlayersPosition();
    }

    public void addRandomPower(Graphics g) {
        Random random = new Random();

        int randomIntBounded = random.nextInt(5);

        crrPower = powers.get(randomIntBounded);

        crrPower.activatePower();
    }

    public GamePanel() {
        setBackground(Color.black);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(kl);
        t.start();
        powers.put(0, new IncreaseBallSpeed(ball));
        powers.put(1, new DecreaseBallSize(ball));
        powers.put(2, new DecreasePlayersSize(player1, player2));
        powers.put(3, new FreezePlayers(player1, player2));
        powers.put(4, new ReverseKeysPlayers(player1, player2));
    }

    public void loadPlayersWins (Graphics g) {
        String player1Wins = player1.getPlayerData().getWins() + " wins";
        String player2Wins = player2.getPlayerData().getWins() + " wins";
        g.setFont(new Font("Arial", Font.BOLD, 24));
        
        g.drawString(player1Wins, player1.getX() + 50, 50);
        g.drawString(player2Wins, player2.getX() - 100, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (player1.getScore() == maxScore) {
            g.setColor(Color.GREEN);
            g.drawString("Player 1 wins", 350, 350);
            if(resetGameCounter==0) {
                playerDAO.increasePlayerWins("Player 1");
                playerDAO.increasePlayerDefeats("Player 2");
            }
            resetGameCounter++;
            if(resetGameCounter>100) {
                resetGame();
                resetGameCounter=0;
            } else {
                return;
            }
        } else if (player2.getScore() == maxScore) {
            g.setColor(Color.GREEN);
            g.drawString("Player 2 wins", 350, 350);
            if(resetGameCounter==0) {
                playerDAO.increasePlayerWins("Player 2");
                playerDAO.increasePlayerDefeats("Player 1");
            }
            resetGameCounter++;
            if(resetGameCounter>100) {
                resetGame();
                resetGameCounter=0;
            } else {
                return;
            }
        }

        ball.draw(g, getWidth(), getHeight());
        player1.draw(g, getWidth(), getHeight());
        player2.draw(g, getWidth(), getHeight());
        score.draw(g, getWidth(), getHeight());
        loadPlayersWins(g);

        checkBallPosition();

        if (screenHeight != getHeight() | screenWidth != getWidth()) {
            setPlayersPosition();
        }

        this.screenHeight = getHeight();
        this.screenWidth = getWidth();

        counter++;

        if (counter == 200) {
            addRandomPower(g);
        }

        if (counter == 300) {
            crrPower.deActivatePower();
            counter = 0;
        }

        Toolkit.getDefaultToolkit().sync();
    }
}
