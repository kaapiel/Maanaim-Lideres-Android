// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.mainMenu;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Sugestoes_ViewBinding<T extends Sugestoes> implements Unbinder {
  protected T target;

  private View view2131624344;

  private View view2131624343;

  public Sugestoes_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.botaoEnviarSugestao, "field 'botaoEnviar' and method 'onClickOnSendButton'");
    target.botaoEnviar = Utils.castView(view, R.id.botaoEnviarSugestao, "field 'botaoEnviar'", Button.class);
    view2131624344 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnSendButton();
      }
    });
    view = Utils.findRequiredView(source, R.id.botaoCancelarSugestao, "field 'botaoCancelar' and method 'onClickOnCancelButton'");
    target.botaoCancelar = Utils.castView(view, R.id.botaoCancelarSugestao, "field 'botaoCancelar'", Button.class);
    view2131624343 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnCancelButton();
      }
    });
    target.editTextSugestao = Utils.findRequiredViewAsType(source, R.id.editTextSugestao, "field 'editTextSugestao'", EditText.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.botaoEnviar = null;
    target.botaoCancelar = null;
    target.editTextSugestao = null;

    view2131624344.setOnClickListener(null);
    view2131624344 = null;
    view2131624343.setOnClickListener(null);
    view2131624343 = null;

    this.target = null;
  }
}
