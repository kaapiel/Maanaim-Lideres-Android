// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.listagens;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditarMembro_ViewBinding<T extends EditarMembro> implements Unbinder {
  protected T target;

  private View view2131624166;

  private View view2131624173;

  public EditarMembro_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading_content, "field 'loading'", RelativeLayout.class);
    target.spinnerCelula = Utils.findRequiredViewAsType(source, R.id.editarMembro_spinnerCelula, "field 'spinnerCelula'", Spinner.class);
    target.spinnerSexo = Utils.findRequiredViewAsType(source, R.id.editarMembro_spinnerSexo, "field 'spinnerSexo'", Spinner.class);
    target.editNome = Utils.findRequiredViewAsType(source, R.id.editarMembro_editNome, "field 'editNome'", EditText.class);
    target.editApelido = Utils.findRequiredViewAsType(source, R.id.editarMembro_editApelido, "field 'editApelido'", EditText.class);
    target.editEmail = Utils.findRequiredViewAsType(source, R.id.editarMembro_editEmail, "field 'editEmail'", EditText.class);
    target.editCPF = Utils.findRequiredViewAsType(source, R.id.editarMembro_editCPF, "field 'editCPF'", EditText.class);
    target.editrg = Utils.findRequiredViewAsType(source, R.id.editarMembro_editRG, "field 'editrg'", EditText.class);
    target.editNumeroCelular = Utils.findRequiredViewAsType(source, R.id.editarMembro_editNumeroCelular, "field 'editNumeroCelular'", EditText.class);
    target.editDataBatismo = Utils.findRequiredViewAsType(source, R.id.editarMembro_editDataBatismo, "field 'editDataBatismo'", EditText.class);
    target.editCEP = Utils.findRequiredViewAsType(source, R.id.editarMembro_editCEP, "field 'editCEP'", EditText.class);
    target.editRua = Utils.findRequiredViewAsType(source, R.id.editarMembro_editRua, "field 'editRua'", EditText.class);
    target.editNumeroEndereco = Utils.findRequiredViewAsType(source, R.id.editarMembro_editNumeroEndereco, "field 'editNumeroEndereco'", EditText.class);
    target.editComplemento = Utils.findRequiredViewAsType(source, R.id.editarMembro_editComplemento, "field 'editComplemento'", EditText.class);
    target.editBairro = Utils.findRequiredViewAsType(source, R.id.editarMembro_editBairro, "field 'editBairro'", EditText.class);
    target.editCidade = Utils.findRequiredViewAsType(source, R.id.editarMembro_editCidade, "field 'editCidade'", EditText.class);
    target.editEstado = Utils.findRequiredViewAsType(source, R.id.editarMembro_editEstado, "field 'editEstado'", EditText.class);
    view = Utils.findRequiredView(source, R.id.editarMembro_botaoBuscarCEP, "field 'botaoBuscarCEP' and method 'onClickNoBotaoBuscarCEP'");
    target.botaoBuscarCEP = Utils.castView(view, R.id.editarMembro_botaoBuscarCEP, "field 'botaoBuscarCEP'", Button.class);
    view2131624166 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickNoBotaoBuscarCEP();
      }
    });
    view = Utils.findRequiredView(source, R.id.editarMembro_botaoCadastrar, "field 'botaoCadastrar' and method 'onClickNoBotaoCadastrar'");
    target.botaoCadastrar = Utils.castView(view, R.id.editarMembro_botaoCadastrar, "field 'botaoCadastrar'", Button.class);
    view2131624173 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickNoBotaoCadastrar();
      }
    });
    target.datePickerNascimento = Utils.findRequiredViewAsType(source, R.id.editarMembro_dataNasc, "field 'datePickerNascimento'", DatePicker.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.loading = null;
    target.spinnerCelula = null;
    target.spinnerSexo = null;
    target.editNome = null;
    target.editApelido = null;
    target.editEmail = null;
    target.editCPF = null;
    target.editrg = null;
    target.editNumeroCelular = null;
    target.editDataBatismo = null;
    target.editCEP = null;
    target.editRua = null;
    target.editNumeroEndereco = null;
    target.editComplemento = null;
    target.editBairro = null;
    target.editCidade = null;
    target.editEstado = null;
    target.botaoBuscarCEP = null;
    target.botaoCadastrar = null;
    target.datePickerNascimento = null;

    view2131624166.setOnClickListener(null);
    view2131624166 = null;
    view2131624173.setOnClickListener(null);
    view2131624173 = null;

    this.target = null;
  }
}
