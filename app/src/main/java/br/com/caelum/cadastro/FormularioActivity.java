package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private Aluno aluno;
    private String localArquivo;
    private static final int TIRA_FOTO = 321;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TIRA_FOTO){
            if(resultCode == Activity.RESULT_OK){
                helper.carregaImagem(this.localArquivo);
            }else{
                this.localArquivo = null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);


        this.aluno = (Aluno) getIntent().getSerializableExtra("selecionado");

        if(this.aluno != null){
            helper.popularForm(aluno);
        }

        Button botaoFoto = (Button) findViewById(R.id.formulario_botao);
        if (botaoFoto != null) {
            botaoFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    localArquivo = getExternalFilesDir(null) + "/" +
                            System.currentTimeMillis()+".jpg";

                    File arquivo = new File(localArquivo);
                    Uri localFoto = Uri.fromFile(arquivo);

                    Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);

                    startActivityForResult(irParaCamera,TIRA_FOTO);
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_formulario:
//                Toast.makeText(this,"OK Clicado",Toast.LENGTH_LONG).show();

                aluno = helper.getFromHelper();

                if (helper.temNome()){
                    AlunoDAO dao = new AlunoDAO(this);
                    if(aluno.getId() != null){
                        dao.alterar(aluno);
                    }else {
                        dao.inserir(aluno);
                    }
                }else{
                    helper.mostraErro();
                }
                finish();
                return false;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
