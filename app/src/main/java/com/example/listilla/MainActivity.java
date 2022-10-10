package com.example.listilla;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record implements Comparable<Record>{
        public int intents;
        public String nom;

        public Record(int _intents, String _nom ) {
            intents = _intents;
            nom = _nom;
        }

        public int compareTo(Record r) {

            return this.intents - r.intents;
        }
    }

    ArrayList<String> nomUser;
    int randomNum = (int) Math.floor(Math.random()*(100-1+1)+1);
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomUser = new ArrayList<String>();
        //Afegim noms
        nomUser.add("Manuel");
        nomUser.add("Manolo");
        nomUser.add("Paco");
        nomUser.add("Pepe");
        nomUser.add("David");
        nomUser.add("Genji");
        nomUser.add("Edgar");
        nomUser.add("Arbol milenario");
        nomUser.add("Nacho");
        nomUser.add("Maria");
        nomUser.add("Pepito");
        nomUser.add("Kira");
        nomUser.add("Sirena");
        nomUser.add("Huevo");
        nomUser.add("Eric");
        nomUser.add("Nadia");



        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        records.add( new Record(33,"Manolo") );
        records.add( new Record(12,"Pepe") );
        records.add( new Record(42,"Laura") );




        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.listilla);
        lv.setAdapter(adapter);

        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.addButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<30;i++) {
                    randomNum = (int) Math.floor(Math.random()*(100-1+1)+1);
                    int nomRandom = (int) Math.floor(Math.random()*(nomUser.size()-1+1));
                    records.add(new Record(randomNum, nomUser.get(nomRandom)));

                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });

        Button order = (Button) findViewById(R.id.orderButton);
        order.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Collections.sort(records);
                adapter.notifyDataSetChanged();
            }
        });
    }
}