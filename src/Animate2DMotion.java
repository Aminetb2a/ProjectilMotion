package ProjectilMotion;//package p2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: fatih.senel
 * Date: 27.11.2014
 * Time: 14:21
 */
public class Animate2DMotion extends JFrame {


    Timer timer ;
    TwoDimensionalMotionPanel drawPanel = new TwoDimensionalMotionPanel();
    JPanel northInner = new JPanel();

    JPanel main = new JPanel();
    JScrollPane jScrollPane = new JScrollPane(main);

    JLabel lblAngle = new JLabel("Angle: ");
    JLabel lblVelocity = new JLabel("Velocity (m/s): ");
    JTextField txtAngle = new JTextField();
    JTextField txtVelocity = new JTextField();
    JButton btnStart = new JButton("Start");
    JButton btnReset = new JButton("Reset");
    boolean done = false;
    boolean started = false;
    public int x;

    public Animate2DMotion() throws HeadlessException {
        setSize(800, 600);
        //main.add(jScrollPane);
        //add(jScrollPane);
        timer = new Timer(10, new MyActionListener());
        northInner.setLayout(new GridBagLayout());
        northInner.add(lblAngle, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
        northInner.add(txtAngle, new GridBagConstraints(1, 0, 1, 1, 0.5, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));
        northInner.add(lblVelocity, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
        northInner.add(txtVelocity, new GridBagConstraints(3, 0, 1, 1, 0.4, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));
        northInner.add(btnStart, new GridBagConstraints(4, 0, 1, 1, 0.3, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        northInner.add(btnReset, new GridBagConstraints(5, 0, 1, 1, 0.3, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        btnStart.addActionListener(new MyActionListener());
        btnReset.addActionListener(new MyActionListener());

        main.setLayout(new BorderLayout());
        main.add(northInner, BorderLayout.NORTH);
        main.add(drawPanel, BorderLayout.CENTER);

        getContentPane().add(jScrollPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public static void main(String[] args) {

        new Animate2DMotion().setVisible(true);

    }

    private class MyActionListener implements ActionListener {







        @Override


        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnStart) {
                if (!timer.isRunning()) {

                    if (done || (txtAngle.getText().equalsIgnoreCase("") && txtVelocity.getText().equalsIgnoreCase("")))
                        return;

                    double angle;
                    double velocity;
                    try {
                        if (!started) {
                            started = true;
                            angle = Math.toRadians(Double.parseDouble(txtAngle.getText()));
                            velocity = Double.parseDouble(txtVelocity.getText());
                            double vx = velocity * Math.cos(angle);
                            double vy = velocity * Math.sin(angle);
                            drawPanel.setVelocity(new double[]{vx, vy});
                        }
                        btnStart.setText("Stop");

                        timer.start();
                        drawPanel.repaint();
                    } catch (NumberFormatException ex) {
                        return;
                    }

                } else {
                    btnStart.setText("Start");
                    timer.stop();
                    started = false;
                    drawPanel.repaint();
                }
            } else if (e.getSource() == btnReset) {
                btnStart.setText("Start");
                timer.stop();
                started = false;
                done = false;
                drawPanel.reset();
                drawPanel.repaint();
            } else if (e.getSource() == timer) {
                done = drawPanel.updatePosition();
                drawPanel.repaint();
                if (done) {
                    timer.stop();
                    drawPanel.repaint();
                }
            }
        }
    }
}
