package pkg3dgraphics;

import javax.swing.JFrame;

public class Frame extends JFrame{
    private Canvas canvas;
    private final int WIDTH = 600, HEIGHT = 600;
    
    public Frame(String name){
        super(name);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        canvas = new Canvas(WIDTH, HEIGHT);
        add(canvas);
        canvas.setFocusable(true);  // Allowing keyListener
    }
}
