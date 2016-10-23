

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import java.awt.*;
import java.net.*;

/**
 * This class generates a random point to place the apple on the canvas and draws
 * the apple onto the canvas.
 * 
 * @author Matt Ashford
 */
public class Apple extends Canvas
{
    public Point apple;
    
    /**
     * Draws apple on canvas
     */
    public void drawApple( Graphics g )
    {
        Image appleIcon = null;
        try
        {
            URL imagePointer = Apple.class.getResource("apple.png");
            appleIcon = Toolkit.getDefaultToolkit().getImage( imagePointer );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        g.setColor( Color.RED );
        g.drawImage( appleIcon, apple.x * Globals.POINT_WIDTH, apple.y * Globals.POINT_HEIGHT, Globals.POINT_WIDTH, Globals.POINT_HEIGHT, this );
        g.setColor( Color.BLACK );        
    }
    /**
     * generates a random point to place the apple
     */
    public void placeApple( LinkedList snake )
    {
        Random rand = new Random();
        int randX = rand.nextInt( Globals.GAME_WIDTH );
        int randY = rand.nextInt( Globals.GAME_HEIGHT );
        Point randPoint = new Point( randX, randY );
        while ( snake.contains( randPoint ) );
        {
            randX = rand.nextInt( Globals.GAME_WIDTH );
            randY = rand.nextInt( Globals.GAME_HEIGHT );
            if ( randX < 10 )
                randX += 10;
        
            if ( randY < 10 )
                randY += 10;
            
            randPoint = new Point( randX, randY );
        }            
        apple = randPoint;
    }
}
