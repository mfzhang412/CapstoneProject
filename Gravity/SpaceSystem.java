import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

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
    private double velocity;
    private Ellipse2D.Double planet;
    private DrawingPanel panel;
    
    /**
     * Default constructor for objects of class System
     */
    public SpaceSystem(double m, double r, double x, double y, double v, DrawingPanel p)
    {
        this.mass = m;
        this.radius = r;
        this.center = new Point2D.Double(x, y);
        this.velocity = v;
        this.panel = p;
    }

    public void setMass(double mass)
    {
        this.mass = mass;
    }

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
    
    private double calculation()
    {
        ArrayList<SpaceSystem> list = panel.getSystems();
        
    }
}
