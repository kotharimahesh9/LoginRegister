package com.mahesh.hp.loginregister;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText name,email,phone,pass;
    private Button register;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.etName);
        email=(EditText)findViewById(R.id.etEmail);
        phone= (EditText)findViewById(R.id.etPhone);
        pass=(EditText)findViewById(R.id.etPass);
        register=(Button)findViewById(R.id.register);
        firebaseAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e=email.getText().toString().trim();
                String password=pass.getText().toString().trim();
                if(TextUtils.isEmpty(e)){
                    Toast.makeText(getApplicationContext(),"Enter your emailId",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_LONG).show();
                    return;
                }
                try{
                firebaseAuth.createUserWithEmailAndPassword(e,password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "User Registered ", Toast.LENGTH_LONG).show();
                                   Intent intent=new Intent(RegisterActivity.this,smsVerification.class);
                                   RegisterActivity.this.startActivity(intent);
                                }

                                else {
                                    Toast.makeText(getApplicationContext(),"Error Registering ",Toast.LENGTH_SHORT).show();

                                }
            }
        });} catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Exception occured " + ex,Toast.LENGTH_LONG).show();
                    Log.d("COPY",ex.toString());

                }
    }
});
    }
}
