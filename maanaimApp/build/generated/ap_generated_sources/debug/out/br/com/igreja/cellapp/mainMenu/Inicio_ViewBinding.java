// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.mainMenu;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Inicio_ViewBinding implements Unbinder {
  private Inicio target;

  @UiThread
  public Inicio_ViewBinding(Inicio target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Inicio_ViewBinding(Inicio target, View source) {
    this.target = target;

    target.textConexao = Utils.findRequiredViewAsType(source, R.id.textConexao, "field 'textConexao'", TextView.class);
    target.wv = Utils.findRequiredViewAsType(source, R.id.webView1, "field 'wv'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Inicio target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textConexao = null;
    target.wv = null;
  }
}
