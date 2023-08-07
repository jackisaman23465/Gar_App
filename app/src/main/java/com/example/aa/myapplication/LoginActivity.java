package com.example.aa.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    EditText login_password;
    EditText login_username;
    Button loginbtn;
    Button signupbtn;
    UserSQLite helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        signupbtn = (Button) findViewById(R.id.signupbtn);
        if (helper == null) {
            helper = new UserSQLite(this);
        }
        loginbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
               login();
            }
        });
        signupbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                login_username.setText(null);
                login_password.setText(null);
            }
        });
    }

    public void login(){
        if (isUserNameAndPwdValid()) {
            String loginusername = login_username.getText().toString().trim();
            String loginpassword = login_password.getText().toString().trim();
            int result=helper.findByUsernameAndPassword(loginusername, loginpassword);
            if(result==1){
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, getString(R.string.msg_LoginSuccess),
                        Toast.LENGTH_SHORT).show();
                login_username.setText(null);
                login_password.setText(null);
            }else if(result==0){
                Toast.makeText(this, getString(R.string.msg_LoginFail),
                        Toast.LENGTH_SHORT).show();
                login_username.setText(null);
                login_password.setText(null);
                return;
            }
        }
    }

    public boolean isUserNameAndPwdValid() {
        if (login_username.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.msg_UserNameIsInvalid),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (login_password.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.msg_PasswordInvalid),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}