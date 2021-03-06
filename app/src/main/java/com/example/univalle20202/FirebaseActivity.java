package com.example.univalle20202;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseActivity extends AppCompatActivity {


    Button btnPushFirebaseRealtime, btnGetDataFirebase;
    EditText etMessageDescription, etReceiver;
    String username, password;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        // Enlazamiento
        btnPushFirebaseRealtime = findViewById(R.id.btnPushFirebaseRealtime);
        btnGetDataFirebase = findViewById(R.id.btnGetData);
        btnPushFirebaseRealtime.setBackgroundColor(getResources().getColor(R.color.firebase_color)); // set color button
        btnGetDataFirebase.setBackgroundColor(getResources().getColor(R.color.firebase_color));

        etMessageDescription = findViewById(R.id.editTextMessage);
        etReceiver = findViewById(R.id.editTextTo);

        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //
        Bundle dataReceive = getIntent().getExtras();
        username = dataReceive.getString("userName");
        password = dataReceive.getString("passwd");
    }


    public void PushFirebaseRealtime(View view) {
        String message_description = etMessageDescription.getText().toString();
        String receiver = etReceiver.getText().toString();
        if(message_description.equals("") || receiver.equals("")){
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            Message messages = new Message(message_description, receiver);
            mDatabase.child("last_message").child(username).setValue(messages);

            Toast.makeText(getApplicationContext(), "Instanciado en firebase exitosamente", Toast.LENGTH_SHORT).show();
            etMessageDescription.setText("");
            etReceiver.setText("");
        }
    }

    public void getMessageFirebase(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        mDatabase.child("last_message").child(username).child("message_description").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>(){
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    builder.setTitle("Último mensaje");
                    builder.setMessage(String.valueOf(task.getResult().getValue()));
                    builder.setPositiveButton("Aceptar", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }


    public class Message {

        public String message_description;
        public String receiver;

        public Message() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Message(String message_description, String receiver) {
            this.message_description = message_description;
            this.receiver = receiver;
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.firebase_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle data = new Bundle();
        switch (item.getItemId()) {
            case R.id.menuCerrarSesion:
                Intent irLogin = new Intent(getApplicationContext(), Login.class);
                irLogin.addFlags(irLogin.FLAG_ACTIVITY_CLEAR_TOP | irLogin.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(irLogin);
                return true;
            case R.id.menuVolver:
                Intent irIndex = new Intent(getApplicationContext(), Index.class);
                irIndex.addFlags(irIndex.FLAG_ACTIVITY_CLEAR_TOP | irIndex.FLAG_ACTIVITY_CLEAR_TASK);
                data.putString("userName",username);
                data.putString("passwd", password);
                irIndex.putExtras(data);
                startActivity(irIndex);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

