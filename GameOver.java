import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DisplayScore extends JComponent {
  @Override
  protected void paintComponent(Graphics arg) {
    super.paintComponent(arg);
    arg.setColor(Color.BLACK);
    arg.setFont(arg.getFont().deriveFont(arg.getFont().getSize() * 1.6F));
    arg.drawString("Score: "+Game.score, 375, 200);
    arg.drawString("Press any key to exit",325,245);
  }
}

public class GameOver {
  GameOver() {
    Main.gameplaying = false;
    Main.frame.getContentPane().removeAll();
    Main.frame.getContentPane().add(new DisplayScore());
    Main.frame.setVisible(true);
  }
}
