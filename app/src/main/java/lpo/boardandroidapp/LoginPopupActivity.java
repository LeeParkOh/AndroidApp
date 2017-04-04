package lpo.boardandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by leewoonho on 2017. 4. 3..
 */

public class LoginPopupActivity extends AppCompatActivity{

    protected static final String TAG = "LoginPopupActivity";
    private EditText idEdit;
    private EditText pwEdit;
    private final String EXTR_ID_ARG = "EXTR_ID_ARG";
    private final String EXTR_PW_ARG = "EXTR_PW_ARG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_login);

        // 테스트 UI
        Button loginBtn = (Button) findViewById(R.id.login_btn);
        Button signupBtn = (Button) findViewById(R.id.signup_btn);

        idEdit = (EditText) findViewById(R.id.id_edit_view);
        pwEdit = (EditText) findViewById(R.id.pw_edit_view);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String idText = idEdit.getText().toString();
                String pwText = pwEdit.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(EXTR_ID_ARG, idText);
                intent.putExtra(EXTR_PW_ARG, pwText);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}
