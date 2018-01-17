package p2;

/**
 * Created by Amine on 10-Dec-14.
 */

import javax.swing.*;
import java.awt.*;

public class test extends JFrame{
    public test(){
        //drawPanel1.setLayout(new BorderLayout());
        add(new drawPanel1());

    }
    public static void main(String []args){
        test fr = new test();
        fr.setSize(800,600);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);

    }
}
class drawPanel1 extends JPanel{


    private ImageIcon ball = new ImageIcon("images/Ball_Black.png");
    private ImageIcon can = new ImageIcon("images/Military-Cannon-icon.png");
    private Image bal = ball.getImage();
    private Image cann = can.getImage();
    private int []x= new int[5000];
    private int []y= new int[5000];

    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        String H = new String(String.valueOf(h));
        for (int i=0;i<100;i+=10){
            x[i]=i;
        }
        for (int i=h;i<1000;i+=50){
            y[i]=i;
        }
        g.drawString(H,100,50);
        g.drawLine(42,h-40,w-20,h-40);
        g.drawLine(42,20,42,h-40);
        g.drawImage(bal,42,h-55,15,15,this);
        g.drawImage(cann,5,h-50,35,35,this);
        g.drawPolyline(x,y,x.length);

    }
    public void paint (Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        int w = getWidth();
        int h = getHeight();
        int xpt=40;
        int ypt=h-40;
        int xnum=0;
        int ynum=0;

        while(ypt>20){

            g.drawLine(34,ypt,46,ypt);
            g.drawString(""+ynum,10,ypt+3);
            ypt-=50;
            ynum+=50;
        }
    }
}