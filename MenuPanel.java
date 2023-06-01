import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class MenuPanel extends JPanel implements Parameters {
    public JButton start;
    public JButton bestScores;
    public JButton howto;
    public JButton exit;
    public Main parent;
    public Score scores;

    public MenuPanel(final Main parent, final Score scores) {
        this.parent = parent;
        this.scores = scores;
        this.start = new JButton("Начать");
        this.start.setBounds(295, 150, 200, 50);
        this.bestScores = new JButton("Лучший счет");
        this.bestScores.setBounds(295, 205, 200, 50);
        this.howto = new JButton("Правила");
        this.howto.setBounds(295, 260, 200, 50);
        this.exit = new JButton("Выход");
        this.exit.setBounds(295, 315, 200, 50);
        this.setLayout((LayoutManager)null);
        this.start.setBackground(Color.GREEN);
        this.bestScores.setBackground(Color.GREEN);
        this.howto.setBackground(Color.GREEN);
        this.exit.setBackground(Color.GREEN);
        this.add(this.start);
        this.add(this.bestScores);
        this.add(this.howto);
        this.add(this.exit);
        this.howto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog((Component)null, "Цель игры: сбить все кирпичи\nКирпичи имеют разную прочность: от 1 до 3\nЗа каждое попадание по кирпичику начисляется 10 очков", "Помощь", 1);
            }
        });
        this.start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(false);
                new Game(parent, scores);
            }
        });
        this.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.dispose();
                System.exit(0);
            }
        });
        this.bestScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "";
                int[] bestScores = scores.getBestScores();
                String[] bestScoresName = scores.getBestScoresName();

                for(int i = 0; i < bestScores.length && bestScores[i] != 0; ++i) {
                    message = message + bestScoresName[i] + " - " + bestScores[i] + "\n";
                }

                JOptionPane.showMessageDialog((Component)null, message, "Лучшие счета", 1);
            }
        });
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
    }
}
