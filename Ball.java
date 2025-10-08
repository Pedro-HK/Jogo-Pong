
import java.awt.Color;
import java.awt.Graphics;

public class Ball implements GameComponent {

    private int x, y;
    private Color color = Color.BLACK;
    private boolean xGrow = false, yGrow = false;
    private int SPEED = 8;
    private int SIZE = 40;

    public Ball(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void draw(Graphics g, int screenWidth, int screenHeight) {
        // if (y < 0) {
        //     yGrow = true;
        // }

        // if ((y + 40) > screenHeight) {
        //     yGrow = false;
        // }
        // if (xGrow) {
        //     x += SPEED;
        // } else {
        //     x -= SPEED;
        // }
        // if (yGrow) {
        //     y += SPEED;
        // } else {
        //     y -= SPEED;
        // }
        g.setColor(color);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public boolean isxGrow() {
        return xGrow;
    }

    public void setxGrow(boolean xGrow) {
        this.xGrow = xGrow;
    }

    public boolean isyGrow() {
        return yGrow;
    }

    public void setyGrow(boolean yGrow) {
        this.yGrow = yGrow;
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

}
