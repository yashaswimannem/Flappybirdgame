import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    Timer timer;
    Bird bird;
    ArrayList<Pipe> pipes;
    int pipeSpawnCounter = 0;
    int score = 0;
    boolean gameOver = false;

    long startTime;
    String elapsedTime = "0.0s";

    public GamePanel() {
        bird = new Bird();
        pipes = new ArrayList<>();
        timer = new Timer(20, this);
        timer.start();
        startTime = System.currentTimeMillis();

        setFocusable(true);
        addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Sky background
        g.setColor(new Color(135, 206, 235)); // sky blue
        g.fillRect(0, 0, 800, 600);

        // Ground
        g.setColor(new Color(222, 184, 135)); // ground brown
        g.fillRect(0, 560, 800, 40);

        bird.draw(g);
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        g.drawString("Score: " + score, 20, 50);

        // Timer display
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        g.drawString("Time: " + elapsedTime, 20, 85);

        if (gameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("GAME OVER", 250, 250);

            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Press ENTER to Restart", 240, 320);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            long currentTime = System.currentTimeMillis();
            double seconds = (currentTime - startTime) / 1000.0;
            elapsedTime = String.format("%.1f s", seconds);

            bird.update();

            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.update();

                if (pipe.checkCollision(bird)) {
                    gameOver = true;
                }

                if (pipe.passed(bird)) {
                    score++;
                }

                if (pipe.isOffScreen()) {
                    pipes.remove(i);
                    i--;
                }
            }

            pipeSpawnCounter++;
            if (pipeSpawnCounter >= 100) {
                pipes.add(new Pipe(800));
                pipeSpawnCounter = 0;
            }

            if (bird.y >= 560 || bird.y <= 0) {
                gameOver = true;
            }

            repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            bird.jump();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER && gameOver) {
            resetGame();
        }
    }

    private void resetGame() {
        bird = new Bird();
        pipes.clear();
        score = 0;
        gameOver = false;
        pipeSpawnCounter = 0;
        startTime = System.currentTimeMillis();
        elapsedTime = "0.0s";
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
