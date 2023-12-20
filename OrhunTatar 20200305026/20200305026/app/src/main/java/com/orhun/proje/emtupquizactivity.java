package com.orhun.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class emtupquizactivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalquestiontextview;
    TextView questiontextview;
    Button ansa, ansb, ansc, ansd;
    Button submitbutton;

    Button playbutton;
    Button backbutton;
    Button stopbutton;

    int score = 0;
    int totalquestion = questionanswer2.question2.length;
    int currentquestionindex = 0;
    String selectedanswer = "";

    MediaPlayer mediaPlayer;
    String[] selectedAnswers = new String[totalquestion];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emtupquizactivity);

        totalquestiontextview = findViewById(R.id.textView222);
        questiontextview = findViewById(R.id.textView244);
        ansa = findViewById(R.id.ansA2);
        ansb = findViewById(R.id.ansB2);
        ansc = findViewById(R.id.ansC2);
        ansd = findViewById(R.id.ansD2);
        submitbutton = findViewById(R.id.submıtbutton2);
        playbutton = findViewById(R.id.playbutton2);
        stopbutton = findViewById(R.id.stopbutton2);
        backbutton = findViewById(R.id.backbutton3);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
                Intent stp = new Intent(emtupquizactivity.this, quizmenu.class);
                startActivity(stp);
            }
        });

        ansa.setOnClickListener(this);
        ansb.setOnClickListener(this);
        ansc.setOnClickListener(this);
        ansd.setOnClickListener(this);
        submitbutton.setOnClickListener(this);
        playbutton.setOnClickListener(this);
        stopbutton.setOnClickListener(this);

        totalquestiontextview.setText("Total Questions :  " + totalquestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansa.setBackgroundColor(Color.BLACK);
        ansb.setBackgroundColor(Color.BLACK);
        ansc.setBackgroundColor(Color.BLACK);
        ansd.setBackgroundColor(Color.BLACK);

        Button clickedbutton = (Button) view;
        if (clickedbutton.getId() == R.id.submıtbutton2) {
            if (selectedanswer.equals(questionanswer2.correctanswers2[currentquestionindex])) {
                score++;
            }
            selectedAnswers[currentquestionindex] = selectedanswer; // Store the selected answer
            currentquestionindex++;
            loadNewQuestion();
        } else if (clickedbutton.getId() == R.id.playbutton2) {
            startMusic();
        } else if (clickedbutton.getId() == R.id.stopbutton2) {
            stopMusic();
        } else {
            // choices button clicked
            selectedanswer = clickedbutton.getText().toString();
            clickedbutton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion() {
        if (currentquestionindex == totalquestion) {
            finishquiz();
            return;
        }

        questiontextview.setText(questionanswer2.question2[currentquestionindex]);
        questiontextview.setGravity(Gravity.CENTER);
        ansa.setText(questionanswer2.choices2[currentquestionindex][0]);
        ansb.setText(questionanswer2.choices2[currentquestionindex][1]);
        ansc.setText(questionanswer2.choices2[currentquestionindex][2]);
        ansd.setText(questionanswer2.choices2[currentquestionindex][3]);
    }

    void finishquiz() {
        StringBuilder resultMessage = new StringBuilder("Quiz Results:\n\n");

        String passStatus;
        if (score > totalquestion * 0.50) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }

        resultMessage.append("\nCorrect Answers:\n");
        for (int i = 0; i < totalquestion; i++) {
            resultMessage.append("Q").append(i + 1).append(": ").append(questionanswer2.correctanswers2[i]).append("\n");
        }

        resultMessage.append("\nYour Answers:\n");
        for (int i = 0; i < totalquestion; i++) {
            resultMessage.append("Q").append(i + 1).append(": ").append(selectedAnswers[i]).append("\n");
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage(resultMessage.toString() + "\n\nScore is " + score + " out of " + totalquestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartquiz())
                .setCancelable(false)
                .show();
    }

    void restartquiz() {
        score = 0;
        currentquestionindex = 0;
        loadNewQuestion();
    }

    private void startMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.loseyourself);
            mediaPlayer.start();
        }
    }

    private void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic();
    }
}

