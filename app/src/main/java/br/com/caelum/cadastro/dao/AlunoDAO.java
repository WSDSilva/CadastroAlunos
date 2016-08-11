package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by wanderson on 20/05/16.
 */
public class AlunoDAO extends SQLiteOpenHelper {
    private  static final int VERSAO = 3;
    private  static final  String TABELA = "Alunos";
    private  static final String DATABASE = "CadastroCaelum";

    public AlunoDAO(Context context) {
        super(context, DATABASE,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA +" (id INTEGER PRIMARY KEY, "+
                        "nome TEXT NOT NULL, "+
                        "telefone TEXT, "+
                        "endereco TEXT, "+
                        "site TEXT, "+
                        "nota REAL);";

        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql;

        if(oldVersion <= 2) {
            sql = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT;";
            db.execSQL(sql);
        }



    }

    public void  inserir(Aluno aluno){
        ContentValues values = new ContentValues();

        values.put("nome", aluno.getNome());
        values.put("telefone",aluno.getTelefone());
        values.put("site", aluno.getSite());
        values.put("endereco", aluno.getEndereco());
        values.put("nota", aluno.getNota());
        values.put("caminhoFoto",aluno.getCaminhoFoto());

        getWritableDatabase().insert(TABELA,null,values);

    }

    public void excluir(Aluno aluno){
        getWritableDatabase().delete(TABELA,"id=?", new String[]{aluno.getId().toString()});
    }


    public List<Aluno> getAlunos(){
        List<Aluno> alunos = new ArrayList<Aluno>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABELA+";",null);

        while (cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));

            alunos.add(aluno);

        }

        cursor.close();
        return alunos;
    }

    public void alterar(Aluno aluno){
        ContentValues values = new ContentValues();

        values.put("id",aluno.getId());
        values.put("nome",aluno.getNome());
        values.put("telefone",aluno.getTelefone());
        values.put("endereco",aluno.getEndereco());
        values.put("site",aluno.getSite());
        values.put("nota",aluno.getNota());
        values.put("caminhoFoto",aluno.getCaminhoFoto());

        getWritableDatabase().update(TABELA,values, "id=?",new String[] {aluno.getId().toString()});

    }

    public boolean isAluno(String telefone){
        String[] paramentros = {telefone};
        Cursor cursor = getReadableDatabase()
                .rawQuery("SELECT telefone FROM "+TABELA+
                            " WHERE telefone = ?",paramentros);

        int total = cursor.getCount();
        return (total > 0);
    }
}
