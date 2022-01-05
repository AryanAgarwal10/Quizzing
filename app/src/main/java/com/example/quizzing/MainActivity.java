package com.example.quizzing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int index, progress;
    private ImageButton trueBtn, falseBtn;//true and false buttons
    TextView questionTxt, score;//question and progress text views
    ProgressBar progressBar;
    final QuizzModel quizModel[] = {
            new QuizzModel(true, R.string.q0),
            new QuizzModel(false, R.string.q1),
            new QuizzModel(true, R.string.q2),
            new QuizzModel(false, R.string.q3),
            new QuizzModel(true, R.string.q4),
            new QuizzModel(false, R.string.q5),
            new QuizzModel(true, R.string.q6),
            new QuizzModel(false, R.string.q7),
            new QuizzModel(true, R.string.q8),
            new QuizzModel(false, R.string.q9),
    };
    int length=quizModel.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueBtn = findViewById(R.id.trueButton);
        falseBtn = findViewById(R.id.falseButton);
        questionTxt = findViewById(R.id.question);
        score = findViewById(R.id.score);
        progressBar = findViewById(R.id.progressBar);
        if(savedInstanceState!=null) {
        progress=savedInstanceState.getInt("progress");
        index= savedInstanceState.getInt("index");
        }
        else{
            progress=0;
            index=0;
        }
        progressBar.setMax(length);
        progressBar.incrementProgressBy(progress);
        questionTxt.setText(quizModel[index].getQuestion());
        score.setText(progress+"/"+length);
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluate(true);
            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluate(false);
            }
        });
    }

    private void evaluate(boolean answer) {
        Boolean ans = quizModel[index].isAnswer();
        if (ans == answer) {

            score.setText(++progress+"/"+length);
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
        index++;
        if (index < length) {
        questionTxt.setText(quizModel[index].getQuestion());
        progressBar.incrementProgressBy(1);
        }
        else
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setCancelable(false);
            alert.setTitle(R.string.alertTitle);
            alert.setMessage("YOUR SCORE="+progress+"/"+length);
            alert.setPositiveButton("Finish Quiz", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    finish();

                }
                });
            alert.show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("progress",progress);
        outState.putInt("index",index);
    }
}

