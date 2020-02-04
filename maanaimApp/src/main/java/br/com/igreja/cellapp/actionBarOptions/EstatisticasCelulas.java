package br.com.igreja.cellapp.actionBarOptions;

import java.util.ArrayList;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.model.Celula;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EstatisticasCelulas extends Activity {

    @BindView(R.id.loading_content)
    RelativeLayout loading;
    @BindView(R.id.qtdVermelha)
    TextView qtdCelulasVermelha;
    @BindView(R.id.qtdAmarela)
    TextView qtdCelulasAmarela;
    @BindView(R.id.qtdAzul)
    TextView qtdCelulasAzul;
    @BindView(R.id.qtdVerde)
    TextView qtdCelulasVerde;
    @BindView(R.id.qtdLaranja)
    TextView qtdCelulasLaranja;
    @BindView(R.id.qtdBranca)
    TextView qtdCelulasBranca;
    @BindView(R.id.qtdTotal)
    TextView qtdCelulasTotal;

    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("celulas");
    private ArrayList<Celula> listaCelulas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estatisticas_celulas);
        ButterKnife.bind(this);
        loading.setVisibility(View.VISIBLE);
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
                    c.setLiderMembroId(convertStringArrayToIntArray(celula.child("liderMembroId").getValue().toString()));
                    c.setNome(celula.child("nome").getValue().toString());

                    listaCelulas.add(c);
                }
                loading.setVisibility(View.GONE);

                int[] qtdCelulas = new int[7]; //Vermelha=0; Branca=1; Amarela=2; Laranja=3; Azul=4; Verde=5; Total=6;

                for (Celula celulaAndroid : listaCelulas) {

                    if (celulaAndroid.getIdRegiao().equals(1)) {
                        qtdCelulas[4]++;
                    } else if (celulaAndroid.getIdRegiao().equals(2)) {
                        qtdCelulas[5]++;
                    } else if (celulaAndroid.getIdRegiao().equals(3)) {
                        qtdCelulas[2]++;
                    } else if (celulaAndroid.getIdRegiao().equals(4)) {
                        qtdCelulas[0]++;
                    } else if (celulaAndroid.getIdRegiao().equals(5)) {
                        qtdCelulas[1]++;
                    } else if (celulaAndroid.getIdRegiao().equals(6)) {
                        qtdCelulas[3]++;
                    }
                }

                qtdCelulas[6] = listaCelulas.size();
                qtdCelulasVermelha.setText(String.valueOf(qtdCelulas[0]));
                qtdCelulasBranca.setText(String.valueOf(qtdCelulas[1]));
                qtdCelulasAmarela.setText(String.valueOf(qtdCelulas[2]));
                qtdCelulasLaranja.setText(String.valueOf(qtdCelulas[3]));
                qtdCelulasAzul.setText(String.valueOf(qtdCelulas[4]));
                qtdCelulasVerde.setText(String.valueOf(qtdCelulas[5]));

                qtdCelulasTotal.setText(String.valueOf(qtdCelulas[6]));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int[] convertStringArrayToIntArray(String array) {

        String[] ids = array.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] liderMembroId = new int[ids.length];

        for (int i = 0; i < ids.length; i++) {
            try {
                liderMembroId[i] = Integer.parseInt(ids[i]);
            } catch (NumberFormatException nfe) {
                //NOTE: write something here if you need to recover from formatting errors
            }
        }

        return liderMembroId;
    }
}
