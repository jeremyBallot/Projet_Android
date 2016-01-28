package com.example.etu.projet_android;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by etu on 22/12/15.
 */
public class bddfragment extends Fragment {

    private etuaffiche dbHelper;
    private SimpleCursorAdapter dataAdapter;
    private ListView listView;
    private EditText myFilter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bddfragment , container, false);

        super.onCreate(savedInstanceState);

        listView = (ListView) view.findViewById(R.id.listView1);
        myFilter = (EditText) view.findViewById(R.id.myFilter);


        dbHelper = new etuaffiche(getActivity());
        dbHelper.open();

        // suppresion
        dbHelper.deleteAllETU();

        // insertion
        dbHelper.insertEtu();


        displayListView();

        return view;

    }

    private void displayListView() {


        Cursor cursor = dbHelper.fetchAll();

        // on va lister les etudiants
        String[] columns = new String[] {
                etuaffiche.KEY_NOM,
                etuaffiche.KEY_PRENOM,
                etuaffiche.KEY_AGE,
                etuaffiche.KEY_EMAIL
        };

        // Les vues XML dans lesquelles les données vont être liées
        int[] to = new int[] {
                R.id.nom ,
                R.id.prenom,
                R.id.age,
                R.id.email,
        };


        // Création de l'adaptateur appelé etuaffiche
        dataAdapter = new SimpleCursorAdapter(
                getActivity(), R.layout.etuaffichevue,
                cursor,
                columns,
                to,
                0);


        // utilisation de la ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView listView, View view, int position, long id) {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                String userFirstName = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
                Toast.makeText(getActivity().getApplicationContext(), userFirstName, Toast.LENGTH_SHORT).show();

            }
        });


        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return dbHelper.fetchNomEtu(constraint.toString());
            }
        });

    }

}
