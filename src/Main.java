import javax.swing.JFrame;

//-----------------------------------------------------------------------------
public class  Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Snake");        
        SnakeCanvas game = new SnakeCanvas();

        frame.pack();
        frame.setSize( 700, 500 );
        frame.setResizable( false );
        frame.setLocationRelativeTo( null );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add( game );
        frame.setVisible( true );
        frame.setFocusable( true );
    }    
}
//-----------------------------------------------------------------------------