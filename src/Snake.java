

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.awt.*;
import java.net.*;

/**
 * This class creates a linkedlist which will contain the point coordinates of the snakes current
 * location. This class also contains the methods to draw the snake onto the canvas and spawn the 
 * snake at the default location.
 * 
 * @author Matt Ashford
 */
public class Snake
{
    public LinkedList<Point> snake;
    public int direction = Globals.NO_DIRECTION;

    public Snake()
    {
    }
    /**
     * Clears the snake linked list and inserts default points
     */
    public void spawnDefaultSnake()
    {
        snake.clear();
        snake.add( new Point( 16, 16 ) );
        snake.add( new Point( 16, 17 ) );
        snake.add( new Point( 16, 18 ) );
        direction = Globals.NO_DIRECTION;
        
    }
    /**
     * Draws snake on the canvas
     */
    public void drawSnake( Graphics g )
    {
        g.setColor( Color.GREEN );
        // for every point in snake, set colour to green.
        for ( Point p : snake )
        {
            g.fillRect( p.x * Globals.POINT_WIDTH, p.y * Globals.POINT_HEIGHT, Globals.POINT_WIDTH, Globals.POINT_HEIGHT );
        }
        g.setColor( Color.WHITE );        
    }

    public void instantiateSnake()
    {
        snake = new LinkedList<Point>();
    }
}
