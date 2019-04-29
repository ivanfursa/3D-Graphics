package pkg3dgraphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Vertex {
    private double x, y;                // Real, on the screen coordinates
    private double refX, refY, refZ;    // Imaginable 3D coordinates
    private double cx, cy;              // Center of the screen coords
    private double radius = 4;
    private double xRad = 0, yRad = 0, zRad = 0;    // A rotation angle
    private double cos = 1, sin = 0;                // Recalculateable cas and sin
    private double rotX, rotY, rotZ;                // Rotation variables
    private double trnX, trnY, trnZ;                // Translation variables
    
    public Vertex(double x, double y, double z, double cx, double cy, Camera camera){
        refX = x;
        rotX = x;
        trnX = 0;
        refY = y;
        rotY = y;
        trnY = 0;
        refZ = z;   // Create a cube further off
        rotZ = z;
        trnZ = -10;
        this.cx = cx;
        this.cy = cy;
        update();
    }
    
    public double getX(){return x;}
    public double getY(){return y;}
    public double getRefX(){return refX;}
    public double getRefY(){return refY;}
    public double getRefZ(){return refZ;}   
    public double getRadius(){return radius;}
    
    public void moveUp(){trnY -= 0.5;}
    public void moveDown(){trnY += 0.5;}
    public void moveLeft(){trnX -= 0.5;}
    public void moveRight(){trnX += 0.5;}
    public void moveIn(){trnZ += 0.5;}
    public void moveOut(){trnZ -= 0.5;}
    
    public void xClockwise(){
        xRad += Math.PI/32;
        cos = Math.cos(xRad);
        sin = Math.sin(xRad);
        rotY = refY*cos - refZ*sin;
        rotZ = refY*sin + refZ*cos;
    }
    public void xAntiClockwise(){
        xRad -= Math.PI/32;
        cos = Math.cos(xRad);
        sin = Math.sin(xRad);
        rotY = refY*cos - refZ*sin;
        rotZ = refY*sin + refZ*cos;
    }
    public void yClockwise(){
        yRad += Math.PI/32;
        cos = Math.cos(yRad);
        sin = Math.sin(yRad);
        rotX = refX*cos - refZ*sin;
        rotZ = refX*sin + refZ*cos;
    }
    public void yAntiClockwise(){
        yRad -= Math.PI/32;
        cos = Math.cos(yRad);
        sin = Math.sin(yRad);
        rotX = refX*cos - refZ*sin;
        rotZ = refX*sin + refZ*cos;
    }
    public void zClockwise(){
        zRad += Math.PI/32;
        cos = Math.cos(zRad);
        sin = Math.sin(zRad);
        rotX = refX*cos - refY*sin;
        rotY = refX*sin + refY*cos;
    }
    public void zAntiClockwise(){
        zRad -= Math.PI/32;
        cos = Math.cos(zRad);
        sin = Math.sin(zRad);
        rotX = refX*cos - refY*sin;
        rotY = refX*sin + refY*cos;
    }
    
    public void update(){
        rotZ -= trnZ;
        rotX -= trnX;
        rotY -= trnY;
                
        assert rotZ != 0;
        double f = 600/rotZ;
               
        x = rotX*f + cx;
        y = rotY*f + cy;
    }
    
    public void draw(Graphics2D g2){
        g2.setPaint(Color.BLACK);
        g2.fill(new Ellipse2D.Double(x, y, radius*2, radius*2));
    }
}
