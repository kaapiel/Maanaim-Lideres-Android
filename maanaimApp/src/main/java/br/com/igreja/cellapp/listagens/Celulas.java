package br.com.igreja.cellapp.listagens;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.actionBarOptions.EstatisticasCelulas;
import br.com.igreja.cellapp.adapter.ListaCelulasAdapter;
import br.com.igreja.cellapp.mainMenu.Inicio;
import br.com.igreja.cellapp.model.Celula;
import br.com.igreja.cellapp.model.Regiao;
import br.com.igreja.cellapp.util.WebClientCellApp;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class Celulas extends AppCompatActivity {

    @BindView(R.id.loading_content)
    RelativeLayout loading;

    @BindView(R.id.listaDeCelulas)
    ListView lista;

    @BindView(R.id.searchViewCelulas)
    SearchView search;

    private ArrayList<Celula> listaCelulas = new ArrayList<>();
    private ArrayList<Regiao> listaRegioes = new ArrayList<>();
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("celulas");
    private DatabaseReference myRefRegioes = FirebaseDatabase.getInstance().getReference().child("regioes");
    private Celula celula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.celulas);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Em desenvolvimento...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUIelements();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                if (query == null || query.equals(null) || query == "" || query.equals("")) {
                    ListaCelulasAdapter adapter = new ListaCelulasAdapter(listaCelulas, Celulas.this, listaRegioes);
                    lista.setAdapter(adapter);
                } else {
                    ArrayList<Celula> listaDeCelulas = new WebClientCellApp().getListaDeCelulasPorNome(listaCelulas, query);
                    ListaCelulasAdapter adapter = new ListaCelulasAdapter(listaDeCelulas, Celulas.this, listaRegioes);
                    lista.setAdapter(adapter);
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.voltar_menu_celula, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setUIelements() {
        loading.setVisibility(View.VISIBLE);
//        ActionBar actionBar = getActionBar();
//        actionBar.setSubtitle(getString(R.string.selecione_celula));
//        actionBar.setLogo(R.drawable.ic_action_name);
        registerForContextMenu(lista);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menuPrincipal) {
            Intent intent = new Intent(Celulas.this, Inicio.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (item.getItemId() == R.id.estatisticas) {
            Intent intent = new Intent(Celulas.this, EstatisticasCelulas.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem excluir = menu.add(getString(R.string.excluir_celula));
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //verificar se é supervisor. Se não for, disparar mensagem.
                //depois, verificar se existem membros na célula: se nao existem, perguntar se deseja excluir, e deletar.

                AlertDialog.Builder alert = new AlertDialog.Builder(Celulas.this);
                alert.setTitle(getString(R.string.confirmacao));
                alert.setIcon(R.drawable.mapp_ic_atencao);
                alert.setMessage(getString(R.string.certeza_excluir_celula));
                alert.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //if(!tem membros na celula){

                        // excluir célula direto
                        // Intent i = new Intent(Celulas.this, Inicio.class);
                        // i.putExtra(getString(R.string.put_extra_usuario), usuario);
                        // startActivity(i);
                        // finish();

                        //} else {

                        // abrir custom_dialog_transf.
                        // apos abrir custom_dialog_transf selecionar os membros e a célula destino.
                        // se houver mais de uma célula destino, selecione os primeiros e clique no botão "selecionar"
                        // apos todas as seleções, apresentar um novo dialog, mostrando as células e os respectivos membros.
                        // ao clicar em cofnrimar é feita a requisição POST ao servidor, alterando a base de dados.
                        // alterar dados da base de dados local do android (criar métodos);
                        // abrir tela inicial, dando um finish() passando bundle usuario.

                        //}

                    }
                });
                alert.setNegativeButton(getString(R.string.nao), null);
                AlertDialog alert2 = alert.create();
                alert2.show();

                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        myRef.orderByChild("idRegiao").addValueEventListener(new ValueEventListener() {
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

                ListaCelulasAdapter adapter = new ListaCelulasAdapter(listaCelulas, Celulas.this, listaRegioes);
                lista.setAdapter(adapter);
                loading.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRefRegioes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listaRegioes = new ArrayList<>();

                for (DataSnapshot regiao : dataSnapshot.getChildren()) {

                    Regiao r = new Regiao();
                    r.setCor(regiao.child("cor").getValue().toString());
                    r.setDescricao(regiao.child("descricao").getValue().toString());
                    r.setIdMembroPastor(new WebClientCellApp().convertStringArrayToIntArray(regiao.child("idMembroPastor").getValue().toString()));
                    r.setIdRegiao(Integer.valueOf(regiao.child("idRegiao").getValue().toString()));

                    listaRegioes.add(r);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @OnItemClick(R.id.listaDeCelulas)
    public void onItemClickDaLista(AdapterView<?> adapter, View view, int posicao, long id) {
        Celula celulaSelecionada = (Celula) adapter.getItemAtPosition(posicao);
        Intent irParaCelulaSelecionada = new Intent(Celulas.this, CelulaSelecionada.class);
        Bundle extras = new Bundle();
        extras.putSerializable(getString(R.string.put_extra_celula_relacionada), celulaSelecionada);
        irParaCelulaSelecionada.putExtras(extras);
        startActivity(irParaCelulaSelecionada);
    }

    @OnItemLongClick(R.id.listaDeCelulas)
    public boolean onItemLongClickDaLista(AdapterView<?> adapter, View view, int posicao, long id) {
        celula = (Celula) adapter.getItemAtPosition(posicao);
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
