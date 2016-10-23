/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.image.BufferedImage;
import java.awt.*;
import java.net.*;

/**
 * This class retreives the main menu image from the package and draws it onto the canvas.
 * 
 * @author Matt Ashford 
 */
public class MainMenu extends Canvas
{
    private Image menubackground = null;

    public MainMenu()
    {
    }

    /**
     * Draws the main menu onto the canvas
     */
    public void drawMenu( Graphics g )
    {
        if ( this.menubackground == null )
        {
            try
            {
                URL imagePointer = MainMenu.class.getResource( "menubackground.png" );
                this.menubackground = Toolkit.getDefaultToolkit().getImage( imagePointer );
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }        
       g.drawImage( menubackground, 0, 0, this );       
    }
}
