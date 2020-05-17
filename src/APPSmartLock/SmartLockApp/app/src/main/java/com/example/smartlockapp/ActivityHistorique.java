package com.example.smartlockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityHistorique extends AppCompatActivity {
    ListView listView;
    MyAdapter adapter;
    public static ArrayList<Historique> historiqueArrayList = new ArrayList<> (  );
    String url ="http://192.168.0.12/smart_lock/histoData.php";
    Historique historique;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_historique );
        setTitle ( "Historique" );
        listView = (ListView)findViewById ( R.id.myListView );
        adapter = new MyAdapter ( this,historiqueArrayList );
        listView.setAdapter ( adapter );
        retrieveDaya();


    }
    public  void  retrieveDaya(){
        StringRequest request = new StringRequest ( Request.Method.POST, url,
                new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response) {
                        historiqueArrayList.clear ();
                        try {
                            JSONObject jsonObject= new JSONObject ( response );
                            String success =jsonObject.getString ( "success" );
                            JSONArray jsonArray = jsonObject.getJSONArray ( "data" );
                            if(success.equals ( "1" )){
                                for(int i= 0; i<jsonArray.length (); i++){
                                    JSONObject object = jsonArray.getJSONObject ( i );

                                    String id = object.getString ( "id" );
                                    String  pseudo = object.getString ( "pseudo" );
                                    String statut = object.getString ( "statut" );
                                    historique = new Historique ( id,pseudo,statut );
                                    historiqueArrayList.add ( historique );
                                    adapter.notifyDataSetChanged ();



                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace ();
                        }


                    }
                }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText ( ActivityHistorique.this,error.getMessage (),Toast.LENGTH_SHORT ).show ();


            }
        } );
        RequestQueue requestQueue = Volley.newRequestQueue ( this );
        requestQueue.add ( request );

    }
}
