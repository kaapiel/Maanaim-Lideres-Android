package br.com.igreja.cellapp.maps;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Endereco;
import br.com.igreja.cellapp.model.Membro;
import br.com.igreja.cellapp.util.Mensagens;
import br.com.igreja.cellapp.util.WebClientCellApp;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

	@BindView(R.id.loading_content)
	RelativeLayout loading;

	@BindView(R.id.textLoading)
	TextView carregando;

	private ArrayList<Membro> listaDeLideres;
	private ProgressDialog progresso;
	private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("membros");
	private DatabaseReference myRefCelulas = FirebaseDatabase.getInstance().getReference().child("celulas");
	private ArrayList<Membro> listaDeMembros = new ArrayList<>();
	private ArrayList<Celula> listaDeCelulas = new ArrayList<>();
	private GoogleMap mMap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		ButterKnife.bind(this);

		loading.setVisibility(View.VISIBLE);


//		progresso = new ProgressDialog(this);
//		progresso.setTitle("Colocando células no Mapa");
//		progresso.setMessage("Apontando células...");
//		progresso.show();
//		progresso.setCancelable(false);

	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}


	@Override
	public void onStart() {
		super.onStart();

		myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				listaDeMembros = new ArrayList<>();

				for (DataSnapshot membro: dataSnapshot.getChildren()) {

					Membro m = new Membro();
					m.setApelido(membro.child("apelido").getValue().toString());
					m.setCpf(membro.child("cpf").getValue().toString());
					m.setDataBatismo(membro.child("dataBatismo").getValue().toString());
					m.setDataCadastro(membro.child("dataCadastro").getValue().toString());
					m.setDataNasc(membro.child("dataNasc").getValue().toString());
					m.setEmail(membro.child("email").getValue().toString());

					Endereco e = new Endereco();
					e.setBairro(membro.child("endereco").child("bairro").getValue().toString());
					e.setCep(membro.child("endereco").child("cep").getValue().toString());
					e.setCidade(membro.child("endereco").child("cidade").getValue().toString());
					e.setComplemento(membro.child("endereco").child("complemento").getValue().toString());
					e.setNumero(membro.child("endereco").child("numero").getValue().toString());
					e.setRua(membro.child("endereco").child("rua").getValue().toString());
					e.setUf(membro.child("endereco").child("uf").getValue().toString());

					m.setEndereco(e);
					m.setFoneCel(membro.child("foneCel").getValue().toString());
					m.setFoto(membro.child("foto").getValue().toString());
					m.setIdCelula(Integer.valueOf(membro.child("idCelula").getValue().toString()));
					m.setIdMembro(Integer.valueOf(membro.child("idMembro").getValue().toString()));
					m.setNome(membro.child("nome").getValue().toString());
					m.setRg(membro.child("rg").getValue().toString());
					m.setSexo(membro.child("sexo").getValue().toString());

					listaDeMembros.add(m);
				}

				Thread td = new Thread(){

					private Celula cacm;

					@Override
					public void run() {
						try{
							listaDeLideres = new WebClientCellApp().getListaDeLideres(listaDeCelulas, listaDeMembros);

							for (Membro lider : listaDeLideres) {
								cacm = new WebClientCellApp().getCelulaAssociadaComMembro(listaDeCelulas, lider);

									Mapa.this.runOnUiThread(new Runnable() {
										@Override
										public void run() {
//											progresso.setMessage(getString(R.string.celula)+": "+cacm.getNome());
											carregando.setText(getString(R.string.celula)+": "+cacm.getNome());
										}
									});

									LatLng localDoMembro = new Localizador(Mapa.this).getCoordenadas(lider.getEndereco().getRua()+" "+
											lider.getEndereco().getCidade());

									final MarkerOptions options = new MarkerOptions().title(getString(R.string.celula)+": "+cacm.getNome())
											.position(localDoMembro).icon(BitmapDescriptorFactory.defaultMarker
													(cacm.getIdRegiao() == 1 ? BitmapDescriptorFactory.HUE_BLUE :
													 cacm.getIdRegiao() == 2 ? BitmapDescriptorFactory.HUE_GREEN :
													 cacm.getIdRegiao() == 3 ? BitmapDescriptorFactory.HUE_RED :
													 cacm.getIdRegiao() == 4 ? BitmapDescriptorFactory.HUE_ORANGE :
													 cacm.getIdRegiao() == 5 ? BitmapDescriptorFactory.HUE_YELLOW :
													 BitmapDescriptorFactory.HUE_CYAN));

									Mapa.this.runOnUiThread(new Runnable() {
										@Override
										public void run() {
											mMap.addMarker(options);
										}
									});
							}

						}catch (final Exception e){

							Mapa.this.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Mensagens m = new Mensagens(Mapa.this);
									m.toastMensagem("Erro ao colocar a celula "+cacm.getNome()+" no mapa ! Volte e " +
											"tente novamente.", 0, 550, 0, R.drawable.ponteiro).show();

									Log.e("sadsdasdasdas", e.getMessage());
										}
							});
						}

						Mapa.this.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								loading.setVisibility(View.GONE);
							}
						});
					}
				};
				td.start();

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

		myRefCelulas.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				listaDeCelulas = new ArrayList<>();

				for (DataSnapshot celula: dataSnapshot.getChildren()) {

					Celula c = new Celula();
					c.setAtivo(Boolean.valueOf(celula.child("ativa").getValue().toString()));
					c.setDataCriacao(celula.child("dataCriacao").getValue().toString());
					c.setIdCelulaOrigem(Integer.valueOf(celula.child("idCelulaOrigem").getValue().toString()));
					c.setIdRegiao(Integer.valueOf(celula.child("idRegiao").getValue().toString()));
					c.setIdSupervisao(Integer.valueOf(celula.child("idSupervisao").getValue().toString()));
					c.setLiderMembroId(new WebClientCellApp().convertStringArrayToIntArray(celula.child("liderMembroId").getValue().toString()));
					c.setNome(celula.child("nome").getValue().toString());
					c.setIdCelula(Integer.valueOf(celula.child("idCelula").getValue().toString()));
					c.setDescricao(celula.child("descricao").getValue().toString());
					listaDeCelulas.add(c);
				}

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});


	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
	}
}
