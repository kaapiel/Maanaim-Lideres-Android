package br.com.igreja.cellapp.mainMenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.activities.Login;
import br.com.igreja.cellapp.adapter.NavigationDrawerFragment;
import br.com.igreja.cellapp.listagens.Celulas;
import br.com.igreja.cellapp.listagens.Membros;
import br.com.igreja.cellapp.maps.Mapa;
import br.com.igreja.cellapp.util.Mensagens;
import br.com.igreja.cellapp.util.Parametros;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Inicio extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    @BindView(R.id.textConexao)
    TextView textConexao;

    @BindView(R.id.webView1)
    WebView wv;

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private UiLifecycleHelper uiHelper;
    private CharSequence mTitle;

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i("STATUS DO LOGIN...", "Logando...");
        } else if (state.isClosed()) {
            Log.i("STATUS DO LOGIN...", "Deslogando...");
        }
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        ButterKnife.bind(this);

        setUpConfigurations(savedInstanceState);
        setUIelements();
    }

    private void setUpConfigurations(Bundle savedInstanceState) {
        uiHelper = new UiLifecycleHelper(Inicio.this, callback);
        uiHelper.onCreate(savedInstanceState);
    }

    private void setUIelements() {
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
            wv.loadUrl(Parametros.URL_SITE);
        } else if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
            wv.loadUrl(Parametros.URL_SITE);
        } else {
            textConexao.setText(getString(R.string.erro_sem_conexao_site));
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
    }

    public void onSectionAttached(int number) {
        Mensagens m = new Mensagens(Inicio.this);
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                Intent irParaAgendaDeReuniao = new Intent(Inicio.this, Relatorios.class);
                startActivity(irParaAgendaDeReuniao);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                Intent irParacelulas = new Intent(Inicio.this, Celulas.class);
                startActivity(irParacelulas);
                break;
            case 4:

                mTitle = getString(R.string.title_section4);
                Intent irParaMembros = new Intent(Inicio.this, Membros.class);
                startActivity(irParaMembros);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                AlertDialog alertMaps = m.alertDialogMensagemSIMeNAO(getString(R.string.celula_maps),
                        getString(R.string.visualizar_maps),
                        Mapa.class);
                alertMaps.show();
                break;
            case 6:
                Intent intent = new Intent(Inicio.this, Sugestoes.class);
                startActivity(intent);
                break;
            case 7:
                Intent iv = new Intent(Inicio.this, Sobre.class);
                startActivity(iv);
                break;
            case 8:
                AlertDialog.Builder alert = new AlertDialog.Builder(Inicio.this);
                alert.setTitle(getString(R.string.sair_sistema));
                alert.setIcon(R.drawable.ic_splash_preto);
                alert.setMessage(getString(R.string.certeza_sair_membro));
                alert.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Inicio.this, Login.class);
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton(getString(R.string.nao), null);
                AlertDialog alert2 = alert.create();
                alert2.show();
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {

            getMenuInflater().inflate(R.menu.inicio, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_inicio,
                    container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Inicio) activity).onSectionAttached(getArguments().getInt(
                    ARG_SECTION_NUMBER));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed())) {
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
            @Override
            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
                Log.e("Activity", String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
                Log.i("Activity", "Success!");
            }
        });
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

}
