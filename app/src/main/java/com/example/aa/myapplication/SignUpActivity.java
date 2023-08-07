package com.example.aa.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {
    private EditText signup_password;
    private EditText signup_username;
    private EditText confirm_password;
    protected UserSQLite helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        findViews();
        if (helper == null) {
            helper = new UserSQLite(this);
        }
    }

    private void findViews() {
        signup_username = (EditText) findViewById(R.id.signup_username);
        signup_password = (EditText) findViewById(R.id.signup_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
    }

    public void onSignUpFinishInsertClick(View view) {
        String username = signup_username.getText().toString().trim();
        String password = signup_password.getText().toString().trim();
        String confirmpassword = confirm_password.getText().toString().trim();
        int result = helper.findByUsername(username);
        System.out.printf("%d",result);
        if(result == 1){
            Toast.makeText(this, R.string.msg_UserNameHasBeenRegistered,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (username.length() <= 0) {
            Toast.makeText(this, R.string.msg_UserNameIsInvalid,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() <= 0) {
            Toast.makeText(this, R.string.msg_PasswordInvalid,
                    Toast.LENGTH_SHORT).show();
            return;
        }else if(password.length() < 8 || password.length() > 12){
            Toast.makeText(this, R.string.msg_PasswordLength,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(!confirmpassword.equals(password)){
            Toast.makeText(this,R.string.msg_PasswordNotConform,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        UserSpot userSpot = new UserSpot(username, password);
        long rowId = helper.insert(userSpot);
        if (rowId != -1) {
            Toast.makeText(this, R.string.msg_SignUpSuccess,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.msg_SignUpFail,
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.close();
        }
    }
}