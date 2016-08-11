package br.com.caelum.cadastro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.cadastro.ProvasActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

/**
 * Created by wanderson on 16/06/16.
 */
public class ListaProvasFragment extends Fragment {

    private ListView listViewProvas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas,container,false);

        listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas_listview);


        Prova prova1 = new Prova("28/06/2016", "Matematica");
        prova1.setTopicos(Arrays.asList("Álgebra","Estatística", "Cálculo"));

        Prova prova2 = new Prova("02/07/2016","Português");
        prova2.setTopicos(Arrays.asList("Gramática","Fonética", "Análise textual"));

        List<Prova> provas =  Arrays.asList(prova1,prova2);

        listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(),android.R.layout.simple_list_item_1,provas));


        this.listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova provaSelecionada = (Prova) parent.getItemAtPosition(position);
//                Toast.makeText(getActivity(),"Prova de: "+provaSelecionada,Toast.LENGTH_LONG).show();

                ProvasActivity calendarioProvas = (ProvasActivity) getActivity();
                calendarioProvas.selecionaProva(provaSelecionada);

            }
        });

        return layoutProvas;
    }
}
