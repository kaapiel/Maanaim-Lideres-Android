// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.mainMenu;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Sobre_ViewBinding implements Unbinder {
  private Sobre target;

  @UiThread
  public Sobre_ViewBinding(Sobre target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Sobre_ViewBinding(Sobre target, View source) {
    this.target = target;

    target.txtVersao = Utils.findRequiredViewAsType(source, R.id.txtVersao, "field 'txtVersao'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Sobre target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtVersao = null;
  }
}
