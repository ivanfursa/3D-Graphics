package pkg3dgraphics;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

class Edge {
    private Vertex v1, v2;
    
    public Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
    }
    
    public void draw(Graphics2D g2){
        double x1 = v1.getX() + v1.getRadius();
        double y1 = v1.getY() + v1.getRadius();
        double x2 = v2.getX() + v2.getRadius();
        double y2 = v2.getY() + v2.getRadius(); 
        g2.draw(new Line2D.Double(x1,y1, x2,y2));
    }
}
