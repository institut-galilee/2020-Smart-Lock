package com.example.smartlockapp.myrequest;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRequest {
    private Context context;
    private RequestQueue queue;


    public MyRequest(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }


    public void register(final String nom, final String prenom, final String pseudo, final String email, final String password, final String password2, final RegisterCallback callback){
        String url = "http://192.168.0.12/smart_lock/register.php";
        StringRequest request= new StringRequest ( Request.Method.POST, url, new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {

                Map<String, String> errors = new HashMap<> (  );


                try {
                    JSONObject json = new JSONObject ( response );
                    Boolean error = json.getBoolean ( "error" );

                    if(!error){
                        //l'inserion s'est bien deroulé
                        callback.onSuccess ( "Vous etes bien inscrit" );

                    }else{
                        JSONObject messages = json.getJSONObject ( "message" );
                        if(messages.has ( "nom" )){
                            errors.put ( "nom", messages.getString ( "nom" ) );
                        }
                        if(messages.has ( "prenom" )){
                            errors.put ( "prenom", messages.getString ( "prenom" ) );
                        }
                        if(messages.has ( "pseudo" )){
                            errors.put ( "pseudo", messages.getString ( "pseudo" ) );
                        }
                        if(messages.has ( "email" )){
                            errors.put ( "email", messages.getString ( "email" ) );
                        }
                       /* if(messages.has ( "telephone" )){
                            errors.put ( "telephone", messages.getString ( "telephone" ) );
                        }*/
                        if(messages.has ( "password" )){
                            errors.put ( "password", messages.getString ( "password" ) );

                        }
                        callback.inputErrors ( errors );

                    }
                } catch (JSONException e) {
                    e.printStackTrace ();
                }

                // Log.d("APP", response);

            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError ( "Impossible de se connecter" );
                }else if(error instanceof VolleyError){
                    callback.onError ( "une erreur s'est produite" );

                }

                //Log.d("APP", "ERROR"+ error);

            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<> (  );
                map.put ( "nom",nom );
                map.put ( "prenom",prenom );
                map.put ( "pseudo", pseudo );
                map.put ( "email", email );
               // map.put ( "telephone", String.valueOf ( telephone ) );
                map.put ( "password", password );
                map.put ( "password2", password2 );


                return map;
            }
        };
        queue.add ( request );
    }

    public interface RegisterCallback{
        void onSuccess(String message);
        void inputErrors(Map<String, String> errors);
        void onError(String message);
    }
    // connexion
    public void connection (final String pseudo, final String password, final LoginCallback callback){
        String url = "http://192.168.0.12/smart_lock/login.php";
        StringRequest request= new StringRequest ( Request.Method.POST, url, new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                JSONObject json;
                try {
                    json= new JSONObject ( response );
                    Boolean error= json.getBoolean ( "error" );
                    if(!error){
                        String id = json.getString ( "id" );
                        String pseudo = json.getString ( "pseudo" );
                        callback.onSuccess ( id,pseudo );

                    }else{
                        callback.onError ( json.getString ( "message" ) );
                    }
                } catch (JSONException e) {
                    callback.onError ( "une erreur s'est produite" );
                    e.printStackTrace ();
                }


                // Log.d("APP", response);

            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError ( "Impossible de se connecter" );
                }else if(error instanceof VolleyError){
                    callback.onError ( "une erreur s'est produite" );

                }

                //Log.d("APP", "ERROR"+ error);

            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<> (  );
                map.put ( "pseudo", pseudo );
                map.put ( "password", password );



                return map;
            }
        };
        queue.add ( request );


    }
    public interface LoginCallback{
        void onSuccess(String id, String pseudo);
        void onError(String message);
    }
    //#################################################### historique#####################################
    public void historique(final String pseudo, final String statut){
        String url = "http://192.168.0.12/smart_lock/historique.php";
        StringRequest request= new StringRequest ( Request.Method.POST, url, new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                Map<String, String> errors = new HashMap<> (  );
                try {
                    JSONObject json = new JSONObject ( response );
                    Boolean error = json.getBoolean ( "error" );

                } catch (JSONException e) {
                    e.printStackTrace ();
                }

                // Log.d("APP", response);

            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Log.d("APP", "ERROR"+ error);

            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<> (  );
                map.put ( "pseudo", pseudo );
                map.put ( "statut",statut );

                return map;
            }
        };
        queue.add ( request );
    }

//###########################################################################################################


}
