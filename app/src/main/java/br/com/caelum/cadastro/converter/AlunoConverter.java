package br.com.caelum.cadastro.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by wanderson on 08/06/16.
 */
public class AlunoConverter {



    public String toJSON(List<Aluno> alunos){
        try {
            JSONStringer jsonStringer = new JSONStringer();
            jsonStringer.object().key("list").array()
                    .object().key("aluno").array();

            for (Aluno aluno : alunos) {

                jsonStringer.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("telefone").value(aluno.getTelefone())
                        .key("nota").value(aluno.getNota())
                        .key("site").value(aluno.getNota())
                        .key("caminhofoto").value(aluno.getCaminhoFoto())
                        .endObject();

            }
            return  jsonStringer.endArray().endObject()
                    .endArray().endObject().toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}
