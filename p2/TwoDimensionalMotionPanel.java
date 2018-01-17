package p2; /**
 * Created by Amine on 06-Dec-14.
 */

import p2.Animate2DMotion;
import javax.swing.*;
import java.awt.*;


public class TwoDimensionalMotionPanel extends JPanel{

    private ImageIcon ball = new ImageIcon("images/latest.png");    // import image of the ball
    private Image bal = ball.getImage();

    private ImageIcon can = new ImageIcon("images/Military-Cannon-icon.png");       // import image of the canon
    private Image cann = can.getImage();

    private ImageIcon War = new ImageIcon("images/war.png");       // import image of the canon
    private Image war = War.getImage();

    private double Vx=0;    // X velocity of the ball at t=0
    private double Vy=0;    // Y velocity of the ball at t=0
    private double t=0;     // Timer t that refreche every 10 msc
    private double Tf=0;    // Time of Flight
    private double Th=0;    // Time of maximum height
    private double Td=0;    // Time of maximum distance of the projectile
    private double At=0;    // Altitude
    private double Rg=0;    // Range
    private int x=35, y=40,i=0;
    private int []X = new int [89999];  // Array that take every 10msc x coordinate
    private int []Y = new int [89999];  // Array that take every 10msc y coordinate


    public TwoDimensionalMotionPanel(){}

    public void setVelocity(double[] vel) {
        this.Vx = vel[0];       // it takes the x initial velocity
        this.Vy = vel[1];       // it takes the y initial velocity
        this.Tf = (2 * Vy ) / (9.8);    // compute the Time of Flight
    }
    public void reset()         // Reset the variables to the initial values
    {
        this.x=35;
        this.y=40;
        this.t=0;
        this.Tf=0;
        this.X = new int [99999];
        this.Y = new int [99999];
        repaint();
    }

    public boolean updatePosition()
    {
        this.t+=0.01;           // Add 10msc = 0.01 at every update
        this.Th = Vy / 9.8;     // Compute the time of the maximum height that the ball can reach
        this.Td = (2 * Vy) / 9.8;   // Compute the time of the maximum distance that the ball can reach
        this.At = Vy * Th - 0.5*(9.8 * (Math.pow(Th,2)));   // Compute the maximum Altitude
        this.Rg = Vx * Td;          // Compute the Range (Maximum distance that the ball can reach)
        this.x = (int)(Vx * t) + 35; // Assign the X coordinate
        this.y = (int)((Vy * t)-0.5*(9.8*(t*t))) + 40;      //Assign the Y coordinate

        repaint();

        if(t < Tf)          // if the timer steal didn't reach the Time of Flight the Update still working
            return false;
        else                // till the timer reach the Time of Flight it Stop
            return true;
    }

    @Override
    public void paint (Graphics g)
    {

        super.paint (g);
        int w = getWidth();     //Assign maximum width
        int h = getHeight();        //Assign maximum Height

        g.drawLine(35,h-40,w-20,h-40);      // Draw the X Axis
        g.drawLine(35,20,35,h-40);          // Draw the Y Axis

        int xpt=35;             // Assign 35px as initial value to the first tick marks of X Axis
        int ypt=h-40;           //Assign Height-40px as initial value to the first tick marks of Y Axis
        int xcoo=0;             //Assign 0 to the Origin
        int ycoo=0;             //Assign 0 to the Origin
        while(xpt<w){
            if(xpt >= w-30){break;}

            g.drawLine(xpt,h-46,xpt,h-34);      // Draw the tick marks of the X Axis
            g.drawString(""+xcoo,xpt-10,h-20);  //Draw the values of the X Axis
            xcoo+=50;                           // Add 50 every 50px
            xpt+=50;                            // Add marks every 50px
        }

        while(ypt>20){

            g.drawLine(29,ypt,41,ypt);          // Draw the tick marks of the X Axis
            g.drawString(""+ycoo,5,ypt+3);      //Draw the values of the X Axis
            ypt-=50;                            // Add marks every 50px
            ycoo+=50;                           // Add 50 every 50px
        }

        g.drawImage(bal, x-5, h - y-5, 10, 10, this);       // Draw the ball
        g.drawImage(cann,7,h-43,25,25,this);                // Draw the canon
        g.drawImage(war,0,0,w,h,this);



        FontMetrics Fm = g.getFontMetrics();                // Using the FontMetrics to get the width of it

        String TF= new String("Time of Flight: "+ (Math.round(Tf*100)/100.0) +"secs");
        String AT = new String("Maximum Altitude: "+ (Math.round(At*100)/100.0) +"m");
        String RG = new String("Range: "+ (Math.round(Rg*100)/100.0) +"m");

        int SW = Fm.stringWidth(AT);        // Get the String Width

        g.drawString(TF,w-(SW+20),100);     // Draw the three Strings
        g.drawString(AT,w-(SW+20),120);
        g.drawString(RG,w-(SW+20),140);

        this.X[i]= this.x;                      // Assign X coordinate to the X Array
        this.Y[i]= this.y;                    // Assign Y coordinate to the X Array
        this.i+=1;                              // Add 1 index after every update

        for(int k=0 ; k < X.length-1; k++)          // Draw the curve every 10msc
        {
            g.drawOval(X[k], h-Y[k], 1, 1);
        }

        g.setColor(Color.BLACK);
        repaint();
    }


    public void  paintComponent (Graphics g)
    {
        super.paintComponent(g);
        int h = getHeight();

        g.setColor(Color.BLUE);

        g.drawLine(x , h - 46, x , h - 34);        // Draw a line that follow the ball at the X Axis
        g.drawString("" + (x - 35), x-10 , h - 10);

        g.drawLine(29, h - y ,41 , h - y );          // Draw a line that follow the ball at the Y Axis
        g.drawString(""+(y-40), 5, h-y+3);

    }
}