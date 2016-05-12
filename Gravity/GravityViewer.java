import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * The frame component of the Gravity class
 * 
 * @author Michael Zhang
 * @version 14 April 2016
 */
public class GravityViewer extends JFrame
{   
    final static private int ANIMATION_TIME_IN_SECONDS = 100;
    
    /** Defines the frame's dimensions */
    final static private int FRAME_WIDTH = 1200;
    final static private int FRAME_HEIGHT = 600;
    
    /** Instantiates the panel on which the systems are drawn and the controller to create the systems */
    private DrawingPanel panel;
    private ControlPanel control;

    /**
     * Default constructor for objects of class GravityViewer
     */
    public GravityViewer()
    {
        panel = new DrawingPanel();
        control = new ControlPanel(panel);
        
        this.setLayout(new BorderLayout());
        this.add(control, BorderLayout.EAST);
        this.add(panel, BorderLayout.CENTER);
        
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        

    }

    public static void main (String[] args)
    {
        GravityViewer frame = new GravityViewer();
    }

}
