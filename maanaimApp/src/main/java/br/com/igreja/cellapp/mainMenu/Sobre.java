package br.com.igreja.cellapp.mainMenu;


import br.com.igreja.cellapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.TextView;

public class Sobre extends Activity{

	@BindView(R.id.txtVersao) TextView txtVersao;
	private String versionName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.versao);
		ButterKnife.bind(this);

		setUIelements();
	}

	private void setUIelements() {
		try{
			PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			versionName = pInfo.versionName;
		} catch (Exception e){
			e.printStackTrace();
		}

		txtVersao.setText(getString(R.string.versao)+": v"+versionName);
	}
}
