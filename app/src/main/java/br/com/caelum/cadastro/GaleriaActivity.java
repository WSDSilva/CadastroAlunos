package br.com.caelum.cadastro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.List;

import br.com.caelum.cadastro.adapter.GaleriaAlunosAdapter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by wanderson on 21/06/16.
 */
public class GaleriaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galeria);

        ViewPager gallery = (ViewPager) findViewById(R.id.gallery);
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getAlunos();

        GaleriaAlunosAdapter adapter = new GaleriaAlunosAdapter(alunos, this);
        gallery.setAdapter(adapter);
    }
}
