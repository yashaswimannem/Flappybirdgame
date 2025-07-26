import java.awt.*;

public class Bird {
    int x = 100;
    int y = 250;
    int width = 42;
    int height = 42;

    int velocity = 0;
    int gravity = 1;
    int lift = -12;

    public void update() {
        velocity += gravity;

        if (velocity > 12) velocity = 12;

        y += velocity;

        if (y > 560) {
            y = 560;
            velocity = 0;
        }
        if (y < 0) {
            y = 0;
            velocity = 0;
        }
    }

    public void jump() {
        velocity = lift;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Body gradient
        GradientPaint bodyGradient = new GradientPaint(x, y, new Color(255, 255, 100),
                                                       x, y + height, new Color(255, 204, 0));
        g2.setPaint(bodyGradient);
        g2.fillOval(x, y, width, height);

        // Belly
        g2.setColor(new Color(255, 255, 180, 220));
        g2.fillOval(x + 8, y + 15, 26, 20);

        // Wing
        g2.setColor(new Color(255, 180, 0));
        g2.fillOval(x + 4, y + 18, 16, 12);

        // Eye
        g2.setColor(Color.WHITE);
        g2.fillOval(x + 24, y + 10, 10, 10);
        g2.setColor(Color.BLACK);
        g2.fillOval(x + 27, y + 13, 4, 4);

        // Eye shine
        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillOval(x + 26, y + 11, 3, 3);

        // Beak
        g2.setColor(new Color(255, 120, 0));
        Polygon beak = new Polygon();
        beak.addPoint(x + 38, y + 20);
        beak.addPoint(x + 45, y + 23);
        beak.addPoint(x + 38, y + 26);
        g2.fillPolygon(beak);

        // Blush
        g2.setColor(new Color(255, 105, 180, 100));
        g2.fillOval(x + 8, y + 26, 6, 6);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
