package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Game extends Canvas implements Runnable,MouseListener{
    public static final int TAM = 640;
    public static int contador = 100;
    public Spawner spaw;
    public static int pontuacao = 0;
    public static int mx,my;
    public static boolean clicked = false, GameOver = false; 
    
    public static void main(String[] args) {
        Game game = new Game();
        JFrame jf = new JFrame("Game");
        jf.add(game);
        jf.setLocationRelativeTo(null);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setVisible(true);
        
        new Thread(game).start();
    }
    
    public Game(){
        Dimension dim = new Dimension(TAM,TAM);
        this.setPreferredSize(dim);
        this.addMouseListener(this);
        spaw = new Spawner();
    }
    
    public void update(){
//        if (GameOver) return;
        spaw.update();
//        contador--;
        if (contador <= 0)
            GameOver = true;
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, TAM, TAM);
        spaw.render(g);
        
        if(GameOver){
            for(int i = 0; i<spaw.rec.size(); i++){
                RectObj cur = spaw.rec.get(i);
                for(int x = 0; x<50; x++){
                    spaw.par.add(new Particula(cur.x,cur.y,8,8,cur.color));
                }
                spaw.rec.remove(cur);
            }
        }else{
            g.setColor(Color.green);
            g.fillRect(Game.TAM/2 - 170, 20, contador*3, 20);
            g.setColor(Color.white);
            g.drawRect(Game.TAM/2 - 170, 20, 300, 20);
        }
        
        bs.show();
    }

    @Override
    public void run() {
        while (true){
            update();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    
    
}
