package br.com.igreja.cellapp.mainMenu;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.net.URLEncoder;
import java.util.ArrayList;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.adapter.SpinnerAdapter;
import br.com.igreja.cellapp.util.HttpRequest;
import br.com.igreja.cellapp.util.Mensagens;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Relatorios extends Activity {
	
	final String myTag = "DocsUpload";

	@BindView(R.id.nomeLider)
	EditText nomelider;
	@BindView(R.id.qtdinicio)
	EditText qtdInicio;
	@BindView(R.id.nomeAuxiliar)
	EditText nomeAux;
	@BindView(R.id.qtdpresentes)
	EditText qtdPresentes;
	@BindView(R.id.qtdmembros)
	EditText qtdMembros;
	@BindView(R.id.qtdcriancas)
	EditText qtdCriancas;
	@BindView(R.id.qtdvisitantes)
	EditText qtdVisitantes;
	@BindView(R.id.qtdtornmemb)
	EditText qtdTornMemb;
	@BindView(R.id.qtdtadel)
	EditText qtdTadel;
	@BindView(R.id.qtddisciulos)
	EditText qtdDiscipulos;
	@BindView(R.id.qtddiscipuladores)
	EditText qtdDiscipuladores;
	@BindView(R.id.datePickerInicio)
	DatePicker dataIniciou;
	@BindView(R.id.datePickerMultiplicacao)
	DatePicker dataMultiplicacao;
	@BindView(R.id.spinnerRegiao)
	Spinner regiao;
	@BindView(R.id.spinnerOferta)
	Spinner ofertas;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_relatorio);
		ButterKnife.bind(this);
		setUIelements();
	}

	private void setUIelements() {
		ArrayList<String> regioes = new ArrayList<String>();
		regioes.add("Azul"); regioes.add("Verde"); regioes.add("Vermelha");
		regioes.add("Amarela");	regioes.add("Branca"); regioes.add("Laranja");

		ArrayList<String> oferta = new ArrayList<String>();
		oferta.add("Sim"); oferta.add("Não");

		regiao.setAdapter(new SpinnerAdapter(this));

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_spinner_item, oferta);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ofertas.setAdapter(adapter2);
	}

	public void callPostData(View view) {
        Log.i(myTag, "Calling Post Data");

		if(validations()){
			if(dateValidation()){

				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {

						ConnectivityManager cm = (ConnectivityManager) Relatorios.this.getSystemService(Context.CONNECTIVITY_SERVICE);
						if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected() ||
						        cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {

							postData();

						} else {
							new Mensagens(Relatorios.this)
									.toastMensagem("Você não está conectado a internet. Conecte-se e tente novamente.",
											0, 0, 0, R.drawable.mapp_ic_olhos).show();
							return;
						}
					}
				});
				t.start();

				new Mensagens(this).toastMensagem("Relatório enviado !", 0, 0, 0, R.drawable.mapp_ic_ok).show();
				new Mensagens(this).alertDialogMensagemOK("Relatório enviado", "Muito obrigado por enviar o relatório. O aplicativo será fechado e te lembrará de enviar o proximo relatório dentro de 7 dias.", R.drawable.mapp_ic_anotacao).show();

			}
		}

    }

	private boolean dateValidation() {

		//TODO

		return true;
	}

	private boolean validations() {

		if(nomelider.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Nome do líder' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		if(qtdInicio.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Qtd inicio de membros' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		if(qtdMembros.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Qtd membros' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		if(qtdPresentes.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Qtd presentes' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		if(qtdCriancas.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Qtd crianças' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		if(qtdVisitantes.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Qtd visitantes' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		if(qtdTornMemb.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Qtd tornou membro' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		if(qtdTadel.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Qtd TADEL' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		if(qtdDiscipulos.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Qtd sendo discipulados' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		if(qtdDiscipuladores.getText().toString().trim().equals("")){
			new Mensagens(this).toastMensagem("O campo 'Qtd discipuladores' não pode estar vazio", 0, 0, 0, R.drawable.mapp_ic_anotacao).show();
			return false;
		}

		return true;

	}

	public void postData() {

		String fullUrl = "https://docs.google.com/forms/d/e/1FAIpQLSc3ALXmDR9DrE7q3ghzWoOAh4mSj2disDzMiMTkleUoqdLH_w/formResponse";
		HttpRequest mReq = new HttpRequest();

		//ESTES SÃO OS IDs DOS CAMPOS DO RELATÓRIO
		String encode = URLEncoder.encode("Relatório de Célula (Maanaim Osasco)");

		String data = encode+"=" + URLEncoder.encode(getResources().getStringArray(R.array.array_regioes)[regiao.getSelectedItemPosition()])+"&";		//PRE-REGIÃO
		data += "entry.1571726919=" + URLEncoder.encode(getResources().getStringArray(R.array.array_regioes)[regiao.getSelectedItemPosition()])+"&";	//REGIÃO
		data += "entry.1769411792_year=" + URLEncoder.encode(dataIniciou.getYear()+"")+"&";			//ANO QUE INICIOU A CÉLULA
		data += "entry.1769411792_day=" + URLEncoder.encode(dataIniciou.getDayOfMonth()+"")+"&";	//DIA QUE INICIOU A CÉLULA
		data += "entry.1769411792_month=" + URLEncoder.encode(dataIniciou.getMonth()+1+"")+"&";		//MES QUE INICIOU A CÉLULA
		data += encode+"=" + URLEncoder.encode(qtdInicio.getText().toString())+"&";					//QTD. INICIAL DE MEMBROS
		data += "entry.176038129=" + URLEncoder.encode(qtdInicio.getText().toString())+"&";			//QTD. INICIAL DE MEMBROS
		data += encode+"=" + URLEncoder.encode(nomelider.getText().toString())+"&";					//NOME DO LIDER
		data += "entry.1046989947=" + URLEncoder.encode(nomelider.getText().toString())+"&";		//NOME DO LIDER
		data += encode+"=" + URLEncoder.encode(nomeAux.getText().toString())+"&";					//NOME DO AUXILIAR
		data += "entry.1100887509=" + URLEncoder.encode(nomeAux.getText().toString())+"&";			//NOME DO AUXILIAR
		data += encode+"=" + URLEncoder.encode(qtdMembros.getText().toString())+"&";				//QTD MEMBROS DA CÉLULA
		data += "entry.1360257835=" + URLEncoder.encode(qtdMembros.getText().toString())+"&";		//QTD MEMBROS DA CÉLULA
		data += encode+"=" + URLEncoder.encode(qtdPresentes.getText().toString())+"&";				//QTD MEMBROS PRESENTES NA CÉLULA
		data += "entry.133772331=" + URLEncoder.encode(qtdPresentes.getText().toString())+"&";		//QTD MEMBROS PRESENTES NA CÉLULA
		data += encode+"=" + URLEncoder.encode(qtdCriancas.getText().toString())+"&";				//QTD DE CRIANÇAS
		data += "entry.1569701434=" + URLEncoder.encode(qtdCriancas.getText().toString())+"&";		//QTD DE CRIANÇAS
		data += encode+"=" + URLEncoder.encode(qtdVisitantes.getText().toString())+"&";				//QTD VISITANTES NO MES
		data += "entry.605197041=" + URLEncoder.encode(qtdVisitantes.getText().toString())+"&";		//QTD VISITANTES NO MES
		data += encode+"=" + URLEncoder.encode(qtdTornMemb.getText().toString())+"&";				//QTD VISITANTE SE TORNARAM MEMBROS
		data += "entry.1138565785=" + URLEncoder.encode(qtdTornMemb.getText().toString())+"&";		//QTD VISITANTE SE TORNARAM MEMBROS
		data += encode+"=" + URLEncoder.encode(qtdTadel.getText().toString())+"&";					//QTD MEMBROS NO TADEL
		data += "entry.1806445115=" + URLEncoder.encode(qtdTadel.getText().toString())+"&";			//QTD MEMBROS NO TADEL
		data += encode+"=" + URLEncoder.encode(qtdDiscipulos.getText().toString())+"&";				//QTD PESSOAS SENDO DISCIPULADAS
		data += "entry.158092542=" + URLEncoder.encode(qtdDiscipulos.getText().toString())+"&";		//QTD PESSOAS SENDO DISCIPULADAS
		data += encode+"=" + URLEncoder.encode(qtdDiscipuladores.getText().toString())+"&";			//QTD DE DISCIPULADORES
		data += "entry.630779926=" + URLEncoder.encode(qtdDiscipuladores.getText().toString())+"&";	//QTD DE DISCIPULADORES
		data += "entry.820468864=" + URLEncoder.encode(ofertas.getSelectedItem().toString())+"&";	//RECOLHEU OFERTA? (Sim OU Não)
		data += "entry.7695192_year=" + URLEncoder.encode(dataMultiplicacao.getYear()+"")+"&";		//PREVISÃO MULTIPLICAÇÃO (ANO)
		data += "entry.7695192_month=" + URLEncoder.encode(dataMultiplicacao.getMonth()+1+"")+"&";	//PREVISÃO MULTIPLICAÇÃO (MES)
		data += "entry.7695192_day=" + URLEncoder.encode(dataMultiplicacao.getDayOfMonth()+"");		//PREVISÃO MULTIPLICAÇÃO (DIA)

		String response = mReq.sendPost(fullUrl, data);

	}
}
