package br.com.igreja.cellapp.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Membro;
import br.com.igreja.cellapp.util.WebClientCellApp;

public class ListaMembrosAdapter extends BaseAdapter{

	private List<Membro> listaDeMembros = new ArrayList<>();
	private List<Celula> listaCelula = new ArrayList<>();
	private Activity activity;
	
	public ListaMembrosAdapter(List<Membro> listaDeMembros, Activity activity, List<Celula> listaCelula) {
		this.listaCelula = listaCelula;
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
		
		final ImageView foto = (ImageView) listagem.findViewById(R.id.fotoDoMembro);

		String fotoByte = membro.getFoto();

		if (fotoByte.equals("")){
					
			AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
			fadeIn.setDuration(700);
			fadeIn.setFillAfter(true);
			
			foto.startAnimation(fadeIn);
			foto.setImageResource(R.drawable.ic_semfoto);
							
		} else {

				byte[] decode = Base64.decode(fotoByte, Base64.DEFAULT);
				Bitmap bm = BitmapFactory.decodeByteArray(decode, 0, decode.length);
				Options op = new BitmapFactory.Options();
				op.inScaled = false;
				Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bm, 500, 500, true);

				AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
				fadeIn.setDuration(700);
				fadeIn.setFillAfter(true);

				foto.startAnimation(fadeIn);
				foto.setImageBitmap(createScaledBitmap);
		}
		
		TextView celulaDoMembro = (TextView) listagem.findViewById(R.id.nomeDaCelula);
		Celula celulaAssociada = new WebClientCellApp().getCelulaAssociadaComMembro(listaCelula, membro);

		try{
			celulaDoMembro.setText(celulaAssociada.getNome());
		}catch (NullPointerException npe){
			celulaDoMembro.setText("Sem célula");
		}

		return listagem;
	}

}
