package com.example.laboratorioars2019;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListaFrasesActivity extends AppCompatActivity {

    ArrayList<Frase> frases = new ArrayList<Frase>();
    ListView lista;
    AdapterItem adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_frases);
        FirebaseFirestore.getInstance().collection("sampleData2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Frase a;
                for(DocumentSnapshot aux : queryDocumentSnapshots){
                    a = aux.toObject(Frase.class);
                    a.setId(aux.getId());
                    frases.add(a);
                }
                lista = (ListView)findViewById(R.id.listaFrases);

                Log.d("List", frases.toString());

                adaptador = new AdapterItem(ListaFrasesActivity.this, frases);

                lista.setAdapter(adaptador);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final int pos = position;
                        Log.d("Lista", "La posicion seleccionada es " + pos);
                        Intent i = new Intent(ListaFrasesActivity.this, MainActivity.class);
                        i.putExtra("id",frases.get(pos).getId());
                        startActivity(i);
                    }
                });

                FloatingActionButton button = findViewById(R.id.addButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ListaFrasesActivity.this, MainActivity.class);
                        i.putExtra("dummy", 0);
                        startActivity(i);
                    }
                });
            }
        });


    }
}
