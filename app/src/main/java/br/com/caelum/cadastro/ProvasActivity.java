package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragment.ListaProvasFragment;
import br.com.caelum.cadastro.modelo.Prova;

/**
 * Created by wanderson on 17/06/16.
 */
public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (isTable()){
            transaction.replace(R.id.provas_listas, new ListaProvasFragment());
            transaction.replace(R.id.provas_detalhes, new DetalhesProvaFragment());
        }else{
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }

        transaction.commit();

    }

    private boolean isTable(){
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void selecionaProva(Prova prova) {
        FragmentManager manager = getSupportFragmentManager();

        if(isTable()){
            DetalhesProvaFragment detalhesProva =
                    (DetalhesProvaFragment) manager.findFragmentById(R.id.provas_detalhes);

            detalhesProva.popularCamposComDados(prova);
        }else {
            Bundle argumentos = new Bundle();

            argumentos.putSerializable("prova",prova);
            DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
            detalhesProva.setArguments(argumentos);


            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.provas_view, detalhesProva);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }
}
