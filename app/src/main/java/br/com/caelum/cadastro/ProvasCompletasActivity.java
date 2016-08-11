package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

/**
 * Created by wanderson on 23/06/16.
 */
public class ProvasCompletasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_prova_topico);

        ExpandableListView listaprovas = (ExpandableListView)
                findViewById(R.id.lista_expansivel_provas);
    }
}
