/**
 * Class Name : NewUserPopupActivity.java
 * Description : 사용자 신규 가입 화면
 * Modification Information
 *
 *   수정일        수정자        수정내용
 *  ---------   ---------   -------------------------------
 *  2017.05.18.	 박종국		최초생성
 *
 * @author 박종국
 * @since 2017.05.18.
 * @version 1.0
 * @see Copyright (C) 2017 by LeeParkOh All right reserved.
 *
 */
package lpo.boardandroidapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUserPopupActivity extends AppCompatActivity{

    protected static final String TAG = "NewUserPopupActivity";
    private EditText idEdit;
    private EditText pwEdit;
    private EditText pwCntEdit;
    private EditText emailEdit;
    private EditText nicknameEdit;

    private final String EXTR_ID_ARG = "EXTR_ID_ARG";
    private final String EXTR_PW_ARG = "EXTR_PW_ARG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_new_user);

        //버튼값 매핑
        Button saveBtn = (Button) findViewById(R.id.btn_save);      //저장버튼
        Button closeBtn = (Button) findViewById(R.id.btn_close);    //종료버튼

        idEdit = (EditText) findViewById(R.id.et_id);               //id
        pwEdit = (EditText) findViewById(R.id.et_pw);               //pw
        pwCntEdit = (EditText) findViewById(R.id.et_pw_cnf);        //pw 확인
        emailEdit = (EditText) findViewById(R.id.et_email);         //email
        nicknameEdit = (EditText) findViewById(R.id.et_nickname);   //별명

        //저장버튼 클릭시
        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String idText = idEdit.getText().toString();
                String pwText = pwEdit.getText().toString();
                String pwCntText = pwCntEdit.getText().toString();
                String emailText = emailEdit.getText().toString();
                String nicknameText= nicknameEdit.getText().toString();


                //아이디 확인
                if(idText.length() > 3){
                    Toast.makeText(getBaseContext(), "ID는 4자리 이상 입력해야 합니다.", Toast.LENGTH_LONG).show();
                    return;
                }
               //아이디 중복확인 수정중


                //비밀번호 확인 절차
                if(pwText.length() > 3){
                    //경고창
                    Toast.makeText(getBaseContext(), "비밀번호는 4자리 이상 입력해야 합니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!pwText.equals(pwCntText)){
                    //경고창
                    Toast.makeText(getBaseContext(), "입력한 비밀번호가 일치 하지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!isValidEmail(emailText)){
                    //경고창
                    Toast.makeText(getBaseContext(), "이메일 형식을 확인 해주십시오.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(nicknameText.length() > 10){
                    //경고창
                    Toast.makeText(getBaseContext(), "닉네임은 10자리 이하로 입력해야 합니다.", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra(EXTR_ID_ARG, idText);
                intent.putExtra(EXTR_PW_ARG, pwText);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        //close 버튼 클릭시
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "닫기", Toast.LENGTH_LONG).show();
                        finish();
            }
        });
    }


    /**
     * Comment  : 정상적인 이메일 인지 검증.
     */
    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }

}
