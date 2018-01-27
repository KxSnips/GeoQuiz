package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private Question [] mQuestionBank  = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int current_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionBank[current_index].getTextResId();
        mQuestionTextView.setText(question);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);


            //Предыдущий вопрос
        mPrevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(current_index != 0){
                    current_index = (current_index - 1) % (mQuestionBank.length);
                    updateQuestion();
                }
                else{
                    current_index = mQuestionBank.length -1;
                    updateQuestion();
                }
            }
        });

            //Кнопка следующего вопроса.
        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                current_index = (current_index + 1)%(mQuestionBank.length);
                updateQuestion();
            }
        });

            //Ответ "Верно"
        mTrueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

            //Ответ "не верно"
        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });

            //Следующий вопрос при клике на текст.
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                current_index = (current_index + 1) % (mQuestionBank.length);
                updateQuestion();
            }
        });

        updateQuestion();
    }

            //Отображаем следующий вопрос.
        private void updateQuestion(){
            int question = mQuestionBank[current_index].getTextResId();
            mQuestionTextView.setText(question);
        }

            //Проверяем ответ Верно/ Не верно и показываем тост
        private void checkAnswer(boolean userPressedTrue){
            boolean AnswerIsTrue = mQuestionBank[current_index].isAnswerTrue();
            int messageresId = 0;
            if(userPressedTrue == AnswerIsTrue){
                messageresId = R.string.correct_toast;
            }
            else{
                messageresId = R.string.incorrect_toast;
            }

            Toast.makeText(QuizActivity.this, messageresId, Toast.LENGTH_SHORT).show();
        }
}
