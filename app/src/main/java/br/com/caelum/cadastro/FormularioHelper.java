package br.com.caelum.cadastro;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by wanderson on 20/05/16.
 */
public class FormularioHelper {
    private EditText nome;
    private EditText telefone;
    private EditText site;
    private EditText endereco;
    private RatingBar nota;
    private ImageView foto;
    private Button botaoFoto;

    private  Aluno aluno;

    public Button getBotaoFoto() {
        return botaoFoto;
    }

    public FormularioHelper(FormularioActivity formularioActivity) {

        aluno = new Aluno();
        nome = (EditText) formularioActivity.findViewById(R.id.formulario_nome);
        telefone = (EditText) formularioActivity.findViewById(R.id.formulario_telefone);
        site = (EditText) formularioActivity.findViewById(R.id.formulario_site);
        endereco = (EditText) formularioActivity.findViewById(R.id.formulario_endereco);
        nota = (RatingBar) formularioActivity.findViewById(R.id.formulario_nota);
        foto = (ImageView) formularioActivity.findViewById(R.id.formulario_foto);
        botaoFoto = (Button) formularioActivity.findViewById(R.id.formulario_botao);

    }


    public Aluno getFromHelper(){

        aluno.setNome(this.nome.getText().toString());
        aluno.setEndereco(this.endereco.getText().toString());
        aluno.setTelefone(this.telefone.getText().toString());
        aluno.setSite(this.site.getText().toString());
        //noinspection UnnecessaryBoxing
        aluno.setNota(Double.valueOf(nota.getProgress()));
        aluno.setCaminhoFoto((String) foto.getTag());

        return  aluno;

    }

    public boolean temNome(){
        return  !aluno.getNome().isEmpty();
    }

    public  void mostraErro(){
        nome.setError("Esse campo deve ser preechido!");
    }

    public void popularForm(Aluno aluno){
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;

        if(aluno.getCaminhoFoto() != null){
            this.carregaImagem(aluno.getCaminhoFoto());
        }
    }

    public void carregaImagem(String localArquivo) {

        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivo);
        Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagemFoto,200, 300, true);
        foto.setImageBitmap(imagemReduzida);
        foto.setTag(localArquivo);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);

        //imagemFoto.recycle();
        //imagemReduzida.recycle();
    }
}
