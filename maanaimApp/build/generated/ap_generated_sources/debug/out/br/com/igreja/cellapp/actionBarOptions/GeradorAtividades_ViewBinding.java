// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.actionBarOptions;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GeradorAtividades_ViewBinding implements Unbinder {
  private GeradorAtividades target;

  private View view7f090067;

  private View view7f090064;

  @UiThread
  public GeradorAtividades_ViewBinding(GeradorAtividades target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GeradorAtividades_ViewBinding(final GeradorAtividades target, View source) {
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
    view7f090067 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnGenerateButton();
      }
    });
    view = Utils.findRequiredView(source, R.id.botaoCompartilhar, "field 'botaoCompartilhar' and method 'onClickOnShareButton'");
    target.botaoCompartilhar = Utils.castView(view, R.id.botaoCompartilhar, "field 'botaoCompartilhar'", Button.class);
    view7f090064 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnShareButton();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    GeradorAtividades target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading = null;
    target.txtCelula = null;
    target.palavra = null;
    target.louvor = null;
    target.dinamica = null;
    target.lanche = null;
    target.botaoGerar = null;
    target.botaoCompartilhar = null;

    view7f090067.setOnClickListener(null);
    view7f090067 = null;
    view7f090064.setOnClickListener(null);
    view7f090064 = null;
  }
}
