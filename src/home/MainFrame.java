package home;

import home.HomePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private HomePanel mainPanel;
    public MainFrame(){
        mainPanel = new HomePanel();
        setContentPane(mainPanel);
        setSize(new Dimension(500,500));
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void showBack(){
        setVisible(true);
    }
}
