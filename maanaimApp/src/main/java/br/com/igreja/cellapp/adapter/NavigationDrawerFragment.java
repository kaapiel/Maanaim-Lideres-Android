package br.com.igreja.cellapp.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.legacy.app.ActionBarDrawerToggle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.igreja.cellapp.R;
import br.com.igreja.cellapp.util.Mensagens;

public class NavigationDrawerFragment extends Fragment {

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private NavigationDrawerCallbacks mCallbacks;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerRelativeLayout;
    private View mFragmentContainerView;
    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;
    private ProgressDialog progresso;
    private Mensagens mensagens;
    private ListView list;
    private String versionName;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }

        selectItem(mCurrentSelectedPosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDrawerRelativeLayout = (RelativeLayout) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        list = (ListView) mDrawerRelativeLayout.findViewById(R.id.ListTeste);

        ImageView fotoMembro = (ImageView) mDrawerRelativeLayout.findViewById(R.id.fotoDoMembroLogado);
        TextView nomeMembro = (TextView) mDrawerRelativeLayout.findViewById(R.id.textViewNomeFragment);
        TextView textVersao = (TextView) mDrawerRelativeLayout.findViewById(R.id.textViewVers);

        try {
            textVersao.setText(getString(R.string.versao) + ": v" +
                    getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName);
        } catch (Exception e) {
            textVersao.setText(getString(R.string.versao) + ": v.NULL");
        }

//		if (usuarioLogado != null){
//			MembroDAO daoM = new MembroDAO(getActivity());
//			Membro membro = daoM.getMembroPorId(usuarioLogado.getIdmembro());
//
//			String fotoByte = "";
//
//			if(membro != null){
//				nomeMembro.setText(usuarioLogado.getNome());
//				nomeMembro.setTypeface(null, Typeface.BOLD);
//				fotoByte = membro.getFoto();
//
//			} else {
//				nomeMembro.setText(usuarioLogado.getNome());
//				nomeMembro.setTypeface(null, Typeface.BOLD);
//			}
//
//
//			if (fotoByte == null || usuarioLogado.getIdmembro() == 0){
//
//				AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
//				fadeIn.setDuration(700);
//				fadeIn.setFillAfter(true);
//
//				fotoMembro.startAnimation(fadeIn);
//				fotoMembro.setImageResource(R.drawable.ic_semfoto);
//
//			} else {
//
//				byte[] decode = Base64.decode(fotoByte, Base64.DEFAULT);
//				Bitmap bm = BitmapFactory.decodeByteArray(decode, 0, decode.length);
//				Options op = new BitmapFactory.Options();
//				op.inScaled = false;
//				Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bm, 500, 500, true);
//
//				AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
//				fadeIn.setDuration(700);
//				fadeIn.setFillAfter(true);
//
//				fotoMembro.startAnimation(fadeIn);
//				fotoMembro.setImageBitmap(createScaledBitmap);
//
//			}
//		} else {

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(700);
        fadeIn.setFillAfter(true);

        nomeMembro.setText(getString(R.string.membro_visitante));
        fotoMembro.startAnimation(fadeIn);
        fotoMembro.setImageResource(R.drawable.ic_semfoto);


//		}

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        ListaFragmentAdapter adapter = new ListaFragmentAdapter(getActivity(),
                new String[]{getString(R.string.title_section1),
                        getString(R.string.title_section2), getString(R.string.title_section3),
                        getString(R.string.title_section4), getString(R.string.title_section5),
                        getString(R.string.sugestoes), getString(R.string.sobre), getString(R.string.action_exit),
                });
        list.setAdapter(adapter);

        list.setItemChecked(mCurrentSelectedPosition, true);
        return mDrawerRelativeLayout;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).commit();
                }

                getActivity().supportInvalidateOptionsMenu();
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (list != null) {
            list.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (mDrawerLayout != null && isDrawerOpen()) {

            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(int position);
    }
}
