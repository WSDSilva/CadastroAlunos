package br.com.caelum.cadastro.task;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.support.WebClient;

/**
 * Created by wanderson on 15/06/16.
 */
public class EnviarAlunosTask extends AsyncTask<Object, Object, String> {

    private Context context;
    ProgressDialog progressDialog;

    public EnviarAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object[] params) {

        AlunoDAO dao = new AlunoDAO(this.context);

        List<Aluno> alunos = dao.getAlunos();

        AlunoConverter converter = new AlunoConverter();

        String json = converter.toJSON(alunos);

        WebClient client = new WebClient();

        return client.post(json);


    }

    @Override
    protected void onPostExecute(String resultado) {
        progressDialog.dismiss();
        Toast.makeText(context,resultado,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Aguarde...","Envio de dados para a web",true, true);
    }


}
