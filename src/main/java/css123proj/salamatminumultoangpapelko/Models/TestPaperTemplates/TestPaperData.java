package css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestPaperData {

    public static List<QuestionData> quest;

    static {
        try (Reader reader = new InputStreamReader(
                TestPaperData.class.getResourceAsStream("/questionDatabase.json"), "UTF-8")) {

            java.lang.reflect.Type listType = new TypeToken<List<QuestionData>>() {}.getType();
            quest = new Gson().fromJson(reader, listType);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class QuestionData {
        public String questionType;
        public String question;
        @SerializedName("A.") public String a;
        @SerializedName("B.") public String b;
        @SerializedName("C.") public String c;
        @SerializedName("D.") public String d;
        public String answer;

        public String getQuestion() {
            return question;
        }
        
        
        
        
        
        
        
    }

    public static List<QuestionData> generateQuestions(int level, int set) {

        int tfCount = 0;
        int mcCount = 0;

        switch (level) {
            case 1:
                tfCount = 10;
                mcCount = 0;
                break;
            case 2:
                tfCount = 7;
                mcCount = 3;
                break;
            case 3:
                tfCount = 0;
                mcCount = 10;
                break;
        }

        List<QuestionData> tfList = new ArrayList<>();
        List<QuestionData> mcList = new ArrayList<>();

        for (QuestionData q : quest) {
            if (q.questionType.equals("True or False")) {
                tfList.add(q);
            } else {
                mcList.add(q);
            }
        }

        Collections.shuffle(tfList);
        Collections.shuffle(mcList);

        List<QuestionData> result = new ArrayList<>();
        result.addAll(tfList.subList(0, Math.min(tfCount, tfList.size())));
        result.addAll(mcList.subList(0, Math.min(mcCount, mcList.size())));

        return result;
    }
}