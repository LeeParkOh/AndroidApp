package lpo.boardandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Woonho on 2017. 3. 19..
 */

public class WritePopupActivity extends AppCompatActivity {

    protected static final String TAG = "WritePopupActivity";
    String titleText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_write);

        // 테스트 UI
        TextView tv = (TextView) findViewById(R.id.test_view);
        Button btn = (Button) findViewById(R.id.test_btn);
        EditText titleEdit = (EditText) findViewById(R.id.title_edit);
        titleText = titleEdit.getText().toString();

        Intent intent = getIntent();
        String test = intent.getStringExtra("data");
        tv.setText(test);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mOnClose(v);
            }
        });
    }

    public void mOnClose(View v) {

        Intent intent = new Intent();
        intent.putExtra("result", "Close");
        setResult(RESULT_OK, intent);
        Log.d(TAG, "Test = " + titleText);

        finish();
    }

}
