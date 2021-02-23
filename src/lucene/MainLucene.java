package lucene;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import lucene.model.Employees;
import lucene.model.Name;
import lucene.model.Question;
import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class MainLucene {
    private Directory directory;
    private IndexWriter indexWriter;
    private IndexReader indexReader;
    private Document document;
    private Field question,questionType,answers;
    private FilePathService filePathService;
    public static String nameRead;


    public MainLucene(String directoryPath) {
       try{
           directory = FSDirectory.open(new File(directoryPath));
           filePathService =new FilePathService();

           indexWriter = new IndexWriter(directory,new StandardAnalyzer(Version.LUCENE_36),
                   true,IndexWriter.MaxFieldLength.UNLIMITED);

           document = new Document();
           question = new Field("questions","",Field.Store.YES,Field.Index.ANALYZED);
           questionType = new Field("question_type","",Field.Store.YES,Field.Index.ANALYZED);
           answers = new Field("answers","",Field.Store.YES,Field.Index.ANALYZED);
           document.add(question);
           document.add(questionType);
           document.add(answers);
       }catch (IOException e){

       }
    }

    public void addData(String questions,String questionTypes,String answer) throws IOException{
                Question question =new Question();
                question.setQuestion(questions);
                question.setQuestion_type(questionTypes);
                question.setAnswer(answer);
                revalidate(question);
    }

    public void revalidate(Question question){
        try {
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(new FileReader(filePathService.getPath()+"/lucene.json"));
            JsonObject jsonObject = (JsonObject) obj;
            JsonArray msg = (JsonArray)jsonObject.get("questions");
            Iterator<JsonElement> iterator = msg.iterator();
            while(iterator.hasNext()) {
                nameRead = iterator.next().toString();
                //System.out.println(nameRead);
            }

            Gson gson = new Gson();
            FileWriter file = new FileWriter(filePathService.getPath()+"/lucene.json", false);
            JsonWriter jw = new JsonWriter(file);
            iterator = msg.iterator();
            Employees questions = new Employees();
            while(iterator.hasNext()) {
                questions.addEmployee(gson.fromJson(iterator.next().toString(), Question.class));
            }
            questions.addEmployee(question);
            gson.toJson(questions, Employees.class, jw);
            file.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public JsonObject readData(String query){
        System.out.println(query);
        try {
            JsonObject jsonObjectResponse=null;
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(new FileReader(filePathService.getPath()+"/lucene.json"));
            JsonObject jsonObject = (JsonObject) obj;
            JsonArray msg = jsonObject.getAsJsonArray("questions");//(JsonArray)jsonObject.get("questions");
            for (int i=0;i<msg.size();i++){
                JsonObject message = msg.get(i).getAsJsonObject();
                String question = message.get("question").getAsString();

                if (question.equalsIgnoreCase(query)){
                    jsonObjectResponse = message;
                }else {
                    jsonObjectResponse=null;
                }
            }
            return jsonObjectResponse;
        }catch (Exception exception){
            System.out.printf(exception.getMessage());
            return null;
        }
    }
}
