package br.com.caelum.cadastro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.caelum.cadastro.adapter.ListaAlunosAdapter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.task.EnviarAlunosTask;

public class ListaAlunosActivity extends AppCompatActivity {

    ListView listaAlunos;
    Button botaoAdd;

    List<Aluno> alunos;

    private void carregarLista(){
        AlunoDAO dao = new AlunoDAO(this);

        alunos = dao.getAlunos();


        ListaAlunosAdapter alunosAdapter = new ListaAlunosAdapter(alunos,this);

//        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1, alunos);


        listaAlunos.setAdapter(alunosAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_enviar_notas: {
/*                AlunoDAO dao = new AlunoDAO(this);
                List<Aluno> alunos = dao.getAlunos();

                String json = new AlunoConverter().toJSON(alunos);

                WebClient client = new WebClient();
                String resposta = client.post(json);

                Toast.makeText(this, resposta, Toast.LENGTH_LONG).show();*/

                new EnviarAlunosTask(this).execute();
                return true;
            }

            case R.id.menu_receber_provas:{
                Intent provas = new Intent(this,ProvasActivity.class);
                startActivity(provas);
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                menuInfo;
        final Aluno alunoSelecionado = (Aluno)listaAlunos.getAdapter().getItem(info.position);

        menu.add("Ligar");
        menu.add("Achar no Mapa");
        menu.add("Navegar no Site");

        MenuItem deletar =  menu.add("Deletar");
        MenuItem ligar = menu.add("Enviar SMS");
        MenuItem galeria = menu.add("Galeria");

        galeria.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent irParaGaleria = new Intent(ListaAlunosActivity.this, GaleriaActivity.class);
                startActivity(irParaGaleria);

                return false;
            }
        });

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirmação")
                        .setMessage("Confirma a exclusão ?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                                dao.excluir(alunoSelecionado);
                                carregarLista();
                            }
                        }).setNegativeButton("Não",null).show();
                return false;
            }
        });


        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent ligarAluno = new Intent(Intent.ACTION_CALL);
                ligarAluno.setData(Uri.parse("tel:"+alunoSelecionado.getTelefone()));
//                setIntent(ligarAluno);
                return false;
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        Permissao.fazPermissao(this);
        listaAlunos = (ListView)findViewById(R.id.lista_alunos);
        registerForContextMenu(listaAlunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(ListaAlunosActivity.this,"Posição selecionada: "+ position,Toast.LENGTH_LONG).show();
                Intent irParaCadastro = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                Aluno alunoSel = (Aluno) parent.getItemAtPosition(position);
                irParaCadastro.putExtra("selecionado",alunoSel);
                startActivity(irParaCadastro);
            }
        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String aluno = parent.getItemAtPosition(position).toString();
                //Toast.makeText(ListaAlunosActivity.this,"Aluno : "+ aluno,Toast.LENGTH_LONG).show();

                return false;
            }
        });


        botaoAdd = (Button)findViewById(R.id.floating_button);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chamaCadastro = new Intent(ListaAlunosActivity.this,FormularioActivity.class);
                startActivity(chamaCadastro);
            }
        });


    }
}
