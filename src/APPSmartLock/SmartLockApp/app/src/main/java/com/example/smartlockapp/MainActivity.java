package com.example.smartlockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_login, btn_register;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        setTitle ( "Smart_Lock" );


        sessionManager=new SessionManager ( this );
        if(sessionManager.isLogged ()){
            Intent intent = new Intent (this, LockActivity.class );
            startActivity ( intent );
            finish ();
        }

        btn_login= (Button)findViewById ( R.id.btn_login );
        btn_register= (Button)findViewById ( R.id.btn_register );


        btn_register.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent ( getApplicationContext (),RegisterUser.class );
                startActivity ( intent );

            }
        } );
        btn_login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent ( getApplicationContext (),LoginUser.class );
                startActivity ( intent );

            }
        } );


    }
}