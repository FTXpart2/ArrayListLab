import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;

public class Runner {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlaylistEditor().setVisible(true);
            }
        });
    }
}