import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

/**
 * The control panel that shows a system's velocity, allows the user to set the parameters of the systems,
 *      start and paue the simulation, and clear the simulation.
 * 
 * @author Michael Zhang
 * @version 15 May 2016
 */
public class ControlPanel extends JPanel
{
    /** The label that displays the velocity */
    private JLabel displayVel;
    
    /** The buttons that allow the user to set parameters of the system and start, pause, and clear the simulation */
    private JButton set;
    private JButton start;
    private JButton stop;
    private JButton clear;
    
    /** The drawing panel and system that will be added to the panel's ArrayList<SpaceSystem> */
    private DrawingPanel panel;
    private SpaceSystem s;
    
    /**
     * Default constructor for objects of class ControlPanel
     */
    public ControlPanel(DrawingPanel panel)
    {
        // sets the DrawingPanel parameter passed in to ControlPanel's panel instance variable
        this.panel = panel;
        
        // creates the label and buttons to be added to the control panel
        this.displayVel = new JLabel("Velocity:                   ");
        this.set = new JButton("Set parameters");
        this.start = new JButton("Start simulation");
        this.stop = new JButton("Pause simulation");
        this.clear = new JButton("Clear systems");
        
        // adds the label and buttons to be displayed
        this.add(displayVel);
        this.add(set);
        this.add(start);
        this.add(stop);
        this.add(clear);
        
        // sets listeners so that, when pressed, the button will do an action
        set.addActionListener(new SetListener());
        start.addActionListener(new StartListener());
        stop.addActionListener(new StopListener());
        clear.addActionListener(new ClearListener());
    }
    
    /**
     * Displays the velocity
     * 
     * @param  v   the velocity to be displayed
     */
    public void setVelocityLabel(double v)
    {
        this.displayVel.setText("Velocity: " + v + " m/s");
    }
    
    /**
     * Overrides JPanel's getPreferredSize() method
     * 
     * @return     the dimensions of the control panel
     */
    public Dimension getPreferredSize()
    {
        return (new Dimension(200, 700));
    }

    
    public class SetListener implements ActionListener
    {
        /**
         * Passes the mass and radius that the user sets to the DrawingPanel
         * 
         * @param  e   an ActionEvent object 
         */
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                Double mass = Double.parseDouble(JOptionPane.showInputDialog("Set the system's mass."));
                Double radius = Double.parseDouble(JOptionPane.showInputDialog("Set the system's radius."));
                
                panel.addSystem(mass, radius);
            }
            catch (NullPointerException ex) {}
        }
    }
    
    public class StartListener implements ActionListener
    {
        /**
         * Starts the DrawingPanel's simulation status
         * 
         * @param  e   an ActionEvent object
         */
        public void actionPerformed(ActionEvent e)
        {
            panel.setStarter(true);
        }
    }
    
    public class StopListener implements ActionListener
    {
        /**
         * Pauses the DrawingPanel's simulation status
         * 
         * @param  e   an ActionEvent object
         */
        public void actionPerformed(ActionEvent e)
        {
            panel.setStarter(false);
        }
    }
    
    public class ClearListener implements ActionListener
    {
        /**
         * Clears the SpaceSystem objects from DrawingPanel and pauses the DrawingPanel's simulation status
         * 
         * @param  e   an ActionEvent object
         */
        public void actionPerformed(ActionEvent e)
        {
            panel.setStarter(false);
            panel.clearSystems();
        }
    }
}
