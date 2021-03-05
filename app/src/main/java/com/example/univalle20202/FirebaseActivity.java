package com.example.univalle20202;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseActivity extends AppCompatActivity {

    Button btnPushFirebaseRealtime;
    EditText etMessageDescription, etReceiver;
    String username, password;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        // Enlazamiento
        btnPushFirebaseRealtime = findViewById(R.id.btnPushFirebaseRealtime);
        btnPushFirebaseRealtime.setBackgroundColor(getResources().getColor(R.color.firebase_color)); // set color button

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
        Message messages = new Message(message_description, receiver);
        mDatabase.child("last_message").child(username).setValue(messages);

        Toast.makeText(getApplicationContext(), "Instanciado en firebase exitosamente", Toast.LENGTH_SHORT).show();
        etMessageDescription.setText("");
        etReceiver.setText("");
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

