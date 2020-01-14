// Generated code from Butter Knife. Do not modify!
package com.app.cbfc.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.cbfc.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddCustomerFragment_ViewBinding implements Unbinder {
  private AddCustomerFragment target;

  @UiThread
  public AddCustomerFragment_ViewBinding(AddCustomerFragment target, View source) {
    this.target = target;

    target.etFullName = Utils.findRequiredViewAsType(source, R.id.cus_full_name, "field 'etFullName'", EditText.class);
    target.etFatherName = Utils.findRequiredViewAsType(source, R.id.cus_father_name, "field 'etFatherName'", EditText.class);
    target.etMobile = Utils.findRequiredViewAsType(source, R.id.cus_mobile, "field 'etMobile'", EditText.class);
    target.etAddress = Utils.findRequiredViewAsType(source, R.id.cus_address, "field 'etAddress'", EditText.class);
    target.etAdharCardNumber = Utils.findRequiredViewAsType(source, R.id.cus_adhar_card_number, "field 'etAdharCardNumber'", EditText.class);
    target.etPanCardNumber = Utils.findRequiredViewAsType(source, R.id.cus_pan_card_number, "field 'etPanCardNumber'", EditText.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddCustomerFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etFullName = null;
    target.etFatherName = null;
    target.etMobile = null;
    target.etAddress = null;
    target.etAdharCardNumber = null;
    target.etPanCardNumber = null;
    target.submit = null;
  }
}
