// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.activities;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Login_ViewBinding<T extends Login> implements Unbinder {
  protected T target;

  private View view2131624269;

  private View view2131624262;

  public Login_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.imgView = Utils.findRequiredViewAsType(source, R.id.imgFundoLogin, "field 'imgView'", ImageView.class);
    target.editUsuario = Utils.findRequiredViewAsType(source, R.id.editTextUsuario, "field 'editUsuario'", EditText.class);
    target.editSenha = Utils.findRequiredViewAsType(source, R.id.editTextSenha, "field 'editSenha'", EditText.class);
    target.chkBoxDados = Utils.findRequiredViewAsType(source, R.id.checkBoxLembrarDados, "field 'chkBoxDados'", CheckBox.class);
    target.site = Utils.findRequiredViewAsType(source, R.id.siteLogin, "field 'site'", TextView.class);
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.button_login_anonymous, "method 'onAnonymousButtonClick'");
    view2131624269 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAnonymousButtonClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.BotaoLogin, "method 'onLoginButton'");
    view2131624262 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLoginButton();
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imgView = null;
    target.editUsuario = null;
    target.editSenha = null;
    target.chkBoxDados = null;
    target.site = null;
    target.loading = null;

    view2131624269.setOnClickListener(null);
    view2131624269 = null;
    view2131624262.setOnClickListener(null);
    view2131624262 = null;

    this.target = null;
  }
}
