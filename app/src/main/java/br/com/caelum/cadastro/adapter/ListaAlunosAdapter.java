package br.com.caelum.cadastro.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by wanderson on 03/06/16.
 */
public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Activity activity;

    public ListaAlunosAdapter(List<Aluno> alunos, Activity activity) {
        this.alunos = alunos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.alunos.size();
    }

    @Override
    public Object getItem(int position) {

        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {

        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.item,parent,false);
        Aluno aluno = alunos.get(position);

        if(position % 2 == 0){
            view.setBackgroundColor(activity.getResources().getColor(R.color.LinhaPar));
        }else {
            view.setBackgroundColor(activity.getResources().getColor(R.color.LinhaImpar));
        }

        TextView nome = (TextView) view.findViewById(R.id.item_nome);
        nome.setText(aluno.toString());

        Bitmap bm;
        if(aluno.getCaminhoFoto() != null){
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        } else{
            bm = BitmapFactory.decodeResource(activity.getResources(),R.drawable.person);
        }

        bm = Bitmap.createScaledBitmap(bm,100, 100,true);

        ImageView foto = (ImageView) view.findViewById(R.id.item_foto);
        foto.setImageBitmap(bm);

        TextView telefone = (TextView) view.findViewById(R.id.item_telefone);


        if(telefone != null){
            telefone.setText(aluno.getTelefone());
        }

        TextView site = (TextView) view.findViewById(R.id.item_site);

        if(site != null){
            site.setText(aluno.getSite());
        }



        return  view;
    }
}
