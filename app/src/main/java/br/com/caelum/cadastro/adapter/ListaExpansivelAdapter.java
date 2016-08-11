package br.com.caelum.cadastro.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

/**
 * Created by wanderson on 23/06/16.
 */
public class ListaExpansivelAdapter extends BaseExpandableListAdapter {

    private List<Prova> provas;
    private Activity activity;

    public ListaExpansivelAdapter(Activity activity, List<Prova> provas) {
        this.provas = provas;
        this.activity = activity;
    }


    @Override
    public int getGroupCount() {
        return provas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return provas.get(groupPosition).getTopicos().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return provas.get(groupPosition)
                .getTopicos()
                .get(childPosition).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

       // TextView topicoLayout = (TextView) inflater.inflate(R.layout.listagem_prova_topico, parent, false)
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
