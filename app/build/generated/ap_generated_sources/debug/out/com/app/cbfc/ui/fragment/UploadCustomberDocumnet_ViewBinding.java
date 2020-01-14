// Generated code from Butter Knife. Do not modify!
package com.app.cbfc.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.app.cbfc.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadCustomberDocumnet_ViewBinding implements Unbinder {
  private UploadCustomberDocumnet target;

  @UiThread
  public UploadCustomberDocumnet_ViewBinding(UploadCustomberDocumnet target, View source) {
    this.target = target;

    target.profileImage = Utils.findRequiredViewAsType(source, R.id.profile_image, "field 'profileImage'", ImageView.class);
    target.adharImage = Utils.findRequiredViewAsType(source, R.id.adhar_image, "field 'adharImage'", ImageView.class);
    target.panImage = Utils.findRequiredViewAsType(source, R.id.pan_image, "field 'panImage'", ImageView.class);
    target.uploadProfileImage = Utils.findRequiredViewAsType(source, R.id.upload_profile_image, "field 'uploadProfileImage'", TextView.class);
    target.uploadAdharImage = Utils.findRequiredViewAsType(source, R.id.upload_adhar_image, "field 'uploadAdharImage'", TextView.class);
    target.uploadPanImage = Utils.findRequiredViewAsType(source, R.id.upload_pan_image, "field 'uploadPanImage'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadCustomberDocumnet target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.profileImage = null;
    target.adharImage = null;
    target.panImage = null;
    target.uploadProfileImage = null;
    target.uploadAdharImage = null;
    target.uploadPanImage = null;
  }
}
