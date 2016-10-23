

import java.awt.*;

/**
 * This class checks the score to see if it is a highscore. There is also a method 
 * which draws the score onto the screen.
 * 
 * @author Matt Ashford
 */
public class Score
{
    public int score = 0;
    public int highscore = 0;

    public Score()
    { 
    }
    /**
     * checks and sets the highscore
     */
    public void checkScore()
    {
       if( score > highscore )
          highscore = score;
    }      
    /**
     * draws score onto the canvas
     */
    public void drawScore( Graphics g )
    {
        g.drawString("Score:         " + score, Globals.POINT_WIDTH * Globals.GAME_WIDTH + 80, 140 );
        g.drawString("Highscore: " + highscore, Globals.POINT_WIDTH * Globals.GAME_WIDTH + 80, 170 );
    }
    
}
