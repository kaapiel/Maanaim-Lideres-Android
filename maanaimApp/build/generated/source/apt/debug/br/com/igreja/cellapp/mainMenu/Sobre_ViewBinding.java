// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.mainMenu;

import android.view.View;
import android.widget.TextView;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Sobre_ViewBinding<T extends Sobre> implements Unbinder {
  protected T target;

  public Sobre_ViewBinding(T target, View source) {
    this.target = target;

    target.txtVersao = Utils.findRequiredViewAsType(source, R.id.txtVersao, "field 'txtVersao'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.txtVersao = null;

    this.target = null;
  }
}
