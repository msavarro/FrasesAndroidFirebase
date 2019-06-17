package com.example.laboratorioars2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    public static final String AUTOR_KEY = "autor";
    public static final String FRASE_KEY = "frase";
    private String path = "sampleData/inspiration";
    private Frase edit;
    private String idEdit;

    private DocumentReference mDocRef;
    private FirebaseFirestore db;

    TextView fraseTextView;

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        idEdit = (String) i.getExtras().get("id");
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        if(idEdit != null) {
            FirebaseFirestore.getInstance().document("sampleData2/" + idEdit).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    edit = documentSnapshot.toObject(Frase.class);
                    edit.setId(idEdit);
                    EditText fraseView = (EditText) findViewById(R.id.editTextFrase);
                    EditText autorView = (EditText) findViewById(R.id.editTextAutor);
                    fraseView.setText(edit.getFrase());
                    autorView.setText(edit.getAutor());
                }
            });
        }else{
            findViewById(R.id.deleteButton).setVisibility(View.GONE);
        }
    }

    public void guardarFrase(View view) {
        EditText fraseView = (EditText) findViewById(R.id.editTextFrase);
        EditText autorView = (EditText) findViewById(R.id.editTextAutor);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.editRatingBar);
        String fraseText = fraseView.getText().toString();
        String autorText = autorView.getText().toString();
        float rating = ratingBar.getRating();

        if (fraseText.isEmpty() || autorText.isEmpty()) {
            Log.d("Edit", "guardarFrase: ");
            return;
        }
        if (edit == null) {
            edit = new Frase();
        }
        edit.setAutor(autorText);
        edit.setFrase(fraseText);
        if (rating != 0) {
            edit.setCantVotos(edit.getCantVotos() + 1);
            edit.setSumaActual(edit.getSumaActual() + rating);
        }
        if(idEdit == null){
            db.collection("sampleData2").add(edit).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    //edit.setId(documentReference.getId());
                    Log.d("InspirationFrase", "Documento guardado con exito");
                    Intent i = new Intent(MainActivity.this, ListaFrasesActivity.class);
                    startActivity(i);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("InspirationFrase", "Documento no fue guardado!!");
                }
            });
        }else{
            FirebaseFirestore.getInstance().document("sampleData2/" + idEdit).set(edit).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("InspirationFrase", "Documento guardado con exito");
                    Intent i = new Intent(MainActivity.this, ListaFrasesActivity.class);
                    startActivity(i);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("InspirationFrase", "Documento no fue guardado!!");
                }
            });
        }
        }

       public void eliminarFrase(View view){
           db.collection("sampleData2").document(idEdit).delete();
           Intent i = new Intent(MainActivity.this, ListaFrasesActivity.class);
           startActivity(i);
       }
}
