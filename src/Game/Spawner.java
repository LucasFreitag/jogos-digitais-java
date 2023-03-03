package Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author LucasFreitag
 */
public class Spawner {
    public int timer = 0;
    
    public ArrayList<RectObj> rec = new ArrayList<RectObj>();
    public ArrayList<Particula> par = new ArrayList<Particula>();
    
    public void update(){
       timer++; 
       int tamRec = new Random().nextInt(20)+30;
//        if (timer % 20 == 0 && !Game.GameOver){
        if (timer % 20 == 0){
          rec.add(new RectObj(0,new Random().nextInt(480-40)+40,tamRec,tamRec));
        }
        for (int i = 0; i<rec.size();i++){
            RectObj cur = rec.get(i);
            rec.get(i).update();
            
            if (cur.x > Game.TAM){                
                rec.remove(cur);
                Game.contador-=4;
            }
            
            if (Game.clicked){
                if(Game.mx >= cur.x && Game.mx < cur.x+cur.width) {
                    if(Game.my >= cur.y && Game.my < cur.y+cur.height) {
                        rec.remove(cur);
                        Game.pontuacao++;
                        if (Game.contador < 100)
                            Game.contador += 1;
                        Game.clicked = false;
                        
                        for(int x = 0; x<50; x++){
                            par.add(new Particula(cur.x,cur.y,8,8,cur.color));
                        }
                    }
                }
            }
        }
        
        for (int i =0; i< par.size(); i++){
            Particula p = par.get(i);
            p.update();
            
            if(p.contador >= 100) par.remove(p);
        }
       
    }
    public void render(Graphics g){
        if (!Game.GameOver){
            for (int i = 0; i<rec.size();i++){
                RectObj cur = rec.get(i);

                Graphics2D g2 = (Graphics2D) g;
                g2.rotate(Math.toRadians(cur.rotation),cur.x+cur.width/2,cur.y+cur.height/2);
                g2.setColor(cur.color);
                g2.fillRect(cur.x, cur.y, cur.width, cur.height);
                g2.rotate(Math.toRadians(-cur.rotation),cur.x+cur.width/2,cur.y+cur.height/2);
            }
        }
        for (Particula p: par){
            p.render(g);
        }
        
    }
}
