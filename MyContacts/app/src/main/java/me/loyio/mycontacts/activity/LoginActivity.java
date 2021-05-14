package me.loyio.mycontacts.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText nameEdit;
    private EditText passwordEdit;
    private CheckBox rememberPass;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameEdit=(EditText) findViewById(R.id.account);
        passwordEdit=(EditText) findViewById(R.id.passwordEdit);
        rememberPass=(CheckBox) findViewById(R.id.remeber_pass);
        login=(Button) findViewById(R.id.Login_button);
        pref= (SharedPreferences) getSharedPreferences("data",MODE_PRIVATE);
        boolean isRemember=pref.getBoolean("remember_password",false);
        if (isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            nameEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=nameEdit.getText().toString();
                String password=passwordEdit.getText().toString();

                if(account.equals(pref.getString("account","admin"))&&password.equals(pref.getString("password",""))){
                    editor=pref.edit();
                    if (rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);


                    }else{
                        editor.clear();

                    }
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"密码或用户名输入错误",Toast.LENGTH_SHORT).show();

                }
            }
        });




    }
}