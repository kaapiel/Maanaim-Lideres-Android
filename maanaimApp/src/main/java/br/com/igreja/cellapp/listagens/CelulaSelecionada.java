package br.com.igreja.cellapp.listagens;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.widget.FacebookDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.actionBarOptions.GeradorAtividades;
import br.com.igreja.cellapp.adapter.ListaMembrosAdapter;
import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Endereco;
import br.com.igreja.cellapp.model.Membro;
import br.com.igreja.cellapp.util.Mensagens;
import br.com.igreja.cellapp.util.Parametros;
import br.com.igreja.cellapp.util.WebClientCellApp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemLongClick;

public class CelulaSelecionada extends Activity {

    @BindView(R.id.loading_content)
    RelativeLayout loading;
    @BindView(R.id.textViewMembrosCelula)
    TextView tvmc;
    @BindView(R.id.textViewLiderCelula)
    TextView tvlc;
    @BindView(R.id.listViewMembrosCelula)
    ListView listaMembros;
    @BindView(R.id.listViewLideres)
    ListView listaLideres;

    private Celula celulaSelecionada;
    private Membro membro;
    private ArrayList<Membro> membrosDaCelula = new ArrayList<>();
    private ArrayList<Membro> lideresDaCelula = new ArrayList<>();
    private Mensagens mensagens = new Mensagens(this);
    private ArrayList<Celula> listaCelulas = new ArrayList<>();
    private ArrayList<Membro> listaMembro = new ArrayList<>();
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("celulas");
    private DatabaseReference myRefMembros = FirebaseDatabase.getInstance().getReference().child("membros");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.celula_selecionada);
        ButterKnife.bind(this);

        setUIelements();
        getBundles();

    }

    private void getBundles() {
        celulaSelecionada = (Celula) getIntent().getExtras().getSerializable(getString(R.string.put_extra_celula_relacionada));
    }

    private void setUIelements() {

        loading.setVisibility(View.VISIBLE);
        tvmc.setTextColor(getResources().getColor(R.color.laranja));
        tvlc.setTextColor(getResources().getColor(R.color.laranja));
        celulaSelecionada = (Celula) getIntent().getExtras().getSerializable(getString(R.string.put_extra_celula_relacionada));

        registerForContextMenu(listaMembros);
        registerForContextMenu(listaLideres);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem sms = menu.add(getString(R.string.mandar_sms));
        sms.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (membro.getFoneCel() == null) {
                    AlertDialog alert = mensagens.alertDialogMensagemOK(getString(R.string.sms),
                            membro.getNome() + getString(R.string.nao_tem_numero_sms), R.drawable.mapp_ic_email);
                    alert.show();
                } else {
                    Intent mensagem = new Intent(Intent.ACTION_VIEW);
                    mensagem.setData(Uri.parse("smsto:" + membro.getFoneCel()));
                    startActivity(mensagem);
                }
                return false;
            }
        });


        MenuItem ligacao = menu.add(getString(R.string.ligar));
        ligacao.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (membro.getFoneCel() == null) {
                    AlertDialog alert = mensagens.alertDialogMensagemOK(getString(R.string.telefone),
                            membro.getNome() + getString(R.string.nao_tem_numero_ligacao), R.drawable.mapp_ic_celular_app);
                    alert.show();
                } else {
                    Intent chamada = new Intent(Intent.ACTION_CALL);
                    chamada.setData(Uri.parse("tel:" + membro.getFoneCel()));
                    startActivity(chamada);
                }
                return false;
            }
        });

        MenuItem whats = menu.add(getString(R.string.whatsapp));
        whats.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (membro.getFoneCel() == null) {
                    AlertDialog alert = mensagens.alertDialogMensagemOK(getString(R.string.whatsapp),
                            membro.getNome() + getString(R.string.nao_tem_numero_whats), R.drawable.mapp_ic_wp);
                    alert.show();

                } else {
                    Uri uri = Uri.parse("smsto:" + membro.getFoneCel());
                    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                    i.setPackage(getString(R.string.pacote_whatsapp));

                    try {
                        startActivity(Intent.createChooser(i, ""));
                    } catch (android.content.ActivityNotFoundException ex) {
                        mensagens = new Mensagens(CelulaSelecionada.this);
                        Toast toastMensagem = mensagens.toastMensagem(getString(R.string.whatsapp_nao_instalado),
                                350, 200, 0, R.drawable.mapp_ic_wp);
                        toastMensagem.show();
                    }
                }
                return false;
            }

        });

        MenuItem face = menu.add(getString(R.string.facebook));
        face.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                FacebookDialog.MessageDialogBuilder builder = new FacebookDialog.MessageDialogBuilder(CelulaSelecionada.this)
                        .setLink(getString(R.string.link_app))
                        .setDescription(getString(R.string.baixe_android))
                        .setPicture(Parametros.URL_LOGO_MAANAIM);

                // If the Facebook app is installed and we can present the share dialog
                if (builder.canPresent()) {
                    // Enable button or other UI to initiate launch of the Message Dialog
                    FacebookDialog dialog = builder.build();
                    dialog.present();

                } else {
                    // Disable button or other UI for Message Dialog
                }
                return false;
            }
        });

        MenuItem email = menu.add(getString(R.string.mandar_email));
        email.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (membro.getEmail() == null) {
                    AlertDialog alert = mensagens.alertDialogMensagemOK(getString(R.string.email)
                            , membro.getNome() + getString(R.string.nao_tem_email), R.drawable.mapp_ic_email);
                    alert.show();
                } else {

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setType(getString(R.string.text_plain));
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{membro.getEmail()});
                    email.putExtra(Intent.EXTRA_SUBJECT, "[Aplicativo Maanaim] - ");
                    email.putExtra(Intent.EXTRA_TEXT, "\n\n\n\nEnviado pelo aplicativo Maanaim.");
                    try {
                        startActivity(Intent.createChooser(email, getString(R.string.enviar_email)));
                    } catch (android.content.ActivityNotFoundException ex) {
                        mensagens = new Mensagens(CelulaSelecionada.this);
                        Toast toastMensagem = mensagens.toastMensagem(getString(R.string.nao_ha_app_email),
                                350, 200, 0, R.drawable.mapp_ic_email);
                        toastMensagem.show();
                    }

                    startActivity(email);
                }
                return false;
            }
        });

        menu.add("Cancelar");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_celula_selecionada, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Mensagens m = new Mensagens(CelulaSelecionada.this);
        switch (item.getItemId()) {
            case R.id.gerador:

                Intent intent = new Intent(this, GeradorAtividades.class);
                Bundle extras = new Bundle();
                extras.putSerializable(getString(R.string.put_extra_celula_relacionada), celulaSelecionada);
                intent.putExtras(extras);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listaCelulas = new ArrayList<>();

                for (DataSnapshot celula : dataSnapshot.getChildren()) {

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

                    listaCelulas.add(c);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRefMembros.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listaMembro = new ArrayList<>();

                for (DataSnapshot membro : dataSnapshot.getChildren()) {

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

                    listaMembro.add(m);
                }

                loading.setVisibility(View.GONE);

                membrosDaCelula = new WebClientCellApp().getListaDeMembrosDaCelula(listaMembro, celulaSelecionada.getIdCelula());
                lideresDaCelula = new WebClientCellApp().getListaDeLideresDaCelula(membrosDaCelula, celulaSelecionada);

                ListaMembrosAdapter adapter = new ListaMembrosAdapter(membrosDaCelula, CelulaSelecionada.this, listaCelulas);
                listaMembros.setAdapter(adapter);

                ListaMembrosAdapter adapter2 = new ListaMembrosAdapter(lideresDaCelula, CelulaSelecionada.this, listaCelulas);
                listaLideres.setAdapter(adapter2);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @OnItemLongClick(R.id.listViewLideres)
    public boolean onItemLongClickLideres(AdapterView<?> adapter, View view, int posicao, long id) {
        membro = (Membro) adapter.getItemAtPosition(posicao);
        return false;
    }

    @OnItemLongClick(R.id.listViewMembrosCelula)
    public boolean onItemLongClickMembros(AdapterView<?> adapter, View view, int posicao, long id) {
        membro = (Membro) adapter.getItemAtPosition(posicao);
        return false;
    }

}