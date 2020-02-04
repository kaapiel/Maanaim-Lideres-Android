package br.com.igreja.cellapp.listagens;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Membro;
import br.com.igreja.cellapp.util.Mensagens;
import br.com.igreja.cellapp.util.WebClientCellApp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditarMembro extends Activity {

    @BindView(R.id.loading_content)
    RelativeLayout loading;

    @BindView(R.id.editarMembro_spinnerCelula)
    Spinner spinnerCelula;
    @BindView(R.id.editarMembro_spinnerSexo)
    Spinner spinnerSexo;

    @BindView(R.id.editarMembro_editNome)
    EditText editNome;
    @BindView(R.id.editarMembro_editApelido)
    EditText editApelido;
    @BindView(R.id.editarMembro_editEmail)
    EditText editEmail;
    @BindView(R.id.editarMembro_editCPF)
    EditText editCPF;
    @BindView(R.id.editarMembro_editRG)
    EditText editrg;
    @BindView(R.id.editarMembro_editNumeroCelular)
    EditText editNumeroCelular;
    @BindView(R.id.editarMembro_editDataBatismo)
    EditText editDataBatismo;
    @BindView(R.id.editarMembro_editCEP)
    EditText editCEP;
    @BindView(R.id.editarMembro_editRua)
    EditText editRua;
    @BindView(R.id.editarMembro_editNumeroEndereco)
    EditText editNumeroEndereco;
    @BindView(R.id.editarMembro_editComplemento)
    EditText editComplemento;
    @BindView(R.id.editarMembro_editBairro)
    EditText editBairro;
    @BindView(R.id.editarMembro_editCidade)
    EditText editCidade;
    @BindView(R.id.editarMembro_editEstado)
    EditText editEstado;

    @BindView(R.id.editarMembro_botaoBuscarCEP)
    Button botaoBuscarCEP;
    @BindView(R.id.editarMembro_botaoCadastrar)
    Button botaoCadastrar;

    @BindView(R.id.editarMembro_dataNasc)
    DatePicker datePickerNascimento;

    private Membro membroParaEditar;
    private ArrayList<Celula> listaCelulas = new ArrayList<>();
    private ArrayAdapter<Celula> adapter;
    private String cep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_membro);
        ButterKnife.bind(this);

        getBundles();
        setUIelements();
    }

    private void getBundles() {
        try {
            membroParaEditar = (Membro) getIntent().getExtras().getSerializable(getString(R.string.param_membro_clicado));
            botaoCadastrar.setText("Alterar");
        } catch (NullPointerException npe) {
            //não faz nada
        }
    }

    private void setUIelements() {
        loading.setVisibility(View.VISIBLE);

        ArrayList<String> sexos = new ArrayList<>();
        sexos.add("Masculino");
        sexos.add("Feminino");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditarMembro.this,
                android.R.layout.simple_spinner_item, sexos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapter);

        spinnerSexo.setBackgroundColor(getResources().getColor(R.color.holo_orange_dark));
        spinnerCelula.setBackgroundColor(getResources().getColor(R.color.holo_orange_dark));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseDatabase.getInstance().getReference()
                .child("celulas")                                           //TABLE
                .orderByChild("nome")                                       //ROW
                .addListenerForSingleValueEvent(new ValueEventListener() {
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

                        adapter = new ArrayAdapter<Celula>(EditarMembro.this,
                                android.R.layout.simple_spinner_item, listaCelulas);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerCelula.setAdapter(adapter);

                        loading.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (membroParaEditar != null) {

            FirebaseDatabase.getInstance().getReference()
                    .child("membros")                                           //TABLE
                    .orderByChild("idMembro")                                   //ROW
                    .equalTo(membroParaEditar.getIdMembro())                    //WHERE CLAUSE
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                                Membro m = messageSnapshot.getValue(Membro.class);

                                editNome.setText(m.getNome());
                                editApelido.setText(m.getApelido());
                                editCPF.setText(m.getCpf());
                                editEmail.setText(m.getEmail());
                                editNumeroCelular.setText(m.getFoneCel());
                                editrg.setText(m.getRg());
                                editDataBatismo.setText(m.getDataBatismo());

                                editCEP.setText(m.getEndereco().getCep());
                                editRua.setText(m.getEndereco().getRua());
                                editNumeroEndereco.setText(m.getEndereco().getNumero());
                                editComplemento.setText(m.getEndereco().getComplemento());
                                editBairro.setText(m.getEndereco().getBairro());
                                editCidade.setText(m.getEndereco().getCidade());
                                editEstado.setText(m.getEndereco().getUf());

                                if (m.getIdCelula().equals(0)) {
                                    spinnerCelula.setSelection(0);
                                } else {
                                    spinnerCelula.setSelection(
                                            adapter.getPosition(new WebClientCellApp().getCelulaPorId(listaCelulas, m.getIdCelula())));
                                }

                                if (m.getSexo().equalsIgnoreCase("masculino")) {
                                    spinnerSexo.setSelection(0);
                                } else {
                                    spinnerSexo.setSelection(1);
                                }

                                datePickerNascimento.updateDate(
                                        Integer.valueOf(m.getDataNasc().split("/")[2]),     //ANO
                                        Integer.valueOf(m.getDataNasc().split("/")[1]) - 1, //MES
                                        Integer.valueOf(m.getDataNasc().split("/")[0]));    //DIA


                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditarMembro.this, Membros.class));
    }

    @OnClick(R.id.editarMembro_botaoBuscarCEP)
    public void onClickNoBotaoBuscarCEP() {

        loading.setVisibility(View.VISIBLE);

        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open("cep_sp.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader reader = new BufferedReader(inputStreamReader);
        String linha;

        try {

            while ((linha = reader.readLine()) != null) {
                if (linha.contains(editCEP.getText().toString())) {
                    cep = linha;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cep != null) {
            try {
                String[] split = cep.split(";");
                editEstado.setText("SP");
                editRua.setText(split[4] + " " + split[1]);
                editCidade.setText(split[0]);
                editBairro.setText(split[2]);
            } catch (Exception e) {
                e.printStackTrace();
                new Mensagens(this).toastMensagem(getString(R.string.erro_parse_cep),
                        0, 550, 1, R.drawable.mapp_ic_emotion_espanto).show();

            }
        } else {
            new Mensagens(this).toastMensagem(getString(R.string.cep_nao_encontrado),
                    0, 400, 0, R.drawable.mapp_ic_planeta).show();
        }

        loading.setVisibility(View.GONE);

    }

    @OnClick(R.id.editarMembro_botaoCadastrar)
    public void onClickNoBotaoCadastrar() {

        if (membroParaEditar != null) {

            loading.setVisibility(View.VISIBLE);
            FirebaseDatabase.getInstance().getReference()
                    .child("membros")                                           //TABLE
                    .orderByChild("idMembro")                                   //ROW
                    .equalTo(membroParaEditar.getIdMembro())                    //WHERE CLAUSE
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

                                String key = messageSnapshot.getKey();
                                dataSnapshot.getRef().child(key).child("nome").setValue(editNome.getText().toString());
                                dataSnapshot.getRef().child(key).child("apelido").setValue(editApelido.getText().toString());
                                dataSnapshot.getRef().child(key).child("cpf").setValue(editCPF.getText().toString());
                                dataSnapshot.getRef().child(key).child("email").setValue(editEmail.getText().toString());
                                dataSnapshot.getRef().child(key).child("foneCel").setValue(editNumeroCelular.getText().toString());
                                dataSnapshot.getRef().child(key).child("rg").setValue(editrg.getText().toString());

                                dataSnapshot.getRef().child(key).child("endereco").child("cep").setValue(editCEP.getText().toString());
                                dataSnapshot.getRef().child(key).child("endereco").child("rua").setValue(editRua.getText().toString());
                                dataSnapshot.getRef().child(key).child("endereco").child("numero").setValue(editNumeroEndereco.getText().toString());
                                dataSnapshot.getRef().child(key).child("endereco").child("complemento").setValue(editComplemento.getText().toString());
                                dataSnapshot.getRef().child(key).child("endereco").child("bairro").setValue(editBairro.getText().toString());
                                dataSnapshot.getRef().child(key).child("endereco").child("cidade").setValue(editCidade.getText().toString());
                                dataSnapshot.getRef().child(key).child("endereco").child("estado").setValue(editEstado.getText().toString());

                                dataSnapshot.getRef().child(key).child("idCelula").setValue(adapter.getItem(spinnerCelula.getSelectedItemPosition()).getIdCelula().toString());
                                dataSnapshot.getRef().child(key).child("sexo").setValue(spinnerSexo.getSelectedItem().toString());
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

            loading.setVisibility(View.GONE);

        } else {

            //CADASTRAR
        }

    }

}