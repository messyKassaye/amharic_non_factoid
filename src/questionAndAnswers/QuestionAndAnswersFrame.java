package questionAndAnswers;

import addNewQuestionAndAnswer.AddQuestionAndAnswerMainPanel;
import lucene.FilePathService;
import lucene.MainLucene;

import javax.swing.*;
import java.awt.*;

public class QuestionAndAnswersFrame extends JFrame {
    private QuestionAndAnswerPanel mainPanel;
    private FilePathService filePathService;
    private MainLucene mainLucene;
    public QuestionAndAnswersFrame(){
        mainPanel = new QuestionAndAnswerPanel();
        filePathService = new FilePathService();
        mainLucene = new MainLucene(filePathService.getPath());
        try {
            mainLucene.addData("who is Ezedin","definition","Mahder is a fucked up girl");
        }catch (Exception exception){}

        setSize(new Dimension(750,600));
        setResizable(false);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);

    }
}
