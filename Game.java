import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

class line extends JComponent {
  static int y = 370;


  @Override
  protected void paintComponent(Graphics arg0) {
    super.paintComponent(arg0);
    arg0.setColor(Color.LIGHT_GRAY);
    arg0.drawLine(0,400 , 800, 400);
    arg0.drawRect(90, y, 30, 30);
    arg0.fillRect(90,y,30,30);
  }
}

class RectEnemies extends JComponent {

  static Random r = new Random();

  static int x1 = 800;
  static int x2 = x1 + r.nextInt(250) + 200;
  static int x3 = x2 + r.nextInt(250) + 200;
  static int x4 = x3 + r.nextInt(250) + 200;

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(g.getFont().deriveFont(g.getFont().getSize() * 1.6F));
    g.drawString("Score:" + Game.score, 600, 50);
    g.setColor(Color.ORANGE);
    g.drawRect(x1, 330, 25, 70);
    g.fillRect(x1, 330, 25, 70);

    g.setColor(Color.red);
    g.drawRect(x2, 330, 25, 70);
    g.fillRect(x2, 330, 25, 70);

    g.setColor(Color.MAGENTA);
    g.drawRect(x3, 330, 25, 70);
    g.fillRect(x3, 330, 25, 70);

    g.setColor(Color.GREEN);
    g.drawRect(x4, 330, 25, 70);
    g.fillRect(x4, 330, 25, 70);

  }

}

class enemy extends Thread {
  static Random r = new Random();
  enemy() {
    super("enemies");
    start();
  }

  void moveLeft() {
    RectEnemies.x1-=5;
    RectEnemies.x2-=5;
    RectEnemies.x3-=5;
    RectEnemies.x4-=5;
  }

  void checkCollision() {
    if(RectEnemies.x1<125 && RectEnemies.x1>75)
      if(line.y>=330)
        Game.gameOver = true;
    if(RectEnemies.x2<125 && RectEnemies.x2>75)
      if(line.y>330)
        Game.gameOver = true;
    if(RectEnemies.x3<125 && RectEnemies.x3>75)
      if(line.y>330)
        Game.gameOver = true;
    if(RectEnemies.x4<125 && RectEnemies.x4>75)
      if(line.y>330)
        Game.gameOver = true;
  }



  void resetEnemy() {
    if(RectEnemies.x1<0) {
      RectEnemies.x1 = 800 + (360+RectEnemies.x4);
      Game.score++;
    }
    if(RectEnemies.x2<0) {
      RectEnemies.x2 = 800 + (400+RectEnemies.x1) + r.nextInt(250) + 100;
      Game.score++;
    }
    if(RectEnemies.x3<0) {
      RectEnemies.x3 = 800 + (600+RectEnemies.x4) + r.nextInt(250) + 100;
      Game.score++;
    }
    if(RectEnemies.x4<0) {
      RectEnemies.x4 = 800 + (200+RectEnemies.x3) + r.nextInt(250) + 100;
      Game.score++;
    }
  }

  @Override
  public void run() {
    while(!Game.gameOver) {
      try {
        sleep(18);
      } catch(Exception e) {
        System.out.print(e);
      }
      checkCollision();
      resetEnemy();
      moveLeft();
      Main.frame.getContentPane().add(new RectEnemies());
      Main.frame.setVisible(true);
    }
    new GameOver();

  }

}

class move extends Thread {
  move() {
    new Thread(this,"move box");
  }

  @Override
  public void run() {
    if(!Game.gameOver) {
      while(line.y<=370 && line.y>230) {
        line.y-=7;
        Main.frame.getContentPane().add(new line());
        Main.frame.setVisible(true);
        try {
          sleep(25);
        } catch(Exception e) {
          System.out.print(e);
        }
      }
      while(line.y<370) {
        Main.frame.getContentPane().add(new line());
        Main.frame.setVisible(true);
        line.y+=7;
        try {
          sleep(25);
        } catch(Exception e) {
          System.out.print(e);
        }
      }
      line.y = 370;
      Game.threadRunning = false;
    }
  }
}

public class Game {

  static boolean threadRunning;
  static boolean gameOver;
  static int score;


  Game() {
    Main.gameplaying = true;
    gameOver = false;
    threadRunning =  false;
    score = 0;
    Main.frame.getContentPane().removeAll();
    Main.frame.repaint();
    new enemy();
    Main.frame.getContentPane().add(new line());
    Main.frame.setVisible(true);
    Main.frame.addKeyListener(new KeyListener(){

      @Override
      public void keyTyped(KeyEvent arg0) {

      }

      @Override
      public void keyReleased(KeyEvent arg0) {

      }

      @Override
      public void keyPressed(KeyEvent arg0) {
          move m = new move();
          if(Main.gameplaying) {
            if(!gameOver) {
              if(!threadRunning) {
                threadRunning = true;
                m.start();
              }
            }
        }
      }
    });
  }


}
