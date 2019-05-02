package pkg3dgraphics;

public class Camera {
    // Variables that consern the camera itself
    private double x = 0, y = 0, z = -10;         // Position
    private double xRad = 0, yRad = 0, zRad = 0;  // Orientation
    // Variables that consern the changes made to vertices.
    // 0 - no changes, 1 - increase (clockwise), -1 - decrease (anti-clockwise)
    private int rotX = 0, rotY = 0, rotZ = 0;
    private int tranX = 0, tranY = 0, tranZ = 0;
    // Movement speed settings.
    private double transRate = 0.5;
    private double rotRate = Math.PI/32;
    private double cos = Math.cos(rotRate), sin = Math.sin(rotRate);
    
    public Camera(){}
    public Camera(double x, double y, double z){
        this.x = x; this.y = y; this.z = z;
    }
    
    public double getX(){return x;}
    public double getY(){return y;}
    public double getZ(){return z;}
    
    // Updating camera's positon
    public void moveUp(){y -= transRate; tranY = -1;}
    public void moveDown(){y += transRate; tranY = 1;}
    public void moveLeft(){x -= transRate; tranX = -1;}
    public void moveRight(){x += transRate; tranX = 1;}
    public void moveIn(){z += transRate; tranZ = 1;}
    public void moveOut(){z -= transRate; tranZ = -1;}
    // Updating camera's orientation
    public void xClockwise(){xRad += rotRate; rotX = 1;}
    public void xAntiClockwise(){xRad -= rotRate; rotX = -1;}
    public void yClockwise(){yRad += rotRate; rotY = 1;}
    public void yAntiClockwise(){yRad -= rotRate; rotY = -1;}
    public void zClockwise(){zRad += rotRate; rotZ = 1;}
    public void zAntiClockwise(){zRad -= rotRate; rotZ = -1;}
    
    // Nullifis all the changes that are ought to be made.
    public void clearChanges(){
        tranX = 0; tranY = 0; tranZ = 0;
        rotX = 0; rotY = 0; rotZ = 0;
    };
    
    // Given the x,y,z coordinates, applies translation and rotation.
    public double[] transform(double[] pos){
        double newX = pos[0];
        double newY = pos[1];
        double newZ = pos[2];
        
        // Translation
            // Along x
        if (tranX != 0){ newX += tranX * transRate; }
            // Along y
        if (tranY != 0){ newY += tranY * transRate; }
            // Along z
        if (tranZ != 0){ newZ += tranZ * transRate; }
        
        // Rotation
            // Around x
        if (rotX != 0){
            newY = pos[1]*cos        - pos[2]*sin * rotX;
            newZ = pos[1]*sin * rotX + pos[2]*cos;
        }
            // Around y
        if (rotY != 0){
            newX = pos[0]*cos        - pos[2]*sin * rotY;
            newZ = pos[0]*sin * rotY + pos[2]*cos;
        }
            // Around z
        if (rotZ != 0){
            newX = pos[0]*cos        - pos[1]*sin * rotZ;
            newY = pos[0]*sin * rotZ + pos[1]*cos;
        }        
        
        return new double[] {newX, newY, newZ};
    }
}
