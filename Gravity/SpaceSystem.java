import javax.swing.JComponent;
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
    private double xVelocity;
    private double yVelocity;
    private Ellipse2D.Double planet;
    
    /**
     * Default constructor for objects of class System
     */
    public SpaceSystem(double m, double r, double x, double y, double vX, double vY)
    {
        this.mass = m;
        this.radius = r;
        this.center = new Point2D.Double(x, y);
        this.xVelocity = vX;
        this.yVelocity = vY;
    }

    public double getMass()
    {
        return this.mass;
    }

    public double getRadius()
    {
        return this.radius;
    }

    public Point2D.Double getCenter()
    {
        return center;
    }
    
    public double getXVal()
    {
        return this.center.getX();
    }
    
    public double getYVal()
    {
        return this.center.getY();
    }
    
    public double getXVelocity()
    {
        return this.xVelocity;
    }
    
    public double getYVelocity()
    {
        return this.yVelocity;
    }
    
    public void setXVelocity(double v)
    {
        this.xVelocity = v;
    }
    
    public void setYVelocity(double v)
    {
        this.yVelocity = v;
    }
    
    public void move(double x, double y)
    {
        this.center = new Point2D.Double(x, y);
    }
    
    public void draw(Graphics2D g2)
    {
        planet = new Ellipse2D.Double(this.center.getX() - this.radius, this.center.getY() - this.radius, this.radius * 2, this.radius * 2);
        g2.setColor(Color.WHITE);
        g2.fill(planet);
        g2.draw(planet);
    }
    
    //     private void calculateNewCenter()
    //     {
    //         ArrayList<SpaceSystem> list = panel.getSystems();
    //         double xComp = 0.0;
    //         double yComp = 0.0;
    //         
    //         for (SpaceSystem sys: list)
    //         {
    //             if (sys != this)
    //             {
    //                 xComp += ((sys.getX() - this.getX()) * (G * sys.getMass() * this.mass)) / ((Math.pow(sys.getX() - this.getX(), 2)) + (Math.pow(sys.getY() - this.getY(), 2)));
    //                 yComp += ((sys.getY() - this.getY()) * (G * sys.getMass() * this.mass)) / ((Math.pow(sys.getX() - this.getX(), 2)) + (Math.pow(sys.getY() - this.getY(), 2)));
    //             }
    //         }
    //         
    //         double retX = this.getX() + xVelocity * 1 + .5 * (xComp / this.mass) * Math.pow(1, 2);
    //         double retY = this.getY() + yVelocity * 1 + .5 * (yComp / this.mass) * Math.pow(1, 2);
    //         
    //         double new_velocity_X = (xComp / this.mass) * 1 + xVelocity;
    //         double new_velocity_Y = (yComp / this.mass) * 1 + yVelocity;
    //         
    //         this.xVelocity = new_velocity_X;
    //         this.yVelocity = new_velocity_Y;
    //         
    //         this.move(retX, retY);
    //     }
}








