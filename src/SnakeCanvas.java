

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import java.awt.*;
import java.net.*;
import javax.swing.*;


/**
 * This class inserts a canvas into the JFrame and paints the GUI onto the canvas.
 * 
 * @author Matt Ashford
 */
public class SnakeCanvas extends Canvas implements Runnable, KeyListener
{
      
    private Thread runThread;
    public boolean inMenu = true;
    public boolean gameOver = false;
    
    public Apple appleclass;
    public Snake snakeclass;
    public GameOverMenu gameoverclass;
    public MainMenu menuclass;
    
    
    /**
     * This is the paint method that is called everytime the canvas is repainted.
     */
    public void paint( Graphics g )
    {
        if ( runThread == null )
        {
            appleclass = new Apple();
            snakeclass = new Snake();
            gameoverclass = new GameOverMenu();
            menuclass = new MainMenu();
            
            this.setPreferredSize( new Dimension( 640, 480 ) );
            this.addKeyListener( this );        
            runThread = new Thread( this );
            runThread.start();
        } 
        if ( inMenu )
        {
            menuclass.drawMenu(g);
        }
        else if ( gameOver )
        {
            gameoverclass.drawGameOver( g );
        }
        else
        {
            if( snakeclass.snake  == null )
            {
                snakeclass.instantiateSnake();
                snakeclass.spawnDefaultSnake();
                appleclass.placeApple( snakeclass.snake );           
            }
            snakeclass.drawSnake( g );
            appleclass.drawApple( g );
            gameoverclass.scoreclass.drawScore( g );
        }       
    }
    /**
     * Implements double buffer.
     */
    public void update( Graphics g )
    {
        Graphics offScreenGraphics;
        BufferedImage offScreen = null;
        Dimension d = this.getSize();
        
        Image background = null;
        try
        {
            URL imagePath = SnakeCanvas.class.getResource( "background.png" );
            background = Toolkit.getDefaultToolkit().getImage( imagePath );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        
        offScreen = new BufferedImage( d.width, d.height, BufferedImage.TYPE_INT_ARGB );
        offScreenGraphics = offScreen.getGraphics();
        offScreenGraphics.drawImage( background, 0, 0, d.width, d.height, this );
        offScreenGraphics.setColor( this.getForeground() );
        paint( offScreenGraphics );
        
        //flip
        g.drawImage( offScreen, 0, 0, this );        
    }
    /**
     * Moves the snake by adding the next coordinate point to the snake linkedlist
     * and removing the last coordinate point from the linked list. Also includes
     * statements to detect if the snake has gone out of bounds, hit an apple, or run
     * into itself.
     */
   private void move()
    {
        if ( snakeclass.direction == Globals.NO_DIRECTION )
            return;
            
        Point head = snakeclass.snake.peekFirst();
        Point nextPoint = head;
        
        
        switch ( snakeclass.direction ) 
        {
        case Globals.NORTH:
            nextPoint = new Point( head.x, head.y - 1 );
            break;
        case Globals.SOUTH:
            nextPoint = new Point( head.x, head.y + 1 );
            break;
        case Globals.WEST:
            nextPoint = new Point( head.x - 1, head.y );
            break;
        case Globals.EAST:
            nextPoint = new Point( head.x + 1, head.y );
            break;
        }        
        //if ( snakeclass.direction != Globals.NO_DIRECTION )
            snakeclass.snake.remove( snakeclass.snake.peekLast() );
        
        if ( nextPoint.equals( appleclass.apple ) )
        {
            // The snake has hit the fruit, add 1 to the score,
            // add a point to body of snake and place a new apple
            gameoverclass.scoreclass.score += 1;
            Point addPoint = ( Point ) nextPoint.clone();
            snakeclass.snake.push( addPoint );
            appleclass.placeApple( snakeclass.snake );
        }
        else if ( nextPoint.x < 1 || nextPoint.x > ( Globals.GAME_WIDTH -1 ) )
        {
            //out of bounds           
            gameoverclass.scoreclass.checkScore();
            gameoverclass.scoreclass.score = 0;
            gameOver = true;
            return;
        }
        else if ( nextPoint.y < 1 || nextPoint.y > ( Globals.GAME_HEIGHT - 1 ) )
        {
            // out of bound
            gameoverclass.scoreclass.checkScore();
            gameoverclass.scoreclass.score = 0;
            gameOver = true;
            return;
        }
        else if ( snakeclass.snake.contains( nextPoint ) )
        {
            // ran into yourself, reset game
            gameoverclass.scoreclass.checkScore();
            gameoverclass.scoreclass.score = 0;
            gameOver = true;
            return;
        }        
        // if we get to this point then were still good.
        snakeclass.snake.push( nextPoint );        
    }  
    @Override
    public void run() 
    {
        while ( true )
        {
            repaint();
            if ( !inMenu && !gameOver )
                move();           
            
            try
            {
                Thread.currentThread();
                Thread.sleep( 50 );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }   
    }    
    //----------------------------------------------------------------------------------------
    @Override
    public void keyReleased( KeyEvent arg0 ){}  
    //----------------------------------------------------------------------------------------
    @Override
    public void keyTyped( KeyEvent arg0) {}
    //----------------------------------------------------------------------------------------
    /**
     * Detects and handles user input
     */
    @Override
    public void keyPressed( KeyEvent e ) 
    {
        switch ( e.getKeyCode() )
        {
        case KeyEvent.VK_UP:
            if( snakeclass.direction != Globals.SOUTH )
                snakeclass.direction =  Globals.NORTH;
            break;
        case KeyEvent.VK_DOWN:
            if( snakeclass.direction != Globals.NORTH )
                snakeclass.direction =  Globals.SOUTH;
            break;
        case KeyEvent.VK_RIGHT:
            if( snakeclass.direction != Globals.WEST )
                snakeclass.direction = Globals.EAST;
            break;
        case KeyEvent.VK_LEFT:
            if( snakeclass.direction != Globals.EAST )
                snakeclass.direction =  Globals.WEST;
            break;
        case KeyEvent.VK_ENTER:
            if ( inMenu )
            {
                inMenu = false;                
                repaint();                
            }
            else if ( gameOver )
            {
                gameOver = false;
                snakeclass.spawnDefaultSnake();
                repaint();
            }
            break;
        case KeyEvent.VK_BACK_SPACE:
            if ( gameOver )
            {
                gameOver = false;
                inMenu = true;
                repaint();
                snakeclass.spawnDefaultSnake();
            }
            break;
        }      
    } 

}
//----------------------------------------------------------------------------------------