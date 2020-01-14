// Generated code from Butter Knife. Do not modify!
package com.app.cbfc.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.cbfc.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.userId = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'userId'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'password'", EditText.class);
    target.login_button = Utils.findRequiredViewAsType(source, R.id.login_button, "field 'login_button'", Button.class);
    target.rgUserType = Utils.findRequiredViewAsType(source, R.id.rg_user_type, "field 'rgUserType'", RadioGroup.class);
    target.rbAdmin = Utils.findRequiredViewAsType(source, R.id.rb_admin, "field 'rbAdmin'", RadioButton.class);
    target.rbEmployee = Utils.findRequiredViewAsType(source, R.id.rb_employee, "field 'rbEmployee'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.userId = null;
    target.password = null;
    target.login_button = null;
    target.rgUserType = null;
    target.rbAdmin = null;
    target.rbEmployee = null;
  }
}
