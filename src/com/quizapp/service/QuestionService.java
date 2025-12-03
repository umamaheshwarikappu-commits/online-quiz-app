package com.quizapp.service;

import com.quizapp.model.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class QuestionService {

    public List<Question> loadQuestions() {
        try (FileReader reader = new FileReader("src/com/quizapp/data/questions.json")) {

            Gson gson = new Gson();
            Type questionListType = new TypeToken<List<Question>>() {}.getType();
            List<Question> questions = gson.fromJson(reader, questionListType);

            return questions;

        } catch (IOException e) {
            System.out.println("Error reading questions: " + e.getMessage());
            return null;
        }
    }
}
