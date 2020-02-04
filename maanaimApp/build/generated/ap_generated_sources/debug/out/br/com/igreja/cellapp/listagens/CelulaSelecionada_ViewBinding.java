// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.listagens;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CelulaSelecionada_ViewBinding implements Unbinder {
  private CelulaSelecionada target;

  private View view7f09012f;

  private View view7f09012e;

  @UiThread
  public CelulaSelecionada_ViewBinding(CelulaSelecionada target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CelulaSelecionada_ViewBinding(final CelulaSelecionada target, View source) {
    this.target = target;

    View view;
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.tvmc = Utils.findRequiredViewAsType(source, R.id.textViewMembrosCelula, "field 'tvmc'", TextView.class);
    target.tvlc = Utils.findRequiredViewAsType(source, R.id.textViewLiderCelula, "field 'tvlc'", TextView.class);
    view = Utils.findRequiredView(source, R.id.listViewMembrosCelula, "field 'listaMembros' and method 'onItemLongClickMembros'");
    target.listaMembros = Utils.castView(view, R.id.listViewMembrosCelula, "field 'listaMembros'", ListView.class);
    view7f09012f = view;
    ((AdapterView<?>) view).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> p0, View p1, int p2, long p3) {
        return target.onItemLongClickMembros(p0, p1, p2, p3);
      }
    });
    view = Utils.findRequiredView(source, R.id.listViewLideres, "field 'listaLideres' and method 'onItemLongClickLideres'");
    target.listaLideres = Utils.castView(view, R.id.listViewLideres, "field 'listaLideres'", ListView.class);
    view7f09012e = view;
    ((AdapterView<?>) view).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> p0, View p1, int p2, long p3) {
        return target.onItemLongClickLideres(p0, p1, p2, p3);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CelulaSelecionada target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading = null;
    target.tvmc = null;
    target.tvlc = null;
    target.listaMembros = null;
    target.listaLideres = null;

    ((AdapterView<?>) view7f09012f).setOnItemLongClickListener(null);
    view7f09012f = null;
    ((AdapterView<?>) view7f09012e).setOnItemLongClickListener(null);
    view7f09012e = null;
  }
}
