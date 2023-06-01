import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



public class Board extends JPanel implements Parameters {
    Image ii;
    Timer timer;
    String message = "Игра окончена.";
    private int level;
    Ball ball;
    Paddle paddle;
    Brick[] bricks;
    Wall wall;
    Score scores;
    boolean ingame = true;
    int timerId;

    public Board(Score scores) {
        this.scores = scores;
        this.level = 0;
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        this.bricks = new Brick[30];
        this.setDoubleBuffered(true);
    }

    public void addNotify() {
        super.addNotify();
        this.gameInit();
    }

    public void startTimer() {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new ScheduleTask(), 500L, (long)(15 - this.level));
    }

    public void gameInit() {
        this.ball = new Ball();
        this.paddle = new Paddle();
        this.wall = new Wall();
        int k = 0;
        Random r = new Random();

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 10; ++j) {
                this.bricks[k] = new Brick(j * 75 + 20, i * 35 + 30, 1 + r.nextInt(3));
                ++k;
            }
        }

        this.startTimer();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Font font;
        this.setBackground(Color.BLACK);
        if (this.ingame) {
            g.drawImage(this.ball.getImage(), this.ball.getX(), this.ball.getY(), this.ball.getWidth(), this.ball.getHeight(), this);
            g.drawImage(this.paddle.getImage(), this.paddle.getX(), this.paddle.getY(), this.paddle.getWidth(), this.paddle.getHeight(), this);
            g.drawImage(this.wall.getImage(), this.wall.getX(), this.wall.getY(), this.wall.getWidth(), this.wall.getHeight(), this);


            for(int i = 0; i < 30; ++i) {
                if (!this.bricks[i].isDestroyed()) {
                    g.drawImage(this.bricks[i].getImage(), this.bricks[i].getX(), this.bricks[i].getY(), this.bricks[i].getWidth(), this.bricks[i].getHeight(), this);
                }
            }

            font = new Font("Verdana", 1, 14);
            this.getFontMetrics(font);
            g.setColor(new Color(173,255,47));
            g.setFont(font);
            g.drawString("Счет: " + this.scores, 290, 20);
        } else {
            font = new Font("Verdana", 1, 18);
            FontMetrics metr = this.getFontMetrics(font);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(this.message, (800 - metr.stringWidth(this.message)) / 2, 200);

        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();

    }
    public void stopGame() {
        this.ingame = false;
        this.timer.cancel();
        if (this.scores.checkBestScore()) {
            String name = JOptionPane.showInputDialog((Component)null, "Ваше имя:", "Поздравляю! Вы попали в 5 лучших!", 1);
            this.scores.addBestScore(name);
        }

    }

    public void checkCollision() {
        if (this.ball.getRect().getMaxY() > 390.0) {
            this.stopGame();
        }

        int i = 0;

        int ballLPos;
        for(ballLPos = 0; i < 30; ++i) {
            if (this.bricks[i].isDestroyed()) {
                ++ballLPos;
            }

            if (ballLPos == 30) {
                this.message = "Победа!";
                JOptionPane.showMessageDialog(this, "Уровень пройден!");
                ++this.level;
                this.timer.cancel();
                this.gameInit();
            }
        }

        int x;
        int second;
        int third;
        if (this.ball.getRect().intersects(this.paddle.getRect())) {
            i = (int)this.paddle.getRect().getMinX();
            ballLPos = (int)this.ball.getRect().getMinX();
            x = i + 8;
            second = i + 16;
            third = i + 24;
            int fourth = i + 32;
            if (ballLPos < x) {
                this.ball.setXDir(-3);
                this.ball.setYDir(-3);
            }

            if (ballLPos >= x && ballLPos < second) {
                this.ball.setXDir(-3);
                this.ball.setYDir(-3 * this.ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                this.ball.setXDir(0);
                this.ball.setYDir(-3);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                this.ball.setXDir(3);
                this.ball.setYDir(-3 * this.ball.getYDir());
            }

            if (ballLPos > fourth) {
                this.ball.setXDir(3);
                this.ball.setYDir(-3);
            }
        }

        for(i = 0; i < 30; ++i) {
            if (this.ball.getRect().intersects(this.bricks[i].getRect())) {
                ballLPos = (int)this.ball.getRect().getMinX();
                x = (int)this.ball.getRect().getHeight();
                second = (int)this.ball.getRect().getWidth();
                third = (int)this.ball.getRect().getMinY();
                Point pointRight = new Point(ballLPos + second + 1, third);
                Point pointLeft = new Point(ballLPos - 1, third);
                Point pointTop = new Point(ballLPos, third - 1);
                Point pointBottom = new Point(ballLPos, third + x + 1);
                if (!this.bricks[i].isDestroyed()) {
                    if (this.bricks[i].getRect().contains(pointRight)) {
                        this.ball.setXDir(-3);
                    } else if (this.bricks[i].getRect().contains(pointLeft)) {
                        this.ball.setXDir(3);
                    }

                    if (this.bricks[i].getRect().contains(pointTop)) {
                        this.ball.setYDir(3);
                    } else if (this.bricks[i].getRect().contains(pointBottom)) {
                        this.ball.setYDir(-3);
                    }

                    this.bricks[i].hit();
                    this.scores.setCurrentScore(this.scores.getCurrentScore() + 10);
                }
            }
        }

    }

    class ScheduleTask extends TimerTask {
        ScheduleTask() {
        }

        public void run() {
            Board.this.ball.move();
            Board.this.paddle.move();
            Board.this.checkCollision();
            Board.this.repaint();
        }
    }

    private class TAdapter extends KeyAdapter {
        private TAdapter() {
        }

        public void keyReleased(KeyEvent e) {
            Board.this.paddle.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            Board.this.paddle.keyPressed(e);
        }
    }
}
