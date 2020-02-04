package br.com.igreja.cellapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.model.Membro;

public class ListaMembrosNascimentoAdapter extends BaseAdapter {

    private List<Membro> listaDeMembros;
    private Activity activity;

    public ListaMembrosNascimentoAdapter(List<Membro> listaDeMembros, Activity activity) {
        this.listaDeMembros = listaDeMembros;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaDeMembros.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeMembros.get(position);
    }

    @Override
    public long getItemId(int position) {
        Membro membro = listaDeMembros.get(position);
        return membro.getIdMembro();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Membro membro = listaDeMembros.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();
        View listagem = inflater.inflate(R.layout.lista_membros_layout, null);

        TextView nomeDoMembro = (TextView) listagem.findViewById(R.id.nomeDoMembro);
        nomeDoMembro.setText(membro.getNome());

        TextView dataNasc = (TextView) listagem.findViewById(R.id.dataNasc);
        dataNasc.setText(membro.getDataNasc());

        String dia = "01";
        GregorianCalendar gc = new GregorianCalendar();

        if (gc.get(Calendar.DAY_OF_MONTH) < 10) {
            dia = "0" + gc.get(Calendar.DAY_OF_MONTH);
        } else {
            dia = String.valueOf(gc.get(Calendar.DAY_OF_MONTH));
        }

        final ImageView foto = (ImageView) listagem.findViewById(R.id.fotoDoMembro);

        int[] imagens = {R.drawable.mapp_ic_emotion_choro, R.drawable.mapp_ic_emotion_alivio, R.drawable.mapp_ic_emotion_espanto
                , R.drawable.mapp_ic_emotion_espanto_constrangido, R.drawable.mapp_ic_emotion_sem_graca, R.drawable.mapp_ic_emotion_feliz
                , R.drawable.mapp_ic_emotion_e_agora, R.drawable.mapp_ic_emotion_surpreso, R.drawable.mapp_ic_emotion_timido
                , R.drawable.mapp_ic_emotion_triste, R.drawable.mapp_ic_emotion_aff, R.drawable.mapp_ic_emotion_feliz};

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(700);
        fadeIn.setFillAfter(true);

        foto.startAnimation(fadeIn);

        foto.setImageResource(imagens[new Random().nextInt(imagens.length)]);

       if (dia.equals(membro.getDataNasc().split("/")[0])) {
            TextView celulaDoMembro = (TextView) listagem.findViewById(R.id.nomeDaCelula);
            celulaDoMembro.setText("HOJE !!!");
            celulaDoMembro.setTextColor(activity.getResources().getColor(R.color.verde));
        }

        return listagem;
    }

}
