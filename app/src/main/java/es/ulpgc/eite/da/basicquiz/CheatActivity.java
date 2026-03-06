package es.ulpgc.eite.da.basicquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {

  public static final String TAG = "Quiz.CheatActivity";


  public final static String EXTRA_ANSWER = "EXTRA_ANSWER";
  public final static String EXTRA_CHEATED = "EXTRA_CHEATED";
  public final static String KEY_ANSWER = "KEY_ANSWER";
  public final static String KEY_CHEATED = "KEY_CHEATED";

  private Button noButton, yesButton;
  private TextView answerField;

  private int currentAnswer;
  private boolean answerCheated;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cheat);
    setTitle(R.string.cheat_screen_title);
    Log.d(TAG, "onCreate");

    linkLayoutComponents();

    if(savedInstanceState == null) {
      initLayoutData();

    } else {

      currentAnswer = savedInstanceState.getInt(KEY_ANSWER);
      answerCheated = savedInstanceState.getBoolean(KEY_CHEATED);

      if(answerCheated) {
        onYesButtonClicked();  // desconecta buttons: yes y no
      }
    }

    initLayoutButtons();
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG, "onResume");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG, "onPause");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.d(TAG, "onSaveInstanceState");

    outState.putInt(KEY_ANSWER, currentAnswer);
    outState.putBoolean(KEY_CHEATED, answerCheated);
  }

  private void returnCheatedStatus() {
    Log.d(TAG, "returnCheatedStatus");

    //Log.d(TAG, "answerCheated: " + answerCheated);

    Intent intent = new Intent();
    intent.putExtra(EXTRA_CHEATED, answerCheated);
    setResult(RESULT_OK, intent);

    finish();

  }

  @SuppressWarnings("ALL")
  @Override
  public void onBackPressed() {
    //super.onBackPressed();
    Log.d(TAG, "onBackPressed");

    returnCheatedStatus();
  }


  private void onYesButtonClicked() {
    Log.d(TAG, "onYesButtonClicked");

    yesButton.setEnabled(false);
    noButton.setEnabled(false);
    answerCheated = true;
    updateLayoutContent();
  }

  private void updateLayoutContent() {
    Log.d(TAG, "updateLayoutContent");

    if(currentAnswer == 0) {
      answerField.setText(R.string.false_text);
    } else {
      answerField.setText(R.string.true_text);

    }
  }

  private void onNoButtonClicked() {
    Log.d(TAG, "onNoButtonClicked");

    yesButton.setEnabled(false);
    noButton.setEnabled(false);

    returnCheatedStatus();
  }


  private void initLayoutData() {
    Log.d(TAG, "initLayoutData");

    Intent intent = getIntent();

    if ( intent != null) {
      currentAnswer = intent.getExtras().getInt(EXTRA_ANSWER);
    }
  }

  private void initLayoutButtons() {

    noButton.setOnClickListener(v -> onNoButtonClicked());
    yesButton.setOnClickListener(v -> onYesButtonClicked());
  }

  private void linkLayoutComponents() {
    noButton = findViewById(R.id.noButton);
    yesButton = findViewById(R.id.yesButton);

    answerField = findViewById(R.id.answerField);
  }

}
