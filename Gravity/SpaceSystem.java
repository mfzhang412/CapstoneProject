import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Write a description of class System here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpaceSystem
{
    /** description of instance variable x (add comment for each instance variable) */
    private double mass;
    private double radius;
    private Point2D.Double center;
    private Ellipse2D.Double planet;
    
    /**
     * Default constructor for objects of class System
     */
    public SpaceSystem()
    {

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
    public void setMass(double mass)
    {
        this.mass = mass;
    }

    /**
     * An example of a method - replace this comment with your own
     *  that describes the operation of the method
     *
     * @pre     preconditions for the method
     *          (what the method assumes about the method's parameters and class's state)
     * @post    postconditions for the method
     *          (what the method guarantees upon completion)
     * @param   y   description of parameter y
     * @return  description of the return value
     */
    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public void move(double x, double y)
    {
        center.setLocation(x, y);
    }
    
    public void draw(Graphics2D g2)
    {
        planet = new Ellipse2D.Double(this.center.getX() - this.radius, this.center.getY() - this.radius, this.radius * 2, this.radius * 2);
        g2.setColor(Color.WHITE);
        g2.fill(planet);
        g2.draw(planet);
    }
    
    public double getMass()
    {
        return mass;
    }
    
    public double getRadius()
    {
        return radius;
    }
    
    public Point2D.Double getCenter()
    {
        return center;
    }
}
