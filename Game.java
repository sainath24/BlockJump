import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

class Line extends JComponent {
  static int y = 370;


  @Override
  protected void paintComponent(Graphics arg0) {
    super.paintComponent(arg0);
    arg0.setColor(Color.LIGHT_GRAY);
    arg0.drawLine(0,400 , 800, 400);
    //arg0.drawRect(90, y, 30, 30);
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
    //g.drawRect(x1, 330, 25, 70);
    g.fillRect(x1, 330, 25, 70);

    g.setColor(Color.red);
    //g.drawRect(x2, 330, 25, 70);
    g.fillRect(x2, 330, 25, 70);

    g.setColor(Color.MAGENTA);
    //g.drawRect(x3, 330, 25, 70);
    g.fillRect(x3, 330, 25, 70);

    g.setColor(Color.GREEN);
    //g.drawRect(x4, 330, 25, 70);
    g.fillRect(x4, 330, 25, 70);

  }

}

/*class Enemy extends Thread {
  static Random r = new Random();
  Enemy() {
    super("enemies");
    start();
  }

  void moveLeft() {
    RectEnemies.x1-=7;
    RectEnemies.x2-=7;
    RectEnemies.x3-=7;
    RectEnemies.x4-=7;
  }

  void checkCollision() {
    if(RectEnemies.x1<120 && RectEnemies.x1>75)
      if(Line.y>=330)
        Game.gameOver = true;
    if(RectEnemies.x2<120 && RectEnemies.x2>75)
      if(Line.y>330)
        Game.gameOver = true;
    if(RectEnemies.x3<120 && RectEnemies.x3>75)
      if(Line.y>330)
        Game.gameOver = true;
    if(RectEnemies.x4<120 && RectEnemies.x4>75)
      if(Line.y>330)
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
        sleep(30);
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

} */

class Move extends Thread {

  boolean click = false;
  static Random r = new Random();

  Move() {
    new Thread(this,"move box");
  }

  //Try on one thread
  void moveLeft() {
    RectEnemies.x1-=7;
    RectEnemies.x2-=7;
    RectEnemies.x3-=7;
    RectEnemies.x4-=7;
  }

  void checkCollision() {
    if(RectEnemies.x1<120 && RectEnemies.x1>75)
      if(Line.y>=330)
        Game.gameOver = true;
    if(RectEnemies.x2<120 && RectEnemies.x2>75)
      if(Line.y>330)
        Game.gameOver = true;
    if(RectEnemies.x3<120 && RectEnemies.x3>75)
      if(Line.y>330)
        Game.gameOver = true;
    if(RectEnemies.x4<120 && RectEnemies.x4>75)
      if(Line.y>330)
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
  //End of trial

  @Override
  public void run() {

    //One thread
    while(!Game.gameOver) {
    checkCollision();
    resetEnemy();
    moveLeft();
    Main.frame.getContentPane().add(new RectEnemies());
    Main.frame.setVisible(true);
    try {
      sleep(30);
    } catch(Exception e) {
      System.out.print(e);
    }
    //

    if(click) {
    if(!Game.gameOver) {
      while(Line.y<=370 && Line.y>230) {

        //One thread
        checkCollision();
        resetEnemy();
        moveLeft();
        Main.frame.getContentPane().add(new RectEnemies());
        //

        Line.y-=10;
        Main.frame.getContentPane().add(new Line());
        Main.frame.setVisible(true);
        try {
          sleep(30);
        } catch(Exception e) {
          System.out.print(e);
        }
      }
      while(Line.y<370) {

        //One thread
        checkCollision();
        resetEnemy();
        moveLeft();
        Main.frame.getContentPane().add(new RectEnemies());
        //
        Main.frame.getContentPane().add(new Line());
        Main.frame.setVisible(true);
        Line.y+=10;
        try {
          sleep(30);
        } catch(Exception e) {
          System.out.print(e);
        }
      }
      Line.y = 370;
      Game.threadRunning = false;
    }
    click =false;
  }

  }
  new GameOver();
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
    //new Enemy();

    Main.frame.getContentPane().add(new Line());
    Main.frame.setVisible(true);

    //One thread
    Main.frame.getContentPane().add(new RectEnemies());
    Move m = new Move();
    m.start();
    //
    Main.frame.addKeyListener(new KeyListener(){

      @Override
      public void keyTyped(KeyEvent arg0) {

      }

      @Override
      public void keyReleased(KeyEvent arg0) {

      }

      @Override
      public void keyPressed(KeyEvent arg0) {
          //Move m = new Move();
          if(Main.gameplaying) {
            if(!gameOver) {
              if(!threadRunning) {
                m.click = true;
                //threadRunning = true;
                //m.start();
              }
            }
        }
      }
    });
  }
}
