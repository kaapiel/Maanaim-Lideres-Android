// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.listagens;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CelulaSelecionada_ViewBinding<T extends CelulaSelecionada> implements Unbinder {
  protected T target;

  private View view2131624130;

  private View view2131624128;

  public CelulaSelecionada_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.tvmc = Utils.findRequiredViewAsType(source, R.id.textViewMembrosCelula, "field 'tvmc'", TextView.class);
    target.tvlc = Utils.findRequiredViewAsType(source, R.id.textViewLiderCelula, "field 'tvlc'", TextView.class);
    view = Utils.findRequiredView(source, R.id.listViewMembrosCelula, "field 'listaMembros' and method 'onItemLongClickMembros'");
    target.listaMembros = Utils.castView(view, R.id.listViewMembrosCelula, "field 'listaMembros'", ListView.class);
    view2131624130 = view;
    ((AdapterView<?>) view).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> p0, View p1, int p2, long p3) {
        return target.onItemLongClickMembros(p0, p1, p2, p3);
      }
    });
    view = Utils.findRequiredView(source, R.id.listViewLideres, "field 'listaLideres' and method 'onItemLongClickLideres'");
    target.listaLideres = Utils.castView(view, R.id.listViewLideres, "field 'listaLideres'", ListView.class);
    view2131624128 = view;
    ((AdapterView<?>) view).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> p0, View p1, int p2, long p3) {
        return target.onItemLongClickLideres(p0, p1, p2, p3);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.loading = null;
    target.tvmc = null;
    target.tvlc = null;
    target.listaMembros = null;
    target.listaLideres = null;

    ((AdapterView<?>) view2131624130).setOnItemLongClickListener(null);
    view2131624130 = null;
    ((AdapterView<?>) view2131624128).setOnItemLongClickListener(null);
    view2131624128 = null;

    this.target = null;
  }
}
