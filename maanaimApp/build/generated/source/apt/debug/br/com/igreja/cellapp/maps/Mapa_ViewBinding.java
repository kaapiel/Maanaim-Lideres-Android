// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.maps;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Mapa_ViewBinding<T extends Mapa> implements Unbinder {
  protected T target;

  public Mapa_ViewBinding(T target, View source) {
    this.target = target;

    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.carregando = Utils.findRequiredViewAsType(source, R.id.textLoading, "field 'carregando'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.loading = null;
    target.carregando = null;

    this.target = null;
  }
}
