import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * The SpaceSystem object that is to be drawn onto the simulation
 * 
 * @author Michael Zhang
 * @version 15 May 2016
 */
public class SpaceSystem
{
    /** The mass, radius, center, x-component velocity, and y-component velocity of the SpaceSytem object */
    private double mass;
    private double radius;
    private Point2D.Double center;
    private double xVelocity;
    private double yVelocity;
    
    /** The circle that is to be drawn to represent the SpaceSystem */
    private Ellipse2D.Double planet;
    
    /**
     * Default constructor for objects of class System
     */
    public SpaceSystem(double m, double r, double x, double y, double vX, double vY)
    {
        // sets the SpaceSystem object's mass, radius, center, x-component velocity, and y-component velocity
        this.mass = m;
        this.radius = r;
        this.center = new Point2D.Double(x, y);
        this.xVelocity = vX;
        this.yVelocity = vY;
    }
    
    /**
     * Returns the mass of the SpaceSystem object
     * 
     * @return     returns the mass of the SpaceSystem object
     */
    public double getMass()
    {
        return this.mass;
    }

    /**
     * Returns the radius of the SpaceSystem object
     * 
     * @return     returns the radius of the SpaceSystem object
     */
    public double getRadius()
    {
        return this.radius;
    }

    /**
     * Returns the center of the SpaceSystem object
     * 
     * @return     returns the center of the SpaceSystem object
     */
    public Point2D.Double getCenter()
    {
        return center;
    }
    
    /**
     * Returns the x-coordinate of the SpaceSystem object
     * 
     * @return     returns the x-coordinate of the SpaceSystem object
     */
    public double getXVal()
    {
        return this.center.getX();
    }
    
    /**
     * Return the y-coordinate of the SpaceSystem object
     * 
     * @return     returns the y-coordinate of the SpaceSystem object
     */
    public double getYVal()
    {
        return this.center.getY();
    }
    
    /**
     * Returns the x-component velocity of the SpaceSystem object
     * 
     * @return     returns the x-component velocity of the SpaceSystem object
     */
    public double getXVelocity()
    {
        return this.xVelocity;
    }
    
    /**
     * Returns the y-component velocity of the SpaceSystem object
     * 
     * @return     returns the y-component velocity of the SpaceSystem object
     */
    public double getYVelocity()
    {
        return this.yVelocity;
    }
    
    /**
     * Sets the x-component velocity of the SpaceSystem object
     * 
     * @param  v   the x-component velocity to be set
     */
    public void setXVelocity(double v)
    {
        this.xVelocity = v;
    }
    
    /**
     * Sets the y-component velocity of the SpaceSystem object
     * 
     * @param  v   the y-component velocity to be set
     */
    public void setYVelocity(double v)
    {
        this.yVelocity = v;
    }
    
    /**
     * Changes the center location of the circle to be drawn that represents the SpaceSystem object
     * 
     * @param  x   the x-coordinate of the new center
     * @param  y   the y-coordinate of the new center
     */
    public void move(double x, double y)
    {
        this.center = new Point2D.Double(x, y);
    }
    
    /**
     * Draws the circle that represents the SpaceSystem object
     * 
     * @param  g2   A Graphics2D object
     */
    public void draw(Graphics2D g2)
    {
        planet = new Ellipse2D.Double(this.center.getX() - this.radius, this.center.getY() - this.radius, this.radius * 2, this.radius * 2);
        g2.setColor(Color.WHITE);
        g2.fill(planet);
        g2.draw(planet);
    }
}








