// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.maps;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Mapa_ViewBinding implements Unbinder {
  private Mapa target;

  @UiThread
  public Mapa_ViewBinding(Mapa target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Mapa_ViewBinding(Mapa target, View source) {
    this.target = target;

    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.carregando = Utils.findRequiredViewAsType(source, R.id.textLoading, "field 'carregando'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Mapa target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading = null;
    target.carregando = null;
  }
}
