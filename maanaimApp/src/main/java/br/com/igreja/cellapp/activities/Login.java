package br.com.igreja.cellapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.mainMenu.Inicio;
import br.com.igreja.cellapp.util.Mensagens;
import br.com.igreja.cellapp.util.Parametros;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends Activity {

    @BindView(R.id.imgFundoLogin)
    ImageView imgView;
    @BindView(R.id.editTextUsuario)
    EditText editUsuario;
    @BindView(R.id.editTextSenha)
    EditText editSenha;
    @BindView(R.id.checkBoxLembrarDados)
    CheckBox chkBoxDados;
    @BindView(R.id.siteLogin)
    TextView site;
    @BindView(R.id.loading_content)
    RelativeLayout loading;

    private static final String TAG = "MaanaimApp_TAG";
    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private int[] fotos = new int[]{R.drawable.splash2, R.drawable.splash3, R.drawable.splash4,
            R.drawable.splash5, R.drawable.splash6, R.drawable.splash7, R.drawable.splash8, R.drawable.splash9,
            R.drawable.splash10, R.drawable.splash11};
    private String passPreference, userPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        OAuthListenerFirebase();
        startUIelements();
    }

    private void startUIelements() {
        imgView.setImageResource(fotos[new Random().nextInt(fotos.length)]);
        imgView.setScaleType(ScaleType.FIT_XY);

        editSenha.setBackgroundColor(getResources().getColor(R.color.branco));
        editUsuario.setBackgroundColor(getResources().getColor(R.color.branco));
        editUsuario.setHint(getString(R.string.usuario));
        editSenha.setHint(getString(R.string.put_extra_senha));

        site.setText(Html.fromHtml("<a href=" + Parametros.URL_SITE + "> " + getString(R.string.acessar_site) + " "));
        site.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        userPreference = preferences.getString(getString(R.string.put_extra_usuario), "");
        passPreference = preferences.getString(getString(R.string.put_extra_senha), "");

        editUsuario.setText(userPreference);
        editSenha.setText(passPreference);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

        SharedPreferences settings = getSharedPreferences("INFORMACOES_DE_LOGIN", 0);
        settings.edit()
                .putString("NOME", editUsuario.getText().toString())
                .putString("SENHA", editSenha.getText().toString())
                .apply();
    }

    private void loginAnonimo() {

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInAnonymously", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick(R.id.button_login_anonymous)
    public void onAnonymousButtonClick() {
        ConnectivityManager cm = (ConnectivityManager) Login.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (!cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected() &&
                !cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {

            new Mensagens(Login.this).toastMensagem(getResources().getString(R.string.conexao_internet), 0, 0, 1, R.drawable.mapp_ic_nao_joia).show();
            Login.this.loading.setVisibility(View.GONE);
        } else {
            loading.setVisibility(View.VISIBLE);
            loginAnonimo();
            loading.setVisibility(View.GONE);
            Login.this.loading.setVisibility(View.GONE);
            startActivity(new Intent(Login.this, Inicio.class));
        }
    }

    @OnClick(R.id.BotaoLogin)
    public void onLoginButton() {
        loading.setVisibility(View.VISIBLE);

        ConnectivityManager cm = (ConnectivityManager) Login.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected() &&
                !cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {


        } else {

            if (chkBoxDados.isChecked()) {

                preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(getString(R.string.put_extra_usuario), editUsuario.getText().toString().trim());
                editor.putString(getString(R.string.put_extra_senha), editSenha.getText().toString());
                editor.apply();

                //VALIDAR USUARIO
                Intent intent = new Intent(Login.this, Inicio.class);
                startActivity(intent);
            }
        }

        loading.setVisibility(View.GONE);
    }

    private void OAuthListenerFirebase() {

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

}