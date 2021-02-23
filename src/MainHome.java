import com.google.gson.JsonObject;
import home.MainFrame;
import lucene.FilePathService;
import lucene.MainLucene;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainHome {
    private static MainFrame frame;
    private FilePathService filePathService;
    private MainLucene mainLucene;
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        MainHome mainHome = new MainHome();
    }

    public MainHome(){
        filePathService = new FilePathService();
        mainLucene = new MainLucene(filePathService.getPath());
        frame = new MainFrame();

        try {
           mainLucene.addData("who is meseret","definition","Mahder is a fucked up girl");
        }catch (Exception e){}
        frame.setVisible(true);
    }
}
