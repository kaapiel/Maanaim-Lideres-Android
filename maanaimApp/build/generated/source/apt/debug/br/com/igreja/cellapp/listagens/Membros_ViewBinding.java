// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.listagens;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Membros_ViewBinding<T extends Membros> implements Unbinder {
  protected T target;

  private View view2131624313;

  public Membros_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.search = Utils.findRequiredViewAsType(source, R.id.searchView, "field 'search'", SearchView.class);
    view = Utils.findRequiredView(source, R.id.listaDeMembros, "field 'lista', method 'onItemClickDaLista', and method 'onItemLongClickDaLista'");
    target.lista = Utils.castView(view, R.id.listaDeMembros, "field 'lista'", ListView.class);
    view2131624313 = view;
    ((AdapterView<?>) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> p0, View p1, int p2, long p3) {
        target.onItemClickDaLista(p0, p1, p2, p3);
      }
    });
    ((AdapterView<?>) view).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> p0, View p1, int p2, long p3) {
        return target.onItemLongClickDaLista(p0, p1, p2, p3);
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.loading = null;
    target.search = null;
    target.lista = null;

    ((AdapterView<?>) view2131624313).setOnItemClickListener(null);
    ((AdapterView<?>) view2131624313).setOnItemLongClickListener(null);
    view2131624313 = null;

    this.target = null;
  }
}
