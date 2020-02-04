// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.listagens;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MembroSelecionado_ViewBinding<T extends MembroSelecionado> implements Unbinder {
  protected T target;

  private View view2131624281;

  private View view2131624282;

  private View view2131624283;

  private View view2131624279;

  public MembroSelecionado_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.carregando = Utils.findRequiredViewAsType(source, R.id.textLoading, "field 'carregando'", TextView.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.fotoDeFundo, "field 'imageView'", ImageView.class);
    target.textCelularMembro = Utils.findRequiredViewAsType(source, R.id.textCelularMembro, "field 'textCelularMembro'", TextView.class);
    target.textTelefoneMembro = Utils.findRequiredViewAsType(source, R.id.textTelefoneMembro, "field 'textTelefoneMembro'", TextView.class);
    view = Utils.findRequiredView(source, R.id.imageButtonExcluirMembro, "field 'imageDelete' and method 'onClickNoBotaoExcluir'");
    target.imageDelete = Utils.castView(view, R.id.imageButtonExcluirMembro, "field 'imageDelete'", ImageView.class);
    view2131624281 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickNoBotaoExcluir();
      }
    });
    view = Utils.findRequiredView(source, R.id.imageButtonEditarMembro, "field 'imageEdit' and method 'onClickOnEditButton'");
    target.imageEdit = Utils.castView(view, R.id.imageButtonEditarMembro, "field 'imageEdit'", ImageView.class);
    view2131624282 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnEditButton();
      }
    });
    view = Utils.findRequiredView(source, R.id.imageButtonMoverMembro, "field 'imageMover' and method 'onClickOnMoveMember'");
    target.imageMover = Utils.castView(view, R.id.imageButtonMoverMembro, "field 'imageMover'", ImageView.class);
    view2131624283 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnMoveMember();
      }
    });
    view = Utils.findRequiredView(source, R.id.imageViewFotoMembro, "field 'imageViewFotoMembro' and method 'onClickNaImagem'");
    target.imageViewFotoMembro = Utils.castView(view, R.id.imageViewFotoMembro, "field 'imageViewFotoMembro'", ImageView.class);
    view2131624279 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickNaImagem();
      }
    });
    target.editTextDataDeNascimentoDoMembro = Utils.findRequiredViewAsType(source, R.id.textDataDeNascimentoDoMembro, "field 'editTextDataDeNascimentoDoMembro'", TextView.class);
    target.textViewNomeMembro = Utils.findRequiredViewAsType(source, R.id.textViewNomeMembro, "field 'textViewNomeMembro'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.loading = null;
    target.carregando = null;
    target.imageView = null;
    target.textCelularMembro = null;
    target.textTelefoneMembro = null;
    target.imageDelete = null;
    target.imageEdit = null;
    target.imageMover = null;
    target.imageViewFotoMembro = null;
    target.editTextDataDeNascimentoDoMembro = null;
    target.textViewNomeMembro = null;

    view2131624281.setOnClickListener(null);
    view2131624281 = null;
    view2131624282.setOnClickListener(null);
    view2131624282 = null;
    view2131624283.setOnClickListener(null);
    view2131624283 = null;
    view2131624279.setOnClickListener(null);
    view2131624279 = null;

    this.target = null;
  }
}
