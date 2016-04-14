import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Write a description of class ControlPanel here.
 * 
 * @author Michael Zhang
 * @version 14 April 2016
 */
public class ControlPanel
{
    /** description of instance variable x (add comment for each instance variable) */
    private JButton set;
    private JOptionPane
    
    private DrawingPanel panel;

    /**
     * Default constructor for objects of class ControlPanel
     */
    public ControlPanel()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *    that describes the operation of the method
     *
     * @pre        preconditions for the method
     *            (what the method assumes about the method's parameters and class's state)
     * @post    postconditions for the method
     *            (what the method guarantees upon completion)
     * @param    y    description of parameter y
     * @return    description of the return value
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x+y;
    }

}
