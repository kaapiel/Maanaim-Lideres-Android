// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.mainMenu;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Sugestoes_ViewBinding implements Unbinder {
  private Sugestoes target;

  private View view7f090066;

  private View view7f090062;

  @UiThread
  public Sugestoes_ViewBinding(Sugestoes target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Sugestoes_ViewBinding(final Sugestoes target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.botaoEnviarSugestao, "field 'botaoEnviar' and method 'onClickOnSendButton'");
    target.botaoEnviar = Utils.castView(view, R.id.botaoEnviarSugestao, "field 'botaoEnviar'", Button.class);
    view7f090066 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnSendButton();
      }
    });
    view = Utils.findRequiredView(source, R.id.botaoCancelarSugestao, "field 'botaoCancelar' and method 'onClickOnCancelButton'");
    target.botaoCancelar = Utils.castView(view, R.id.botaoCancelarSugestao, "field 'botaoCancelar'", Button.class);
    view7f090062 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnCancelButton();
      }
    });
    target.editTextSugestao = Utils.findRequiredViewAsType(source, R.id.editTextSugestao, "field 'editTextSugestao'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Sugestoes target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.botaoEnviar = null;
    target.botaoCancelar = null;
    target.editTextSugestao = null;

    view7f090066.setOnClickListener(null);
    view7f090066 = null;
    view7f090062.setOnClickListener(null);
    view7f090062 = null;
  }
}
