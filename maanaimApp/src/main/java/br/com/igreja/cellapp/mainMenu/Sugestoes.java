package br.com.igreja.cellapp.mainMenu;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.util.Mensagens;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Sugestoes extends Activity{

	@BindView(R.id.botaoEnviarSugestao) Button botaoEnviar;
	@BindView(R.id.botaoCancelarSugestao) Button botaoCancelar;
	@BindView(R.id.editTextSugestao) EditText editTextSugestao;

	private Mensagens mensagens =  new Mensagens(this);
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sugestoes);
		ButterKnife.bind(this);

	}

	@OnClick(R.id.botaoCancelarSugestao)
	public void onClickOnCancelButton(){
		startActivity(new Intent(Sugestoes.this, Inicio.class));
	}

	@OnClick(R.id.botaoEnviarSugestao)
	public void onClickOnSendButton(){
		Intent email = new Intent(Intent.ACTION_SEND);
		email.setType("message/rfc822");
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.email_admin)});
		email.putExtra(Intent.EXTRA_SUBJECT, "[Aplicativo Maanaim] - [Sugestão]");
		email.putExtra(Intent.EXTRA_TEXT   , editTextSugestao.getText().toString()+"\n\n\n\nVisitante\nEnviado pelo aplicativo Maanaim.");

		try {
			startActivity(Intent.createChooser(email, getString(R.string.enviar_email)));
		} catch (android.content.ActivityNotFoundException ex) {
			mensagens = new Mensagens(Sugestoes.this);
			mensagens.alertDialogMensagemOK(getString(R.string.email), getString(R.string.nao_ha_app_email)+". "+ getString(R.string.sugestao_pc)+
					getString(R.string.email_admin), R.drawable.mapp_ic_email);
		}

//		startActivity(email);
	}
}
