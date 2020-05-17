package com.example.smartlockapp;

import android.content.Context;
import android.content.SharedPreferences;
// gestion de la session
public class SessionManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private final static String PREFS_NAME= "app_prefs";
    private final static int PRIVATE_MODE = 0;
    private final static String IS_LOGGED = "isLogged";
    private  final  static  String PSEUDO = "pseudo";
    private  final static  String ID = "id";
    private final static String NOM="nom";
    private Context context;


    public SessionManager( Context context) {
        prefs = context.getSharedPreferences (  PREFS_NAME, PRIVATE_MODE);
        editor = prefs.edit ();
        this.context = context;
    }

    public boolean isLogged(){
        return prefs.getBoolean ( IS_LOGGED, false );

    }
    public  String getPseudo(){
        return prefs.getString ( PSEUDO, null );
    }
    public String getId(){
        return prefs.getString ( ID,null );

    }
    public String getNom(){
        return prefs.getString (NOM,null);
    }
    public void insertUser(String id, String pseudo){
        editor.putBoolean ( IS_LOGGED, true );
        editor.putString ( ID,id );
        editor.putString ( PSEUDO, pseudo );;
        editor.commit ();
    }
    public  void logout(){
        editor.clear ().commit ();
    }
}
