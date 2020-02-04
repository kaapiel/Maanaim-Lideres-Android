// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.activities;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Login_ViewBinding implements Unbinder {
  private Login target;

  private View view7f090071;

  private View view7f090008;

  @UiThread
  public Login_ViewBinding(Login target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Login_ViewBinding(final Login target, View source) {
    this.target = target;

    View view;
    target.imgView = Utils.findRequiredViewAsType(source, R.id.imgFundoLogin, "field 'imgView'", ImageView.class);
    target.editUsuario = Utils.findRequiredViewAsType(source, R.id.editTextUsuario, "field 'editUsuario'", EditText.class);
    target.editSenha = Utils.findRequiredViewAsType(source, R.id.editTextSenha, "field 'editSenha'", EditText.class);
    target.chkBoxDados = Utils.findRequiredViewAsType(source, R.id.checkBoxLembrarDados, "field 'chkBoxDados'", CheckBox.class);
    target.site = Utils.findRequiredViewAsType(source, R.id.siteLogin, "field 'site'", TextView.class);
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.button_login_anonymous, "method 'onAnonymousButtonClick'");
    view7f090071 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAnonymousButtonClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.BotaoLogin, "method 'onLoginButton'");
    view7f090008 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLoginButton();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Login target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgView = null;
    target.editUsuario = null;
    target.editSenha = null;
    target.chkBoxDados = null;
    target.site = null;
    target.loading = null;

    view7f090071.setOnClickListener(null);
    view7f090071 = null;
    view7f090008.setOnClickListener(null);
    view7f090008 = null;
  }
}
