// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.listagens;

import android.view.View;
import android.widget.ImageView;
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

public class MembroSelecionado_ViewBinding implements Unbinder {
  private MembroSelecionado target;

  private View view7f090114;

  private View view7f090113;

  private View view7f090115;

  private View view7f090119;

  @UiThread
  public MembroSelecionado_ViewBinding(MembroSelecionado target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MembroSelecionado_ViewBinding(final MembroSelecionado target, View source) {
    this.target = target;

    View view;
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.carregando = Utils.findRequiredViewAsType(source, R.id.textLoading, "field 'carregando'", TextView.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.fotoDeFundo, "field 'imageView'", ImageView.class);
    target.textCelularMembro = Utils.findRequiredViewAsType(source, R.id.textCelularMembro, "field 'textCelularMembro'", TextView.class);
    target.textTelefoneMembro = Utils.findRequiredViewAsType(source, R.id.textTelefoneMembro, "field 'textTelefoneMembro'", TextView.class);
    view = Utils.findRequiredView(source, R.id.imageButtonExcluirMembro, "field 'imageDelete' and method 'onClickNoBotaoExcluir'");
    target.imageDelete = Utils.castView(view, R.id.imageButtonExcluirMembro, "field 'imageDelete'", ImageView.class);
    view7f090114 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickNoBotaoExcluir();
      }
    });
    view = Utils.findRequiredView(source, R.id.imageButtonEditarMembro, "field 'imageEdit' and method 'onClickOnEditButton'");
    target.imageEdit = Utils.castView(view, R.id.imageButtonEditarMembro, "field 'imageEdit'", ImageView.class);
    view7f090113 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnEditButton();
      }
    });
    view = Utils.findRequiredView(source, R.id.imageButtonMoverMembro, "field 'imageMover' and method 'onClickOnMoveMember'");
    target.imageMover = Utils.castView(view, R.id.imageButtonMoverMembro, "field 'imageMover'", ImageView.class);
    view7f090115 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOnMoveMember();
      }
    });
    view = Utils.findRequiredView(source, R.id.imageViewFotoMembro, "field 'imageViewFotoMembro' and method 'onClickNaImagem'");
    target.imageViewFotoMembro = Utils.castView(view, R.id.imageViewFotoMembro, "field 'imageViewFotoMembro'", ImageView.class);
    view7f090119 = view;
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
  @CallSuper
  public void unbind() {
    MembroSelecionado target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

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

    view7f090114.setOnClickListener(null);
    view7f090114 = null;
    view7f090113.setOnClickListener(null);
    view7f090113 = null;
    view7f090115.setOnClickListener(null);
    view7f090115 = null;
    view7f090119.setOnClickListener(null);
    view7f090119 = null;
  }
}
