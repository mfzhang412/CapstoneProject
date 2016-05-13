import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.awt.Dimension;

/**
 * Write a description of class ControlPanel here.
 * 
 * @author Michael Zhang
 * @version 14 April 2016
 */
public class ControlPanel extends JPanel
{
    /** description of instance variable x (add comment for each instance variable) */
    //private JButton massOption;
    //private JButton radiusOption;
    //private JButton xCoor;
    //private JButton yCoor;
    private JButton set;
    private JLabel displayVel;
    
    private DrawingPanel panel;
    private SpaceSystem s;
    
    //private double mass;
    //private double radius;
    //private double x;
    //private double y;
    
    /**
     * Default constructor for objects of class ControlPanel
     */
    public ControlPanel(DrawingPanel panel)
    {
        this.panel = panel;
        
        //this.massOption = new JButton("Set Mass");
        //this.radiusOption = new JButton("Set Radius");
        
        //this.xCoor = new JButton("Set x-coordinate of the system");
        //this.yCoor = new JButton("Set y-coordinate of the system");
        
        this.displayVel = new JLabel("Velocity:                   ");
        this.set = new JButton("Set parameters");
        //this.add(massOption);
        //this.add(radiusOption);
        //this.add(xCoor);
        //this.add(yCoor);
        
        //this.setLayout(new BorderLayout());
        //this.add(displayVel, BorderLayout.NORTH);
        //this.add(set, BorderLayout.CENTER);
        
        this.add(displayVel);
        this.add(set);
        
        // test
        //check to see if they are overlapping or not, comment one out and see
        
        
        
        //MassListener m = new MassListener();
        //RadiusListener r = new RadiusListener();
        //XListener xLi = new XListener();
        //YListener yLi = new YListener();
        SetListener ok = new SetListener();
        //massOption.addActionListener(new MassListener());
        //radiusOption.addActionListener(new RadiusListener());
        //xCoor.addActionListener(new XListener());
        //yCoor.addActionListener(new YListener());
        set.addActionListener(new SetListener());
    }
    
    public void setVelocityLabel(double v)
    {
        this.displayVel.setText("Velocity: " + v + " m/s");
        // test
        //repaint();
    }
    
    public Dimension getPreferredSize()
    {
        return (new Dimension(200, 700));
    }

    //     public class MassListener implements ActionListener
    //     {
    //         public void actionPerformed(ActionEvent e)
    //         {
    //             System.out.print("Enter in a valid mass: ");
    //             Scanner scan = new Scanner(System.in);
    //             mass = scan.nextDouble();
    //             System.out.println("");
    //         }
    //     }
    // 
    //     public class RadiusListener implements ActionListener
    //     {
    //         public void actionPerformed(ActionEvent e)
    //         {
    //             System.out.print("Enter in a valid volume: ");
    //             Scanner scan = new Scanner(System.in);
    //             radius = scan.nextDouble();
    //             System.out.println("");
    //         }
    //     }
    //     
    //     public class XListener implements ActionListener
    //     {
    //         public void actionPerformed(ActionEvent e)
    //         {
    //             System.out.print("Enter in a valid x-coordinate: ");
    //             Scanner scan = new Scanner(System.in);
    //             x = scan.nextDouble();
    //             System.out.println("");
    //         }
    //     }
    //     
    //     public class YListener implements ActionListener
    //     {
    //         public void actionPerformed(ActionEvent e)
    //         {
    //             System.out.print("Enter in a valid y-coordinate: ");
    //             Scanner scan = new Scanner(System.in);
    //             y = scan.nextDouble();
    //             System.out.println("");
    //         }
    //     }
    
    public class SetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Double mass = Double.parseDouble(JOptionPane.showInputDialog("Set the system's mass."));
            Double radius = Double.parseDouble(JOptionPane.showInputDialog("Set the system's radius."));
            
            panel.addSystem(mass, radius);
            //panel.addSystem(mass, radius, 0, 0, 0, 0);
            
            //System.out.print("Set parameters divided by whitespace (mass radius x-coordinate y-coordinate X-component-velocity Y-component-velocity): ");
            //Scanner scan = new Scanner(System.in);
            //String[] param = (scan.next()).split(" ");
            //panel.addSystem(Double.parseDouble(param[0]), Double.parseDouble(param[1]), Double.parseDouble(param[2]), Double.parseDouble(param[3]), Double.parseDouble(param[4]), Double.parseDouble(param[5]));
        }
    }
}
