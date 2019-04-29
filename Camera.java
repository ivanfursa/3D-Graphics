package pkg3dgraphics;

public class Camera {
    private double x = 0, y = 0, z = -5;
    private double xRad = 0, yRad = 0, zRad = 0;  //Rotation angle around Z-axis
    
    public Camera(){}
    public Camera(double x, double y, double z){
        this.x = x; this.y = y; this.z = z;
    }
    
    public double getX(){return x;}
    public double getY(){return y;}
    public double getZ(){return z;}
    
    public void moveUp(){y -= 0.5;}
    public void moveDown(){y += 0.5;}
    public void moveLeft(){x -= 0.5;}
    public void moveRight(){x += 0.5;}
    public void moveIn(){z += 0.5;}
    public void moveOut(){z -= 0.5;}
    
    public void xClockwise(){
        xRad += Math.PI/32;
    }
    public void xAntiClockwise(){xRad -= Math.PI/32;}
    public void yClockwise(){yRad += Math.PI/32;}
    public void yAntiClockwise(){yRad -= Math.PI/32;}
    public void zClockwise(){zRad += Math.PI/32;}
    public void zAntiClockwise(){zRad -= Math.PI/32;}
    
    public double[] rotate2D(double[] pos, char axis){
        double rad = 0;
        switch (axis){
            case 'x':
                rad = xRad;
                break;
            case 'y':
                rad = yRad;
                break;
            case 'z':
                rad = zRad;
                break;
            default:
                System.out.println("Error");
        }
        
        // pos[0] is x, pos[1] is y;
        double c = Math.cos(rad);
        double s = Math.sin(rad);
        
        double a = pos[0]*c - pos[1]*s;
        double b = pos[0]*s + pos[1]*c;
        return new double[] {a,b};
    }
}
