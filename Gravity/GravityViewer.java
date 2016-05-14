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
    /** Defines the frame's dimensions */
    final static private int FRAME_WIDTH = 1350;
    final static private int FRAME_HEIGHT = 700;
    
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
        panel.readControls(control);
        
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(control, BorderLayout.EAST);
        
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main (String[] args)
    {
        GravityViewer frame = new GravityViewer();
    }

}
