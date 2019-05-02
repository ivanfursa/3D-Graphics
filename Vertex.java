package pkg3dgraphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Vertex {
    private double x, y;                // Real on-screen coordinates
    private double refX, refY, refZ;    // Imaginable 3D coordinates
    private final double cx, cy;        // Center of the screen coords
    private final double radius = 4;
    
    public Vertex(double x, double y, double z, double cx, double cy, Camera camera){
        refX = x;
        refY = y;
        refZ = z - 10;   // Create a cube further off
        this.cx = cx;
        this.cy = cy;
        update(camera);
    }
    
    public double getX(){return x;}
    public double getY(){return y;}
    public double getRefX(){return refX;}
    public double getRefY(){return refY;}
    public double getRefZ(){return refZ;}   
    public double getRadius(){return radius;}
          
    public void update(Camera camera){
        double[] pos = new double[] {refX, refY, refZ};
        pos = camera.transform(pos);
        refX = pos[0];
        refY = pos[1];
        refZ = pos[2];
        
        assert refZ != 0;
        double f = 600/refZ;
        x = refX*f + cx;
        y = refY*f + cy;
    }
    
    public void draw(Graphics2D g2){
        g2.setPaint(Color.BLACK);
        g2.fill(new Ellipse2D.Double(x, y, radius*2, radius*2));
    }
}
