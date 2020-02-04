package br.com.igreja.cellapp.listagens;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.mainMenu.Inicio;
import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Membro;
import br.com.igreja.cellapp.util.Mensagens;
import br.com.igreja.cellapp.util.WebClientCellApp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MembroSelecionado extends Activity {

    @BindView(R.id.loading_content)
    RelativeLayout loading;
    @BindView(R.id.textLoading)
    TextView carregando;
    @BindView(R.id.fotoDeFundo)
    ImageView imageView;
    @BindView(R.id.textCelularMembro)
    TextView textCelularMembro;
    @BindView(R.id.textTelefoneMembro)
    TextView textTelefoneMembro;
    @BindView(R.id.imageButtonExcluirMembro)
    ImageView imageDelete;
    @BindView(R.id.imageButtonEditarMembro)
    ImageView imageEdit;
    @BindView(R.id.imageButtonMoverMembro)
    ImageView imageMover;
    @BindView(R.id.imageViewFotoMembro)
    ImageView imageViewFotoMembro;
    @BindView(R.id.textDataDeNascimentoDoMembro)
    TextView editTextDataDeNascimentoDoMembro;
    @BindView(R.id.textViewNomeMembro)
    TextView textViewNomeMembro;

    private ProgressDialog progresso;
    private Membro membroSelecionado;
    private String urlPostDeleteMembros;
    private ArrayList<Celula> listaCelulas = new ArrayList<>();
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("celulas");
    private Celula celulaAssociada = new Celula();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membro_selecionado);
        ButterKnife.bind(this);

        getBundles();
        setUIelements();
    }

    private void getBundles() {
        membroSelecionado = (Membro) getIntent().getExtras().getSerializable(getString(R.string.param_membro_clicado));
    }

    private void setUIelements() {

        loading.setVisibility(View.VISIBLE);

        int[] fotos = {R.drawable.fundo1, R.drawable.fundo2, R.drawable.fundo3, R.drawable.fundo4,
                R.drawable.fundo5, R.drawable.fundo6, R.drawable.fundo7, R.drawable.fundo8, R.drawable.fundo9,
                R.drawable.fundo10, R.drawable.fundo11};
        Random rdm = new Random();
        imageView.setImageResource(fotos[rdm.nextInt(fotos.length)]);

        editTextDataDeNascimentoDoMembro.setEnabled(false);
        editTextDataDeNascimentoDoMembro.setText(membroSelecionado.getDataNasc());

        textTelefoneMembro.setText(membroSelecionado.getFoneCel());
        textCelularMembro.setText(membroSelecionado.getFoneCel());

        textViewNomeMembro.setText(membroSelecionado.getNome());
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

                loading.setVisibility(View.GONE);
                celulaAssociada = new WebClientCellApp().getCelulaAssociadaComMembro(listaCelulas, membroSelecionado);
                TextView textCelula = (TextView) findViewById(R.id.textViewCelula);

                try {
                    textCelula.setText(getString(R.string.celula) + ": " + celulaAssociada.getNome());
                } catch (NullPointerException npe) {
                    textCelula.setText(getString(R.string.celula) + ": " + "SEM CÉLULA");
                }

                String foto = membroSelecionado.getFoto();

                if (!foto.equals("")) {

                    byte[] decode = Base64.decode(foto, 0);
                    Bitmap bm = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bm, 1000, 1000, true);
                    imageViewFotoMembro.setImageBitmap(scaledBitmap);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @OnClick(R.id.imageViewFotoMembro)
    public void onClickNaImagem() {
        Intent intent = new Intent(MembroSelecionado.this, FotoMembro.class);
        intent.putExtra(getString(R.string.param_foto_membro), membroSelecionado.getFoto());
        startActivity(intent);
        overridePendingTransition(R.animator.foto_evento_anim_1, R.animator.foto_evento_anim_2);
    }

    @OnClick(R.id.imageButtonExcluirMembro)
    public void onClickNoBotaoExcluir() {

        new Mensagens(this).alertDialogMensagemOK("Sessão em desenvolvimento", "Esta sessão está em desenvolvimento.", R.drawable.mapp_ic_alfinete).show();

//            AlertDialog.Builder alert = new AlertDialog.Builder(MembroSelecionado.this);
//            alert.setMessage(getString(R.string.deletar_membro_pergunta));
//
//            alert.setTitle(getString(R.string.verifique_dados));
//            alert.setIcon(R.drawable.ic_splash_preto);
//            alert.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    final Mensagens mensagens = new Mensagens(MembroSelecionado.this);
//
//                    loading.setVisibility(View.VISIBLE);
//                    carregando.setText("Deletando membro " + membroSelecionado.getNome());
//
//                    try {
//                        //VALIDAR SE A PESSOA PODE DELETAR O MEMEBRO (SUPERVISOR, LIDER DE CÉLULA, ETC)
//
//                        myRef = FirebaseDatabase.getInstance().getReference().child("membros");
//                        myRef.orderByChild("idMembro").equalTo(membroSelecionado.getIdMembro()).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                                String index = null;
//                                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
//                                    Membro m = messageSnapshot.getValue(Membro.class);
//                                    index = messageSnapshot.getKey(); //INDEX
//                                }
//
//                                FirebaseDatabase.getInstance().getReference().child("membros").child(index).removeValue();
//                                startActivity(new Intent(MembroSelecionado.this, Membros.class));
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        mensagens.toastMensagem(getString(R.string.delete_membro_success),
//                                0, 550, 1, R.drawable.mapp_ic_ok).show();
//
//                        //REMOVER MEMBRO
//                        loading.setVisibility(View.GONE);
//                        Intent intent = new Intent(MembroSelecionado.this, Inicio.class);
//                        startActivity(intent);
//                        finish();
//
//                    } catch (Exception e) {
//                        mensagens.toastMensagem(getString(R.string.erro_delete_membro), 0, 550, 0, R.drawable.mapp_ic_emotion_e_agora).show();
//
//                        loading.setVisibility(View.GONE);
//                    }
//                }
//            });
//            alert.setNegativeButton(getString(R.string.nao), null);
//            AlertDialog alert2 = alert.create();
//            alert2.show();

    }

    @OnClick(R.id.imageButtonMoverMembro)
    public void onClickOnMoveMember() {

        new Mensagens(this).alertDialogMensagemOK("Sessão em desenvolvimento", "Esta sessão está em desenvolvimento.", R.drawable.mapp_ic_alfinete).show();

//        AlertDialog.Builder alert = new AlertDialog.Builder(MembroSelecionado.this);
//        alert.setMessage(getString(R.string.deletar_membro_pergunta));
//
//        alert.setTitle(getString(R.string.verifique_dados));
//        alert.setIcon(R.drawable.ic_splash_preto);
//        alert.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                loading.setVisibility(View.VISIBLE);
//                //VALIDAR SE O USUARIO TEM PERMISSAO PARA MOVER MEMBRO DE CÉLULA
//                if (22 == 200) {
//
//                    new Mensagens(MembroSelecionado.this).toastMensagem(getString(R.string.delete_membro_success),
//                            0, 550, 1, R.drawable.mapp_ic_ok).show();
//
//                    loading.setVisibility(View.GONE);
//                } else {
//
//                    new Mensagens(MembroSelecionado.this).toastMensagem(getString(R.string.erro_delete_membro),
//                            0, 550, 0, R.drawable.mapp_ic_emotion_e_agora).show();
//                    loading.setVisibility(View.GONE);
//                }
//            }
//        });
//        alert.setNegativeButton(getString(R.string.nao), null);
//        AlertDialog alert2 = alert.create();
//        alert2.show();
    }

    @OnClick(R.id.imageButtonEditarMembro)
    public void onClickOnEditButton() {

        new Mensagens(this).alertDialogMensagemOK("Sessão em desenvolvimento", "Esta sessão está em desenvolvimento.", R.drawable.mapp_ic_alfinete).show();

//        myRef = FirebaseDatabase.getInstance().getReference().child("membros");
//        myRef.orderByChild("idMembro").equalTo(membroSelecionado.getIdMembro()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
//                    Membro m = messageSnapshot.getValue(Membro.class);
//
//                    Intent i = new Intent(MembroSelecionado.this, EditarMembro.class);
//                    Bundle extras = new Bundle();
//                    extras.putSerializable(getString(R.string.param_membro_clicado), m);
//                    i.putExtras(extras);
//                    startActivity(i);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

    }
}