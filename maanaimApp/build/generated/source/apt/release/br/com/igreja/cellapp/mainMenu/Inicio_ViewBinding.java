// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.mainMenu;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Inicio_ViewBinding<T extends Inicio> implements Unbinder {
  protected T target;

  public Inicio_ViewBinding(T target, View source) {
    this.target = target;

    target.textConexao = Utils.findRequiredViewAsType(source, R.id.textConexao, "field 'textConexao'", TextView.class);
    target.wv = Utils.findRequiredViewAsType(source, R.id.webView1, "field 'wv'", WebView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.textConexao = null;
    target.wv = null;

    this.target = null;
  }
}
