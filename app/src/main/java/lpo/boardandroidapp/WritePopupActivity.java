package lpo.boardandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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
    private EditText titleEdit;
    private EditText contentEdit;
    private final String titleArg = "input_title_text";
    private final String contentArg = "input_content_text";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_write);

        // 테스트 UI
        Button btn = (Button) findViewById(R.id.ok_btn);
        titleEdit = (EditText) findViewById(R.id.title_edit);
        contentEdit = (EditText) findViewById(R.id.content_edit);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String titleText = titleEdit.getText().toString();
                String contentText = contentEdit.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(titleArg, titleText);
                intent.putExtra(contentArg, contentText);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}
