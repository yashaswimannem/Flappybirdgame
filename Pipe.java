import java.awt.*;
import java.util.Random;

public class Pipe {
    int x;
    int width = 80;
    int gap = 150;
    int topHeight;
    int speed = 4;

    boolean scored = false;

    public Pipe(int startX) {
        x = startX;
        topHeight = 100 + new Random().nextInt(250);
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(34, 139, 34)); // green

        // Top pipe body and head
        g2d.fillRect(x, 0, width, topHeight);
        g2d.fillRect(x - 5, topHeight - 20, width + 10, 20); // head

        // Bottom pipe body and head
        g2d.fillRect(x, topHeight + gap, width, 600 - (topHeight + gap));
        g2d.fillRect(x - 5, topHeight + gap, width + 10, 20); // head
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public Rectangle getTopBounds() {
        return new Rectangle(x, 0, width, topHeight);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle(x, topHeight + gap, width, 600 - (topHeight + gap));
    }

    public boolean checkCollision(Bird bird) {
        return bird.getBounds().intersects(getTopBounds()) || bird.getBounds().intersects(getBottomBounds());
    }

    public boolean passed(Bird bird) {
        if (!scored && bird.x > x + width) {
            scored = true;
            return true;
        }
        return false;
    }
}
