// Generated code from Butter Knife. Do not modify!
package br.com.igreja.cellapp.mainMenu;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import br.com.igreja.cellapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Relatorios_ViewBinding<T extends Relatorios> implements Unbinder {
  protected T target;

  public Relatorios_ViewBinding(T target, View source) {
    this.target = target;

    target.nomelider = Utils.findRequiredViewAsType(source, R.id.nomeLider, "field 'nomelider'", EditText.class);
    target.qtdInicio = Utils.findRequiredViewAsType(source, R.id.qtdinicio, "field 'qtdInicio'", EditText.class);
    target.nomeAux = Utils.findRequiredViewAsType(source, R.id.nomeAuxiliar, "field 'nomeAux'", EditText.class);
    target.qtdPresentes = Utils.findRequiredViewAsType(source, R.id.qtdpresentes, "field 'qtdPresentes'", EditText.class);
    target.qtdMembros = Utils.findRequiredViewAsType(source, R.id.qtdmembros, "field 'qtdMembros'", EditText.class);
    target.qtdCriancas = Utils.findRequiredViewAsType(source, R.id.qtdcriancas, "field 'qtdCriancas'", EditText.class);
    target.qtdVisitantes = Utils.findRequiredViewAsType(source, R.id.qtdvisitantes, "field 'qtdVisitantes'", EditText.class);
    target.qtdTornMemb = Utils.findRequiredViewAsType(source, R.id.qtdtornmemb, "field 'qtdTornMemb'", EditText.class);
    target.qtdTadel = Utils.findRequiredViewAsType(source, R.id.qtdtadel, "field 'qtdTadel'", EditText.class);
    target.qtdDiscipulos = Utils.findRequiredViewAsType(source, R.id.qtddisciulos, "field 'qtdDiscipulos'", EditText.class);
    target.qtdDiscipuladores = Utils.findRequiredViewAsType(source, R.id.qtddiscipuladores, "field 'qtdDiscipuladores'", EditText.class);
    target.dataIniciou = Utils.findRequiredViewAsType(source, R.id.datePickerInicio, "field 'dataIniciou'", DatePicker.class);
    target.dataMultiplicacao = Utils.findRequiredViewAsType(source, R.id.datePickerMultiplicacao, "field 'dataMultiplicacao'", DatePicker.class);
    target.regiao = Utils.findRequiredViewAsType(source, R.id.spinnerRegiao, "field 'regiao'", Spinner.class);
    target.ofertas = Utils.findRequiredViewAsType(source, R.id.spinnerOferta, "field 'ofertas'", Spinner.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.nomelider = null;
    target.qtdInicio = null;
    target.nomeAux = null;
    target.qtdPresentes = null;
    target.qtdMembros = null;
    target.qtdCriancas = null;
    target.qtdVisitantes = null;
    target.qtdTornMemb = null;
    target.qtdTadel = null;
    target.qtdDiscipulos = null;
    target.qtdDiscipuladores = null;
    target.dataIniciou = null;
    target.dataMultiplicacao = null;
    target.regiao = null;
    target.ofertas = null;

    this.target = null;
  }
}
