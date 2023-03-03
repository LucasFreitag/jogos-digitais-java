package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author LucasFreitag
 */
public class Particula extends Rectangle{
    public Color color;
    public int speed = 0, rotation = 0;
    public double dx,dy,xa,ya;
    public int contador = 0;
    
    public Particula(int x,int y,int width,int height, Color color) {
        super(x,y,width,height);
        this.color = color;
        speed = 8;
        xa = x;
        ya = y;
        dx = new Random().nextGaussian();
        dy = new Random().nextGaussian();
    }
    
    public void update(){
        xa+= dx*speed;
        ya+= dy*speed;
        contador++;
    }
    
    public void render(Graphics g){
        g.setColor(this.color);
        g.fillRect((int)xa, (int)ya, width, height);
    }
}
