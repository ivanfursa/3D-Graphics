package pkg3dgraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private double cx, cy;  // Center of the screen coordinates.
    private Camera camera = new Camera(0,0,-10);
    
    public Canvas(int width, int height){
        setBackground(Color.WHITE);
        
        cx = width/2;
        cy = height/2;
        
        Vertex v1 = new Vertex( 1, 1, 1, cx, cy, camera);
        Vertex v2 = new Vertex( 1,-1, 1, cx, cy, camera);
        Vertex v3 = new Vertex(-1, 1, 1, cx, cy, camera);
        Vertex v4 = new Vertex(-1,-1, 1, cx, cy, camera);
        Vertex v5 = new Vertex( 1, 1,-1, cx, cy, camera);
        Vertex v6 = new Vertex( 1,-1,-1, cx, cy, camera);
        Vertex v7 = new Vertex(-1, 1,-1, cx, cy, camera);
        Vertex v8 = new Vertex(-1,-1,-1, cx, cy, camera);
        
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);
        vertices.add(v8);
        
        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v1, v3);
        Edge e3 = new Edge(v2, v4);
        Edge e4 = new Edge(v3, v4);
        Edge e5 = new Edge(v5, v6);
        Edge e6 = new Edge(v5, v7);
        Edge e7 = new Edge(v6, v8);
        Edge e8 = new Edge(v7, v8);
        Edge e9 = new Edge(v1, v5);
        Edge e10 = new Edge(v2, v6);
        Edge e11 = new Edge(v3, v7);
        Edge e12 = new Edge(v4, v8);
        
        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);
        edges.add(e6);
        edges.add(e7);
        edges.add(e8);
        edges.add(e9);
        edges.add(e10);
        edges.add(e11);
        edges.add(e12);
        
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                // Translation.
                if (e.getKeyChar() == 'w'){ camera.moveUp(); }
                if (e.getKeyChar() == 's'){ camera.moveDown(); }
                if (e.getKeyChar() == 'a'){ camera.moveLeft(); }
                if (e.getKeyChar() == 'd'){ camera.moveRight(); }
                if (e.getKeyChar() == 'q'){ camera.moveIn(); }
                if (e.getKeyChar() == 'e'){ camera.moveOut(); }
                
                // Update.
                for (Vertex vertex: vertices){ vertex.update(camera); }
                repaint();
                camera.clearChanges();
            }
        });
        
        NewMouseListener mouseListener = new NewMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        
    } // Constructor ends.
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for (Edge e: edges) { e.draw(g2); }
    }
    
    class NewMouseListener implements MouseListener, MouseMotionListener {
        double dragX = 0;
        double dragY = 0;
        double clickX = 0;
        double clickY = 0;
        
        // This listener is not invoked.
        @Override
        public void mousePressed(MouseEvent e){
            clickX = e.getX(); clickY = e.getY();
            dragX = clickX; dragY = clickY;
        }
                
        @Override
        public void mouseDragged(MouseEvent e) {            
            dragX = e.getX() - clickX;
            dragY = e.getY() - clickY;
            
            // Rotation.
            if (dragX > 0) { camera.setRotationSpeed(dragX); camera.yRotation(); }
            else if (dragX < 0) { camera.setRotationSpeed(dragX); camera.yRotation();}
            if (dragY > 0) { camera.setRotationSpeed(dragY); camera.xRotation(); }
            else if (dragY < 0) { camera.setRotationSpeed(dragY); camera.xRotation(); }

            // Update.
            for (Vertex vertex : vertices) { vertex.update(camera); }
            repaint();
            camera.clearChanges();
            
            clickX = e.getX();
            clickY = e.getY();
        }
        
        @Override
        public void mouseMoved(MouseEvent e){}
        @Override
        public void mouseClicked(MouseEvent e){}
        @Override
        public void mouseReleased(MouseEvent e){}
        @Override
        public void mouseExited(MouseEvent e){}
        @Override
        public void mouseEntered(MouseEvent e){}
        
    } // NewMouseListener class ends
    
} // Canvas class ends

