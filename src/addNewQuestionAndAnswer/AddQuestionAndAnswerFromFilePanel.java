package addNewQuestionAndAnswer;

import com.sun.tools.javac.Main;
import home.MainFrame;
import lucene.FilePathService;
import lucene.MainLucene;
import standards.StandardButton;
import standards.StandardLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;


public class AddQuestionAndAnswerFromFilePanel extends JPanel {
    private StandardLabel information, question,questionType,answers;
    private StandardButton questionButton,questionTypeButton,answersButton,backToHome;
    private StandardButton proccessFile;
    private GridLayout centerLayout;
    private JPanel centerPanel,bottomPanel;
    private BorderLayout borderLayout;
    private JFileChooser fileChooser;
    private File questionFile,questionTypeFile,answersFile;
    private MainLucene mainLucene;
    private FilePathService filePathService;
    public AddQuestionAndAnswerFromFilePanel(){
        setBorder(new EmptyBorder(50,50,50,50));
        filePathService = new FilePathService();
        mainLucene = new MainLucene(filePathService.getPath());

        fileChooser = new JFileChooser();

        borderLayout =new BorderLayout();
        setLayout(borderLayout);
        information =new StandardLabel();
        information.setText("<html>Note. The files must be equal in numbers of questions,<br/>question answers and question type</html>");
        add(information,BorderLayout.NORTH);

        centerLayout = new GridLayout(5,5,20,50);
        centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(50,0,0,0));
        centerPanel.setLayout(centerLayout);
        add(centerPanel,BorderLayout.CENTER);

        proccessFile = new StandardButton();
        question = new StandardLabel();
        question.setText("Select questions file:");
        question.setFont(new Font("arial",Font.BOLD,14));
        centerPanel.add(question);

        questionButton = new StandardButton();
        questionButton.setText("select file");
        questionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame =(JFrame) SwingUtilities.getWindowAncestor(AddQuestionAndAnswerFromFilePanel.this);
                int result = fileChooser.showOpenDialog(jFrame);
                if (result==JFileChooser.APPROVE_OPTION){
                   questionFile = fileChooser.getSelectedFile();
                   questionButton.setContentAreaFilled(false);
                   questionButton.setFocusable(false);
                   questionButton.setForeground(Color.RED);
                   questionButton.setText("question:"+questionFile.getName());

                }
            }
        });
        centerPanel.add(questionButton);

        questionType = new StandardLabel();
        proccessFile.setVisible(false);

        questionType.setText("Select question type:");
        centerPanel.add(questionType);
        questionTypeButton = new StandardButton();
        questionTypeButton.setText("Select file");
        questionTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame =(JFrame) SwingUtilities.getWindowAncestor(AddQuestionAndAnswerFromFilePanel.this);
                int result = fileChooser.showOpenDialog(jFrame);
                if (result==JFileChooser.APPROVE_OPTION){
                    questionTypeFile = fileChooser.getSelectedFile();
                    questionTypeButton.setContentAreaFilled(false);
                    questionTypeButton.setFocusable(false);
                    questionTypeButton.setForeground(Color.RED);
                    questionTypeButton.setText("question type:"+questionTypeFile.getName());
                }
            }
        });
        centerPanel.add(questionTypeButton);

        answers = new StandardLabel();
        answers.setText("Select answers file:");
        centerPanel.add(answers);

        answersButton = new StandardButton();
        answersButton.setText("Select file");
        answersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame =(JFrame) SwingUtilities.getWindowAncestor(AddQuestionAndAnswerFromFilePanel.this);
                int result = fileChooser.showOpenDialog(jFrame);
                if (result==JFileChooser.APPROVE_OPTION){
                    answersFile = fileChooser.getSelectedFile();
                    answersButton.setContentAreaFilled(false);
                    answersButton.setFocusable(false);
                    answersButton.setForeground(Color.RED);
                    answersButton.setText("Answers:selected");
                    proccessFile.setVisible(true);
                }
            }
        });
        centerPanel.add(answersButton);

        proccessFile.setText("Start processing file");
        centerPanel.add(proccessFile);
        proccessFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader questionReader = new BufferedReader(new FileReader(questionFile));
                    String questionLine =null;
                    BufferedReader questionTypeReader = new BufferedReader(new FileReader(questionTypeFile));
                    String questionTypeLine =null;
                    BufferedReader answersReader = new BufferedReader(new FileReader(answersFile));
                    String answersLine =null;
                    while ((questionLine=questionReader.readLine())!=null&&(questionTypeLine=questionTypeReader.readLine())!=null&&(answersLine=answersReader.readLine())!=null){
                        mainLucene.addData(questionLine,questionTypeLine,answersLine);
                    }
                }catch (Exception exception){

                }
            }
        });


        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,20));
        add(bottomPanel,BorderLayout.SOUTH);

        backToHome = new StandardButton();
        backToHome.setText("Back to home");
        backToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(AddQuestionAndAnswerFromFilePanel.this).dispose();
                MainFrame mainFrame = new MainFrame();
                mainFrame.showBack();
            }
        });
        bottomPanel.add(backToHome);

    }
}
