import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JColorChooser;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 * Write a description of class DrawingPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DrawingPanel extends JPanel
{
    /** description of instance variable x (add comment for each instance variable) */
    private ArrayList<SpaceSystem> list;

    /**
     * Default constructor for objects of class DrawingPanel
     */
    public DrawingPanel()
    {
        this.setBackground(Color.BLACK);
        list = new ArrayList<SpaceSystem>();
        list.add(new SpaceSystem(1.989 * Math.pow(10, 30), 50, 500, 300, 0, 0, this));
    }
    
    public void addSystem(double m, double r, double x, double y, double vX, double vY)
    {
        list.add(new SpaceSystem(m, r, x, y, vX, vY, this));
    }
    
    public ArrayList<SpaceSystem> getSystems()
    {
        return list;
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
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        sys.draw((Graphics2D) g);
    }

}
