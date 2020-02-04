package br.com.igreja.cellapp.actionBarOptions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog.PendingCall;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.mainMenu.Inicio;
import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Endereco;
import br.com.igreja.cellapp.model.Membro;
import br.com.igreja.cellapp.util.Parametros;
import br.com.igreja.cellapp.util.WebClientCellApp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GeradorAtividades extends Activity {

    @BindView(R.id.loading_content)
    RelativeLayout loading;
    @BindView(R.id.txtCelula)
    TextView txtCelula;
    @BindView(R.id.editPalavra)
    EditText palavra;
    @BindView(R.id.editLouvor)
    EditText louvor;
    @BindView(R.id.editDinamica)
    EditText dinamica;
    @BindView(R.id.editLanche)
    EditText lanche;
    @BindView(R.id.botaoGerar)
    Button botaoGerar;
    @BindView(R.id.botaoCompartilhar)
    Button botaoCompartilhar;

    private Celula celulaSelecionada;
    private String textoDaMensagem = "";
    private UiLifecycleHelper uiHelper;
    private List<String> nomes = new ArrayList<String>();
    private ArrayList<Membro> listaMembros;
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("membros");
    private ArrayList<Membro> membrosDaCelula = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.voltar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {

            @Override
            public void onError(PendingCall pendingCall, Exception error, Bundle data) {
                Log.e("Activity", String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(PendingCall pendingCall, Bundle data) {
                Log.i("Activity", "Success!");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerador);
        ButterKnife.bind(this);

        getBundles();
        setConfiguration(savedInstanceState);
        setUIelements();
    }

    private void setUIelements() {
        loading.setVisibility(View.VISIBLE);
        txtCelula.setText(celulaSelecionada.getNome());
    }

    private void setConfiguration(Bundle savedInstanceState) {
        uiHelper = new UiLifecycleHelper(this, null);
        uiHelper.onCreate(savedInstanceState);
    }

    private void getBundles() {
        celulaSelecionada = (Celula) getIntent().getExtras().getSerializable(getString(R.string.put_extra_celula_relacionada));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.setHeaderTitle(getString(R.string.compartilhar));

        MenuItem face = menu.add(getString(R.string.facebook));
        face.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(GeradorAtividades.this)
                        .setLink(Parametros.URL_SITE)
                        .setName(getString(R.string.celula) + celulaSelecionada.getNome())
                        .setDescription(getString(R.string.selecao_atividades))
                        .setFriends(nomes)
                        .build();
                uiHelper.trackPendingDialogCall(shareDialog.present());

						/*
                        Intent i = new Intent(Intent.ACTION_SEND);
				        i.setType("text/plain");
				        i.putExtra(Intent.EXTRA_TEXT, textoDaMensagem);
				        i.setPackage("com.facebook.katana");
				        startActivity(i);
				        */
                return false;
            }
        });

        MenuItem whats = menu.add(getString(R.string.whatsapp));
        whats.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent whatsIntent = new Intent(Intent.ACTION_SEND);
                whatsIntent.setType(getString(R.string.text_plain));
                whatsIntent.setPackage(getString(R.string.pacote_whatsapp));
                whatsIntent.putExtra(Intent.EXTRA_TEXT, textoDaMensagem);
                startActivity(Intent.createChooser(whatsIntent, "Share with"));

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menuPrincipal) {
            Intent intent = new Intent(GeradorAtividades.this, Inicio.class);
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

                Log.e("ALTERAÇÃO !!!", "PASSOU AQUI");

                listaMembros = new ArrayList<>();

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

                    listaMembros.add(m);
                }

                loading.setVisibility(View.GONE);
                membrosDaCelula = new WebClientCellApp().getListaDeMembrosDaCelula(listaMembros, celulaSelecionada.getIdCelula());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @OnClick(R.id.botaoGerar)
    public void onClickOnGenerateButton() {
        Random r = new Random();

        for (Membro membroAndroid : membrosDaCelula) {
            nomes.add(membroAndroid.getNome());
        }

        palavra.setText(membrosDaCelula.get(r.nextInt(membrosDaCelula.size())).getNome());
        louvor.setText(membrosDaCelula.get(r.nextInt(membrosDaCelula.size())).getNome());
        dinamica.setText(membrosDaCelula.get(r.nextInt(membrosDaCelula.size())).getNome());
        lanche.setText(membrosDaCelula.get(r.nextInt(membrosDaCelula.size())).getNome());

        if (listaMembros.size() >= 5) {

            if (palavra.getText().toString().equals(louvor.getText().toString()) ||
                    palavra.getText().toString().equals(dinamica.getText().toString()) ||
                    palavra.getText().toString().equals(lanche.getText().toString()) ||
                    louvor.getText().toString().equals(dinamica.getText().toString()) ||
                    louvor.getText().toString().equals(lanche.getText().toString()) ||
                    dinamica.getText().toString().equals(lanche.getText().toString())) {

                botaoGerar.performClick();
            }
        }
        textoDaMensagem = "Seguem as tarefas da semana: \n\n" +
                "Palavra: " + palavra.getText() + "\n" +
                "Louvor: " + louvor.getText() + "\n" +
                "Dinamica: " + dinamica.getText() + "\n" +
                "Lanche: " + lanche.getText() + "\n" +
                "Que Deus os abencoe !";

    }

    @OnClick(R.id.botaoCompartilhar)
    public void onClickOnShareButton() {
        registerForContextMenu(botaoCompartilhar);
        openContextMenu(botaoCompartilhar);

        //Mensagens m = new Mensagens(GeradorAtividades.this);
        //m.toastMensagem(getString(R.string.nao_lider), 0, 550, 0, R.drawable.mapp_ic_emotion_e_agora).show();
    }

}
