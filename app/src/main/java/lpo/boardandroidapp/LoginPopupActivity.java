package lpo.boardandroidapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import lpo.boardandroidapp.android.retrofit2.ContentService;
import lpo.boardandroidapp.request.LoginReq;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by leewoonho on 2017. 4. 3..
 */

public class LoginPopupActivity extends AppCompatActivity{

    protected static final String TAG = "LoginPopupActivity";
    private static final String URL = "http://feelfos.cafe24.com/";
    private static final String LOGIN_OK = "0";
    private static final String LOGIN_FAIL = "1";

    private EditText idEdit;
    private EditText pwEdit;
    private final String EXTR_ID_ARG = "EXTR_ID_ARG";
    private final String EXTR_PW_ARG = "EXTR_PW_ARG";
    private String mLoginCode;
    private boolean mIsLogin;

    private String mIdText;
    private String mPwText;

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
                mIdText = idEdit.getText().toString();
                mPwText = pwEdit.getText().toString();

//                checkToServer(mIdText, mPwText);

                new LoginCheckTask().execute();

//                if (mIsLogin) {
//                    Intent intent = new Intent();
//                    intent.putExtra(EXTR_ID_ARG, mIdText);
//                    intent.putExtra(EXTR_PW_ARG, mPwText);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                } else {
//                    Toast.makeText(getBaseContext(), "아이디 또는 비밀번호가 맞지 않습니다.", Toast.LENGTH_LONG).show();
//                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext() , NewUserPopupActivity.class);
                startActivityForResult(intent, 2);

            }
        });
    }

    private void checkToServer(String id, String pw) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        ContentService service = retrofit.create(ContentService.class);
//        Call<LoginReq> call = service.getToken(id, pw);
//
//        call.enqueue(new Callback<LoginReq>() {
//            @Override
//            public void onResponse(Call<LoginReq> call, Response<LoginReq> response) {
//                if (response.isSuccessful()) {
//                    LoginReq loginReq = response.body();
//                    mLoginCode = loginReq.resultCode;
//
//                    if (mLoginCode.equals(LOGIN_OK)) {
//                        Log.d(TAG, "Login Success = ");
//                        mIsLogin = true;
//                    } else if (mLoginCode.equals(LOGIN_FAIL)) {
//                        Log.d(TAG, "Login Fail = ");
//                        mIsLogin = false;
//                    }
//
//                } else {
//                    Log.d(TAG, "Retrofit Response Not Success");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginReq> call, Throwable t) {
//                Log.d(TAG, "Retrofit Response Failed");
//            }
//        });

        ContentService service = retrofit.create(ContentService.class);
        Call<LoginReq> call = service.getToken(id, pw);

        try {
            LoginReq loginReq = call.execute().body();
            mLoginCode = loginReq.resultCode;
            if (mLoginCode.equals(LOGIN_OK)) {
                mIsLogin = true;
            } else if (mLoginCode.equals(LOGIN_FAIL)) {
                mIsLogin = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class LoginCheckTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            checkToServer(mIdText, mPwText);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (mIsLogin) {
                Intent intent = new Intent();
                intent.putExtra(EXTR_ID_ARG, mIdText);
                intent.putExtra(EXTR_PW_ARG, mPwText);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(getBaseContext(), "아이디 또는 비밀번호가 맞지 않습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
