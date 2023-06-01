import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Score {
    private int[] bestScores = new int[5];
    private String[] bestScoresName = new String[5];
    private int currentScore = 0;

    public Score() {
        try {
            Scanner in = new Scanner(new File("scores.txt"));
            int n = Integer.parseInt(in.nextLine());
            for(int i = 0; i < n; ++i) {
                String s = in.nextLine();
                StringTokenizer st = new StringTokenizer(s, " ");
                this.bestScoresName[i] = st.nextToken();
                this.bestScores[i] = Integer.parseInt(st.nextToken());
            }
        } catch (FileNotFoundException var6) {
        }

    }

    public boolean checkBestScore() {
        for(int i = 0; i < 5; ++i) {
            if (this.currentScore > this.bestScores[i]) {
                return true;
            }
        }

        return false;
    }

    public void addBestScore(String name) {
        for(int i = 0; i < 5; ++i) {
            if (this.currentScore > this.bestScores[i]) {
                for(int j = 4; j > i; --j) {
                    this.bestScores[j] = this.bestScores[j - 1];
                    this.bestScoresName[j] = this.bestScoresName[j - 1];
                }

                this.bestScores[i] = this.currentScore;
                this.bestScoresName[i] = name;
                break;
            }
        }

        try {
            PrintWriter out = new PrintWriter("scores.txt");
            boolean ok = false;

            int i;
            for(i = 0; i < 5; ++i) {
                if (this.bestScores[i] == 0) {
                    out.println(i);
                    ok = true;
                    break;
                }
            }

            if (!ok) {
                out.println(5);
            }

            for(i = 0; i < 5 && this.bestScores[i] != 0; ++i) {
                out.println(this.bestScoresName[i] + " " + this.bestScores[i]);
            }

            out.close();
        } catch (FileNotFoundException var5) {
        }

    }

    public int[] getBestScores() {
        return this.bestScores;
    }

    public void setBestScores(int[] bestScores) {
        this.bestScores = bestScores;
    }

    public int getCurrentScore() {
        return this.currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public String[] getBestScoresName() {
        return this.bestScoresName;
    }

    public void setBestScoresName(String[] bestScoresName) {
        this.bestScoresName = bestScoresName;
    }

    public String toString() {
        String result;
        for(result = Integer.toString(this.currentScore); result.length() < 5; result = "0" + result) {
        }

        return result;
    }
}
