package android.day3.lab1.loginnote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String SETTING_INFO = "User_Info";
    public static final String USER_NAME = "User_Name";
    public static final String USER_PASSWORD = "User_Password";
    public static final String REGISTERED_FLAG = "Registered_Flag";
    public static boolean isRegistered;
    SharedPreferences register;
    EditText userNameTxt;
    EditText passwordTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = getSharedPreferences(SETTING_INFO,MODE_PRIVATE);
        isRegistered = register.getBoolean(REGISTERED_FLAG,false);
        if(!isRegistered)
        {
            userNameTxt = (EditText) findViewById(R.id.editTextUserName);
            passwordTxt = (EditText) findViewById(R.id.editTextPassword);
        }
        else
        {
            Intent intent = new Intent(LoginActivity.this , MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this , "Login Sucessfuly" ,Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void registerMethod(View view) {
        //SharedPreferences register = getSharedPreferences(SETTING_INFO,MODE_PRIVATE);
        SharedPreferences.Editor editor = register.edit();
        editor.putString(USER_NAME,userNameTxt.getText().toString());
        editor.putString(USER_PASSWORD,passwordTxt.getText().toString());
        editor.putBoolean(REGISTERED_FLAG , true);
        editor.commit();
        //zay alart bytla3 t7t (pupup dialog)
        Toast.makeText(LoginActivity.this , "Register Sucessfuly" ,Toast.LENGTH_SHORT).show();
    }

    public void loginMethod(View view) {
        //SharedPreferences register = getSharedPreferences(SETTING_INFO,MODE_PRIVATE);
        String userNameStr = register.getString(USER_NAME,"");
        String passwordStr = register.getString(USER_PASSWORD,"");
        userNameTxt.setText(userNameStr);
        passwordTxt.setText(passwordStr);
        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(intent);
        Toast.makeText(LoginActivity.this , "Login Sucessfuly" ,Toast.LENGTH_SHORT).show();
        finish();
    }
}
