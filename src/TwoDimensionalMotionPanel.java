package ProjectilMotion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
import javax.swing.*;
import java.awt.*;

/**
 * Created by Hezarfen Fly on 12/7/2014.
 */
public class TwoDimensionalMotionPanel extends JComponent {
    private int xball=25 ;

    private int yball=50;
    private double TimeOfFlight;
    private double currentTime = 0;
    private double velocityY;
    private double velocityX;
    private int xpo;
    private int ypo;
    private String XString = "";
    private String YString = "";
    private String Info = "";
    private int[] xPoints=new int[89999];
    private int[] yPoints=new int[89999];
    private String Xnum="";
    private String Ynum="";
    private   int t = 0;
    private int ind=0;
    private double MaxRange;
    private double MaxAtt;
    private double TimeMaxY;

    public TwoDimensionalMotionPanel () {

    }


    public boolean updatePosition() {
        currentTime+=0.01;
        this.xball = (int) (velocityX*currentTime)+20;
        this.yball = (int) (velocityY*currentTime-0.5*9.8*Math.pow(currentTime,2))+50;
        this.xpo=xball-20;
        this.ypo=yball-50;

        this.XString=Integer.toString(xpo);
        this.YString=Integer.toString(ypo);

        this.xPoints[ind]=this.xpo+30;
        this.yPoints[ind]=this.ypo+40;
        this.ind+=1;

        //this.xPoints = xPoints + xpo;
        //int[] n = (int[])xPoints.toArray(int[this.xPoints.size()]);
        //int[] y = (int[])yPoints.toArray(int[this.yPoints.size()]);
        //this.yPoints.append
        repaint();


        if (currentTime < TimeOfFlight)
            return false ;
        else
            return true ;
    }




    public void reset() {

        this.xball=25 ;
        this.yball=50;
        this.currentTime = 0;
        this.xPoints=new int[89999] ;
        this.yPoints=new int[89999];
        this.Xnum="";
        this.Ynum="";
        this.TimeOfFlight = 0 ;
        this.XString = "";
        this.YString = "";
        Info="";
        repaint();



    }

    
    @Override
        public void paint(Graphics g) {
        super.paint(g);

        for(int index = 2 ; index<xPoints.length-1;index++){
            int x = getWidth();
            int y = getHeight();
            if (y-yPoints[index-1]== getHeight() || xPoints[index]==xpo)   {
                continue;
            }
            else
            g.drawLine(xPoints[index-1],getHeight()-yPoints[index-1],xPoints[index],getHeight()-yPoints[index]);
        }



        ImageIcon ballIcon = new ImageIcon(this.getClass().getResource("/img/ball.gif"));
        ImageIcon backImage = new ImageIcon(this.getClass().getResource("/img/physics.png"));
        Image ball = ballIcon.getImage();
        Image bkgr = backImage.getImage();

        g.drawImage(bkgr,0,0,getWidth(),getHeight(),this);
        g.fillRect(30,getHeight()-40,xball-25,10);
        g.fillRect(20,getHeight()-yball+10,10,yball-50);
        g.drawImage(ball,xball,getHeight()-yball,20,20,this);
        //g.drawString(xPoints,50,30);
        //g.fillOval(xball, getHeight() - yball, 20, 20);
        g.drawLine(30, 20, 30, getHeight() - 40);
        g.drawLine(30, getHeight() - 40, getWidth() - 30, getHeight() - 40);
        g.drawString(XString, xball, getHeight() - 10);
        g.drawString(YString, 10, getHeight() - yball+10);
        g.drawString(Info,getWidth()-200,getHeight()-450);
        g.drawLine(getWidth()-35,getHeight()-45,getWidth()-30,getHeight()-40);
        g.drawLine(getWidth()-35,getHeight()-35,getWidth()-30,getHeight()-40);
        g.drawLine(35,25,30, 20);
        g.drawLine(25,25,30,20 );
        g.drawString("Y",25,15);
        g.drawString("X",getWidth()-25,getHeight()-35);
        g.setColor(Color.gray);

        for (t=50; t<getWidth()-65;t+=50){
            g.drawLine(t+25,getHeight()-35,t+25 ,getHeight()-45);
            this.Xnum =Integer.toString(t);
            g.drawString(Xnum,t+18,getHeight()-20);

        }

        for (t=50; t<getHeight()-65;t+=50){
            g.drawLine(28,getHeight()-40-t,32,getHeight()-40-t);
            this.Ynum =Integer.toString(t);
            g.drawString(Ynum,0,getHeight()-t-36);

        }

        repaint();

    }


    public void setVelocity (double[] vel) {

        this.TimeOfFlight = 2*vel[1]/9.8;
        this.velocityX=vel[0];
        this.velocityY=vel[1];
        this.MaxRange = velocityX * TimeOfFlight;
        this.TimeMaxY = velocityY / 9.8;
        this.MaxAtt = velocityY  * TimeMaxY - 0.5 * 9.8 * Math.pow(TimeMaxY,2);
        Info = "Time of Flight:  %n"+  TimeOfFlight +"\n"+"\n Max Attitude:  %n"+MaxAtt+"Max Range:  "+MaxRange;
        //this.Info=String.format(this.Info);
        //Info.format("%.5g%n", TimeOfFlight);

    }
}


