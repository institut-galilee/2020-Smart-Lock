package com.example.smartlockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.smartlockapp.myrequest.MyRequest;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

public class RegisterUser extends AppCompatActivity {
    private Button btn_send;
    //private EditText et_pseudo, et_email, et_password, et_password2;
   // private ProgressBar pb_loader;
    private TextInputLayout til_nom,til_prenom, til_telephone, til_pseudo, til_email, til_password, til_password2;

    private RequestQueue queue;
    private MyRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_register_user );
        setTitle ( "Inscription" );

        btn_send=(Button)findViewById ( R.id.btn_send );
       // pb_loader=(ProgressBar)findViewById ( R.id.pb_loader );
        til_pseudo = (TextInputLayout) findViewById ( R.id.til_pseudo );
        til_email = (TextInputLayout)findViewById ( R.id.til_email );
        til_password = (TextInputLayout) findViewById ( R.id.til_password );
        til_password2 = (TextInputLayout) findViewById ( R.id.til_password2 );
        til_nom = (TextInputLayout)findViewById ( R.id.til_nom );
        til_prenom = (TextInputLayout) findViewById ( R.id.til_prenom );
        //til_telephone = (TextInputLayout) findViewById ( R.id.til_telephone );


        queue= VolleySingleton.getInstance ( this ).getRequestQueue ();
        request = new MyRequest ( this, queue );

        btn_send.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
               // pb_loader.setVisibility ( View.VISIBLE );
                String nom =til_nom.getEditText ().getText ().toString ().trim ();
                String prenom = til_prenom.getEditText ().getText ().toString ().trim ();
                String pseudo =til_pseudo.getEditText ().getText ().toString ().trim ();
                String email = til_email.getEditText ().getText ().toString ().trim ();
               // int telephone = Integer.parseInt ( til_telephone.getEditText ().getText ().toString ().trim () );
                String password =til_password.getEditText ().getText ().toString ().trim ();
                String password2 = til_password2.getEditText ().getText ().toString ().trim ();
                if(pseudo.length ()>0 && email.length () > 0 && password.length () > 0 && password2.length () >0){
                    request.register (nom,prenom, pseudo, email, password, password2, new MyRequest.RegisterCallback () {
                        @Override
                        public void onSuccess(String message) {
                           // pb_loader.setVisibility ( View.GONE );
                            Intent intent =new Intent ( getApplicationContext (), LoginUser.class );
                            intent.putExtra ( "REGISTER", message );
                            startActivity ( intent );
                            finish ();




                        }

                        @Override
                        public void inputErrors(Map<String, String> errors) {
                            //pb_loader.setVisibility ( View.GONE );
                            if(errors.get("nom") != null){
                                //Toast.makeText ( getApplicationContext (),"Pseudo non valide", Toast.LENGTH_SHORT ).show ();
                                til_nom.setError ( errors.get ( "nom" ) );
                            }else {
                                til_nom.setErrorEnabled ( false );
                            }
                            if(errors.get("prenom") != null){
                                //Toast.makeText ( getApplicationContext (),"Pseudo non valide", Toast.LENGTH_SHORT ).show ();
                                til_prenom.setError ( errors.get ( "prenom" ) );
                            }else {
                                til_prenom.setErrorEnabled ( false );
                            }
                            if(errors.get("pseudo") != null){
                                //Toast.makeText ( getApplicationContext (),"Pseudo non valide", Toast.LENGTH_SHORT ).show ();
                                til_pseudo.setError ( errors.get ( "pseudo" ) );
                            }else {
                                til_pseudo.setErrorEnabled ( false );
                            }
                            if(errors.get("email") != null){
                                //Toast.makeText ( getApplicationContext (),"Pseudo non valide", Toast.LENGTH_SHORT ).show ();
                                til_email.setError ( errors.get ( "email" ) );
                            }else {
                                til_email.setErrorEnabled ( false );
                            }
                            /*if(errors.get("telephone") != null){
                                //Toast.makeText ( getApplicationContext (),"Pseudo non valide", Toast.LENGTH_SHORT ).show ();
                                til_telephone.setError ( errors.get ( "telephone" ) );
                            }else {
                                til_telephone.setErrorEnabled ( false );
                            }*/
                            if(errors.get("password") != null){
                                //Toast.makeText ( getApplicationContext (),"Pseudo non valide", Toast.LENGTH_SHORT ).show ();
                                til_password.setError ( errors.get ( "password" ) );
                            }else {
                                til_password.setErrorEnabled ( false );
                            }


                        }

                        @Override
                        public void onError(String message) {
                           // pb_loader.setVisibility ( View.GONE );
                            Toast.makeText ( getApplicationContext (), message,Toast.LENGTH_SHORT ).show ();

                        }
                    } );

                }else {
                    Toast.makeText ( getApplicationContext (),"Veuillez remplir tous les champs", Toast.LENGTH_SHORT ).show ();
                }

            }
        } );



    }
}
