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

public class generalquizactivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalquestiontextview;
    TextView questiontextview;
    Button ansa, ansb, ansc, ansd;
    Button submitbutton;

    Button playbutton;
    Button backbutton;
    Button stopbutton;


    int score = 0;
    int totalquestion = questionanswer.question.length;
    int currentquestionindex = 0;
    String selectedanswer = "";

    MediaPlayer mediaPlayer;
    String[] selectedAnswers = new String[totalquestion];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalquizactivity);

        totalquestiontextview = findViewById(R.id.textView22);
        questiontextview = findViewById(R.id.textView24);
        ansa = findViewById(R.id.ansA);
        ansb = findViewById(R.id.ansB);
        ansc = findViewById(R.id.ansC);
        ansd = findViewById(R.id.ansD);
        submitbutton = findViewById(R.id.submıtbutton);
        playbutton = findViewById(R.id.playbutton);
        stopbutton = findViewById(R.id.stopbutton);
        backbutton = findViewById(R.id.backbutton2);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stp = new Intent(generalquizactivity.this, quizmenu.class);
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
        if (clickedbutton.getId() == R.id.submıtbutton) {
            if (selectedanswer.equals(questionanswer.correctanswers[currentquestionindex])) {
                score++;
            }
            selectedAnswers[currentquestionindex] = selectedanswer; // Store the selected answer
            currentquestionindex++;
            loadNewQuestion();
        } else if (clickedbutton.getId() == R.id.playbutton) {
            startMusic();

        } else if (clickedbutton.getId() == R.id.stopbutton) {
            stopMusic();
        } else if (clickedbutton.getId() == R.id.backbutton2) {
            stopMusic();
            Intent stp = new Intent(generalquizactivity.this, quizmenu.class);
            startActivity(stp);
        } else {
            selectedanswer = clickedbutton.getText().toString();
            clickedbutton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion() {
        if (currentquestionindex == totalquestion) {
            finishquiz();
            return;
        }

        questiontextview.setText(questionanswer.question[currentquestionindex]);
        questiontextview.setGravity(Gravity.CENTER);
        ansa.setText(questionanswer.choices[currentquestionindex][0]);
        ansb.setText(questionanswer.choices[currentquestionindex][1]);
        ansc.setText(questionanswer.choices[currentquestionindex][2]);
        ansd.setText(questionanswer.choices[currentquestionindex][3]);
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
            resultMessage.append("Q").append(i + 1).append(": ").append(questionanswer.correctanswers[i]).append("\n");
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
            mediaPlayer = MediaPlayer.create(this, R.raw.turkishmarch);
        }
        mediaPlayer.start();
    }

    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic();
    }
}
