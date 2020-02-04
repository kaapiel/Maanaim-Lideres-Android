package br.com.igreja.cellapp.listagens;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.widget.FacebookDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.adapter.ListaMembrosNascimentoAdapter;
import br.com.igreja.cellapp.mainMenu.Inicio;
import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Endereco;
import br.com.igreja.cellapp.model.Membro;
import br.com.igreja.cellapp.util.Mensagens;
import br.com.igreja.cellapp.util.Parametros;
import br.com.igreja.cellapp.util.WebClientCellApp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class MembrosNascimento extends Activity {

    @BindView(R.id.loading_content)
    RelativeLayout loading;
    @BindView(R.id.textViewNaoHaAniv)
    TextView textNaoHaAniv;
    @BindView(R.id.searchViewNasc)
    SearchView search;
    @BindView(R.id.listaDeMembros)
    ListView lista;

    private String finalMes;
    private Membro membro;
    private List<String> nomes = new ArrayList<String>();
    private Mensagens mensagens = new Mensagens(this);
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("membros");
    private DatabaseReference myRefCelulas = FirebaseDatabase.getInstance().getReference().child("celulas");
    private ArrayList<Membro> listaDeMembros = new ArrayList<Membro>();
    private ArrayList<Celula> listaDeCelulas = new ArrayList<Celula>();
    private ArrayList<Membro> listaDeAniversariantes = new ArrayList<Membro>();

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membros_nascimento);
        ButterKnife.bind(this);

        setUIelements();
        setConfigs();

    }

    private void setConfigs() {
        String mes = "01";
        GregorianCalendar gc = new GregorianCalendar();

        if ((gc.get(Calendar.MONTH) + 1) < 10) {
            mes = "0" + (gc.get(Calendar.MONTH) + 1);
        }

        finalMes = mes;
    }

    private void setUIelements() {
        loading.setVisibility(View.VISIBLE);
        registerForContextMenu(lista);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.voltar_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
                        mensagens = new Mensagens(MembrosNascimento.this);
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

                FacebookDialog.MessageDialogBuilder builder = new FacebookDialog.MessageDialogBuilder(MembrosNascimento.this)
                        .setLink(getString(R.string.link_app))
                        .setDescription(getString(R.string.baixe_android))
                        .setPicture(Parametros.URL_LOGO_MAANAIM);

                // If the Facebook app is installed and we can present the share dialog
                if (builder.canPresent()) {
                    // Enable button or other UI to initiate launch of the Message Dialog
                    FacebookDialog dialog = builder.build();
                    dialog.present();
                }
                return false;
            }
        });

        MenuItem email = menu.add(getString(R.string.mandar_email));
        email.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (membro.getEmail() == null) {
                    AlertDialog alert = mensagens.alertDialogMensagemOK(getString(R.string.email),
                            membro.getNome() + getString(R.string.nao_tem_email), R.drawable.mapp_ic_email);
                    alert.show();
                } else {

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setType(getString(R.string.text_plain));
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{membro.getEmail()});
                    email.putExtra(Intent.EXTRA_SUBJECT, "[Aplicativo Maanaim] - ");
                    email.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n" /*+ usuario.getNome()*/ + "\nEnviado pelo aplicativo Maanaim.");
                    try {
                        startActivity(Intent.createChooser(email, getString(R.string.enviar_email)));
                    } catch (android.content.ActivityNotFoundException ex) {
                        mensagens = new Mensagens(MembrosNascimento.this);
                        Toast toastMensagem = mensagens.toastMensagem(getString(R.string.nao_ha_app_email),
                                350, 200, 0, R.drawable.mapp_ic_email);
                        toastMensagem.show();
                    }

                    startActivity(email);
                }
                return false;
            }
        });

        menu.add(getString(R.string.cancelar));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menuPrincipal) {
            Intent intent = new Intent(MembrosNascimento.this, Inicio.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listaDeMembros = new ArrayList<>();

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

                    listaDeMembros.add(m);
                }
                listaDeAniversariantes = new WebClientCellApp().getListaDeAniversariantes(listaDeMembros, finalMes);
                ListaMembrosNascimentoAdapter adapter = new ListaMembrosNascimentoAdapter(listaDeAniversariantes, MembrosNascimento.this);
                lista.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRefCelulas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listaDeCelulas = new ArrayList<>();

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
                    listaDeCelulas.add(c);
                }

                loading.setVisibility(View.GONE);

                if (listaDeMembros.size() != 0) {
                    textNaoHaAniv.setText("");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                if (query == null || query.equals(null) || query == "" || query.equals("")) {
                    ListaMembrosNascimentoAdapter adapter = new ListaMembrosNascimentoAdapter(listaDeAniversariantes, MembrosNascimento.this);
                    lista.setAdapter(adapter);
                } else {
                    ArrayList<Membro> listaDeMembros = new WebClientCellApp().getListaDeMembrosPorNome(listaDeAniversariantes, query);
                    ListaMembrosNascimentoAdapter adapter = new ListaMembrosNascimentoAdapter(listaDeMembros, MembrosNascimento.this);
                    lista.setAdapter(adapter);
                }

                return true;
            }
        });

    }

    @OnItemClick(R.id.listaDeMembros)
    public void onItemClickListaDeMembros(AdapterView<?> adapter, View view, int posicao, long id) {

        Membro membroClicado = (Membro) adapter.getItemAtPosition(posicao);
        Intent irParaMembroSelecionado = new Intent(MembrosNascimento.this, MembroSelecionado.class);
        irParaMembroSelecionado.putExtra(getString(R.string.param_membro_clicado), membroClicado);
        startActivity(irParaMembroSelecionado);

    }

    @OnItemLongClick(R.id.listaDeMembros)
    public boolean onItemLongClickListaDeMembros(AdapterView<?> adapter, View view, int posicao, long id) {
        membro = (Membro) adapter.getItemAtPosition(posicao);
        nomes.add(membro.getNome());
        return false;

    }

} 
