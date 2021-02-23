package lucene;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathService {
    private Path currentRelativePath;
    private File file,jsonFile;
    private FileWriter fileWriter;
    private JsonObject jsonObject;
    public FilePathService(){
        currentRelativePath = Paths.get("luceneData");
        String path = currentRelativePath.toAbsolutePath().toString();
        file = new File(path);
        jsonFile = new File(path+"/lucene.json");

        if (!file.exists()){
            file.mkdirs();
            try {
                jsonFile.createNewFile();

                if (jsonFile.exists()){
                    jsonObject = new JsonObject();
                    jsonObject.addProperty("lucene","This is lucene");
                    jsonObject.add("questions",new JsonArray());
                    fileWriter = new FileWriter(jsonFile.getPath());
                    fileWriter.write(jsonObject.toString());
                    fileWriter.close();
                }
            }catch (Exception exception){}
        }
    }

    public String getPath(){
        return currentRelativePath.toAbsolutePath().toString();
    }
}
