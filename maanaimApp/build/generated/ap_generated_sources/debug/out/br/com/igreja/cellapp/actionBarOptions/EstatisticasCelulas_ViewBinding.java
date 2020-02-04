// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.actionBarOptions;

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

public class EstatisticasCelulas_ViewBinding implements Unbinder {
  private EstatisticasCelulas target;

  @UiThread
  public EstatisticasCelulas_ViewBinding(EstatisticasCelulas target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EstatisticasCelulas_ViewBinding(EstatisticasCelulas target, View source) {
    this.target = target;

    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.qtdCelulasVermelha = Utils.findRequiredViewAsType(source, R.id.qtdVermelha, "field 'qtdCelulasVermelha'", TextView.class);
    target.qtdCelulasAmarela = Utils.findRequiredViewAsType(source, R.id.qtdAmarela, "field 'qtdCelulasAmarela'", TextView.class);
    target.qtdCelulasAzul = Utils.findRequiredViewAsType(source, R.id.qtdAzul, "field 'qtdCelulasAzul'", TextView.class);
    target.qtdCelulasVerde = Utils.findRequiredViewAsType(source, R.id.qtdVerde, "field 'qtdCelulasVerde'", TextView.class);
    target.qtdCelulasLaranja = Utils.findRequiredViewAsType(source, R.id.qtdLaranja, "field 'qtdCelulasLaranja'", TextView.class);
    target.qtdCelulasBranca = Utils.findRequiredViewAsType(source, R.id.qtdBranca, "field 'qtdCelulasBranca'", TextView.class);
    target.qtdCelulasTotal = Utils.findRequiredViewAsType(source, R.id.qtdTotal, "field 'qtdCelulasTotal'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EstatisticasCelulas target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading = null;
    target.qtdCelulasVermelha = null;
    target.qtdCelulasAmarela = null;
    target.qtdCelulasAzul = null;
    target.qtdCelulasVerde = null;
    target.qtdCelulasLaranja = null;
    target.qtdCelulasBranca = null;
    target.qtdCelulasTotal = null;
  }
}
