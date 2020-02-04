package br.com.igreja.cellapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Regiao;

public class ListaCelulasAdapter extends BaseAdapter {

    private List<Celula> listaDeCelulas = new ArrayList<>();
    private Activity activity;
    private List<Regiao> regioes = new ArrayList<>();

    public ListaCelulasAdapter(List<Celula> listaDeCelulas, Activity activity, List<Regiao> regioes) {
        this.listaDeCelulas = listaDeCelulas;
        this.activity = activity;
        this.regioes = regioes;
    }

    @Override
    public int getCount() {
        return listaDeCelulas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeCelulas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaDeCelulas.get(position).getIdCelula();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Celula celula = listaDeCelulas.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        View listagem = inflater.inflate(R.layout.lista_celulas_layout, null);

        TextView nomeDaCelula = (TextView) listagem.findViewById(R.id.nomeDaCelula);
        nomeDaCelula.setText(celula.getNome());

        TextView dataCriacao = (TextView) listagem.findViewById(R.id.dataCriacao);
        dataCriacao.setText(celula.getDataCriacao());

        TextView nomeDaRegiao = (TextView) listagem.findViewById(R.id.nomeDaRegiao);

        for (Regiao r : regioes) {
            if (celula.getIdRegiao().equals(r.getIdRegiao())) {
                nomeDaRegiao.setText(activity.getString(R.string.regiao) + ": " + r.getCor());
                nomeDaRegiao.setTextColor(activity.getResources().getColor(

                        r.getIdRegiao() == 1 ? R.color.royal_blue :
                                r.getIdRegiao() == 2 ? R.color.green :
                                        r.getIdRegiao() == 3 ? R.color.holo_orange_dark :
                                                r.getIdRegiao() == 4 ? R.color.red :
                                                        r.getIdRegiao() == 5 ? R.color.DarkMagenta :
                                                                R.color.yellow));
            }
        }

        return listagem;
    }

}
