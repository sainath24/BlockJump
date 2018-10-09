import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Intro extends JComponent {

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.LIGHT_GRAY);
    g.drawLine(0,400 , 800, 400);
    g.drawRect(90, 370, 30, 30);
    g.fillRect(90,370,30,30);
    g.setColor(Color.BLACK);
    g.setFont(g.getFont().deriveFont(g.getFont().getSize() * 2.0F));
    g.drawString("Press Any Key to Start", 275, 285);
  }
}


public class Main {

  static JFrame frame;
  static boolean gameplaying = false;
  Main() {
    frame = new JFrame();
    frame.setSize(800,600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new Intro());

    frame.addKeyListener(new KeyListener(){

      @Override
      public void keyTyped(KeyEvent arg0) {
      }

      @Override
      public void keyReleased(KeyEvent arg0) {

      }

      @Override
      public void keyPressed(KeyEvent arg0) {
        if(!gameplaying && Game.gameOver)
          System.exit(1);
        if(!gameplaying) {
          frame.getContentPane().removeAll();
            new Game();
        }
      }
    });

    frame.setVisible(true);

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable(){

      @Override
      public void run() {
        new Main();
      }
    });
  }



}
