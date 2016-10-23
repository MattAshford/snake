


import java.awt.image.BufferedImage;
import java.awt.*;
import java.net.*;

/**
 * This class draws the game over screen onto the canvas.
 * 
 * @author Matt Ashford
 */
public class GameOverMenu extends Canvas
{
    public Score scoreclass;

    public GameOverMenu()
    {
        scoreclass = new Score();        
    }
    /**
     * Draws gameover onto the canvas
     */
    public void drawGameOver( Graphics g )
    {
        BufferedImage gameOverImage = new BufferedImage
                                    ( 700, 
                                      500, 
                                      BufferedImage.TYPE_INT_ARGB );
        
        Graphics gameOverGraphics = gameOverImage.getGraphics();        
        gameOverGraphics.setColor( Color.BLACK );
        
        gameOverGraphics.drawString( "Game Over", 50, 50 );        
        //gameOverGraphics.drawString( "Score" + scoreclass.score, 50, 60 );        
        g.drawImage( gameOverImage, 0, 0, this );    
    }
}
