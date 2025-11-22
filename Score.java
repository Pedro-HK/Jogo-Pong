
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score implements GameComponent {
    private PlayerComponent player1;
    private PlayerComponent player2;
    private Font scoreFont;

    public Score(PlayerComponent player1, PlayerComponent player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.scoreFont = new Font("Arial", Font.BOLD, 36);
    }

    @Override
    public void draw(Graphics g, int screenWidth, int screenHeight) {
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        String scoreText = player1.getScore() + " : " + player2.getScore();
        int textWidth = g.getFontMetrics().stringWidth(scoreText);
        g.drawString(scoreText, (screenWidth - textWidth) / 2, 50);
    }
}