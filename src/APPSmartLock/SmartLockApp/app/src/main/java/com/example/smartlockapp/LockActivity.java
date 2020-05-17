package com.example.smartlockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.smartlockapp.myrequest.MyRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class LockActivity extends AppCompatActivity {

    /*private final String DEVICE_ADDRESS = "00:14:03:05:F2:7E"; //Adresse MAC du module HC-05
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //UID commun des bluetooth HC-05

    private BluetoothDevice device;
    private BluetoothSocket socket;

    private OutputStream outputStream;*/


    SessionManager sessionManager;
    private TextView textView;
    private Button btn_logout, liste;
    boolean connected = false;
    private String command;
    private Button verrou, deverrou;
    private TextView texte_etat;
    private ImageView image_etat;
    private RequestQueue queue;
    private MyRequest request;
    private String pseudo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_lock );
        setTitle ( "LOCK / UNLOCK" );

        queue= VolleySingleton.getInstance ( this ).getRequestQueue ();
        request = new MyRequest ( this, queue );
        verrou = (Button) findViewById(R.id.bouton_verrou);
        deverrou = (Button) findViewById(R.id.bouton_deverrou);
        texte_etat = (TextView) findViewById(R.id.texte_etat);
        image_etat = (ImageView) findViewById(R.id.lock_state_img);
         textView = (TextView)findViewById ( R.id.tv_pseudo );

        sessionManager = new SessionManager ( this );
        if(sessionManager.isLogged ()){
            pseudo = sessionManager.getPseudo ();
            String id = sessionManager.getId ();
            //textView.setText ( pseudo );

            verrou.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    /*if (BTinit()){

                            BTconnect();*/
                        request.historique (pseudo,"FERMER");
                        command = "0";
                        texte_etat.setTextColor( Color.RED);
                        texte_etat.setText("Etat actuel : VERROUILLE");
                        //texte_etat.setText("Etat : VERROUILLE"); // Changes the lock state text
                        image_etat.setImageResource(R.drawable.lock_2); //Changes the lock state icon
                    /*try
                    {
                        outputStream.write(command.getBytes()); //On envoie la requete au serveur qui est Arduino ici
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }*/



            }
            });


        }
        if(sessionManager.isLogged ()) {
            pseudo = sessionManager.getPseudo ();
            String id = sessionManager.getId ();
            textView.setText ( pseudo );
            deverrou.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v)
                {
                    /*if (BTinit()){*/

                    request.historique ( pseudo, "OUVERT");

                    command = "1";

                    texte_etat.setTextColor(Color.GREEN);
                    texte_etat.setText("Etat actuel : DEVERROUILLE");
                    image_etat.setImageResource ( R.drawable.unlock );
                /*    try
                    {
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }*/


                }
            } );
        }

    }

    //partie Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.res_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.voir_liste:
                Intent intentVoirList= new Intent(getApplicationContext(),ActivityHistorique.class);
                startActivity(intentVoirList);
                return true;
            case R.id.deconnect:
                sessionManager.logout ();
                Intent intent = new Intent ( getApplicationContext (),MainActivity.class);
                startActivity ( intent );
                finish ();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    //#######################################################################################################################


    /*//On initialise le module bluetooth
    public boolean BTinit()
    {
        boolean blue_trouve = false;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null) //Checks if the device supports bluetooth
        {
            Toast.makeText(getApplicationContext(), "Ce périphériphe ne supporte pas le bluetooth", Toast.LENGTH_SHORT).show();
        }

        //Checks if bluetooth is enabled. If not, the program will ask permission from the user to enable it
        if(!bluetoothAdapter.isEnabled())
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter,0);

            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        //on récupère la liste des périphériques appariés au module Bluetooth
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        if(bondedDevices.isEmpty()) //Checks for paired bluetooth devices
        {
            Toast.makeText(getApplicationContext(), "Connectes-toi d'abord à un périphérique Stp", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Parcours de la liste des périphériques bluethooth
            for(BluetoothDevice iterator : bondedDevices)
            {
                //On vérifie le périphérique trouvé correspond à l'adresse mac du module bluethooth
                if(iterator.getAddress().equals(DEVICE_ADDRESS))
                {
                    device = iterator; //On récupère ledit périphérique
                    blue_trouve = true;
                    break; //On arrête brusquement l'itération
                }
            }
        }

        return blue_trouve;

    }

    public void BTconnect()
    {

        try
        {
            //Creates a socket to handle the outgoing connection
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
            socket.connect();

            Toast.makeText(getApplicationContext(),
                    "Connexion au périphérique bluetooth avec succès", Toast.LENGTH_LONG).show();
            connected = true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            connected = false;
        }

        if(connected)
        {
            try
            {
                outputStream = socket.getOutputStream(); //gets the output stream of the socket
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }


        }

    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }*/

}
