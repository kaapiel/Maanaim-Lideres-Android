// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.actionBarOptions;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GeradorAtividades_ViewBinding<T extends GeradorAtividades> implements Unbinder {
  protected T target;

  private View view2131624084;

  private View view2131624206;

  public GeradorAtividades_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.txtCelula = Utils.findRequiredViewAsType(source, R.id.txtCelula, "field 'txtCelula'", TextView.class);
    target.palavra = Utils.findRequiredViewAsType(source, R.id.editPalavra, "field 'palavra'", EditText.class);
    target.louvor = Utils.findRequiredViewAsType(source, R.id.editLouvor, "field 'louvor'", EditText.class);
    target.dinamica = Utils.findRequiredViewAsType(source, R.id.editDinamica, "field 'dinamica'", EditText.class);
    target.lanche = Utils.findRequiredViewAsType(source, R.id.editLanche, "field 'lanche'", EditText.class);
    view = Utils.findRequiredView(source, R.id.botaoGerar, "field 'botaoGerar' and method 'onClickOnGenerateButton'");
    target.botaoGerar = Utils.castView(view, R.id.botaoGerar, "field 'botaoGerar'", Button.class);
    view2131624084 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnGenerateButton();
      }
    });
    view = Utils.findRequiredView(source, R.id.botaoCompartilhar, "field 'botaoCompartilhar' and method 'onClickOnShareButton'");
    target.botaoCompartilhar = Utils.castView(view, R.id.botaoCompartilhar, "field 'botaoCompartilhar'", Button.class);
    view2131624206 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnShareButton();
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.loading = null;
    target.txtCelula = null;
    target.palavra = null;
    target.louvor = null;
    target.dinamica = null;
    target.lanche = null;
    target.botaoGerar = null;
    target.botaoCompartilhar = null;

    view2131624084.setOnClickListener(null);
    view2131624084 = null;
    view2131624206.setOnClickListener(null);
    view2131624206 = null;

    this.target = null;
  }
}
