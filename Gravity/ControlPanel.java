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
    private JOptionPane mass;
    private JOptionPane volume;
    
    private DrawingPanel panel;
    private System s;
    
    private double mass;
    private double volume;
    
    /**
     * Default constructor for objects of class ControlPanel
     */
    public ControlPanel(DrawingPanel panel)
    {
        this.panel = panel;
        
        this.set = new JButton("Set Parameters");
        
        this.mass = new JOptionPane("Mass of object");
        //mass = joption pane to get mass
    
        this.volume = new JOptionPane("Volume of object");
        //volume = joption pane to get volume
        
        set.addActionListener(new SetListener());
    }

    public class SetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            s.setMass(mass);
            s.setVolume(volume);
        }
    }

}
