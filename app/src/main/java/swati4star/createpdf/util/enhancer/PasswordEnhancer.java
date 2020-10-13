package swati4star.createpdf.util.enhancer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import swati4star.createpdf.R;
import swati4star.createpdf.interfaces.enhancers.EnhancerParentContract;
import swati4star.createpdf.interfaces.enhancers.PdfWriterEnhancer;
import swati4star.createpdf.model.EnhancementOptionsEntity;
import swati4star.createpdf.util.DefaultTextWatcher;
import swati4star.createpdf.util.DialogUtils;
import swati4star.createpdf.util.StringUtils;

import static swati4star.createpdf.util.Constants.MASTER_PWD_STRING;
import static swati4star.createpdf.util.Constants.appName;

public class PasswordEnhancer implements PdfWriterEnhancer {

    private final Activity mActivity;
    private final EnhancementOptionsEntity mEnhancementOptionsEntity;
    private EnhancerParentContract.View mView;
    private String mPassword;

    public PasswordEnhancer(@NonNull final Activity activity,
                     @NonNull final EnhancerParentContract.View view) {
        mActivity = activity;
        mPassword = null;
        mEnhancementOptionsEntity = new EnhancementOptionsEntity(
                mActivity, R.drawable.baseline_enhanced_encryption_24, R.string.set_password);
        mView = view;
    }

    @Override
    public void enhancePdfWriter(PdfWriter writer) throws DocumentException {
        if (mPassword != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
            String masterPwd = sharedPreferences.getString(MASTER_PWD_STRING, appName);
            assert masterPwd != null;
            writer.setEncryption(mPassword.getBytes(), masterPwd.getBytes(),
                    PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_COPY,
                    PdfWriter.ENCRYPTION_AES_128);

            Log.v("Stage create.writer", "Set Encryption");
        }
    }

    /**
     * To apply an enhancement.
     */
    @Override
    public void enhance() {
        MaterialDialog.Builder builder = DialogUtils.getInstance().createCustomDialogWithoutContent(mActivity,
                R.string.set_password);
        final MaterialDialog dialog = builder
                .customView(R.layout.custom_dialog, true)
                .neutralText(R.string.remove_dialog)
                .build();

        final View positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        final View neutralAction = dialog.getActionButton(DialogAction.NEUTRAL);
        final EditText passwordInput = dialog.getCustomView().findViewById(R.id.password);
        passwordInput.setText(mPassword);
        passwordInput.addTextChangedListener(
                new DefaultTextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        positiveAction.setEnabled(s.toString().trim().length() > 0);
                    }
                });

        positiveAction.setOnClickListener(v -> {
            if (StringUtils.getInstance().isEmpty(passwordInput.getText())) {
                StringUtils.getInstance().showSnackbar(mActivity, R.string.snackbar_password_cannot_be_blank);
            } else {
                mPassword = passwordInput.getText().toString();
                onPasswordAdded();
                dialog.dismiss();
            }
        });

        if (StringUtils.getInstance().isNotEmpty(mPassword)) {
            neutralAction.setOnClickListener(v -> {
                mPassword = null;
                onPasswordRemoved();
                dialog.dismiss();
                StringUtils.getInstance().showSnackbar(mActivity, R.string.password_remove);
            });
        }
        dialog.show();
        positiveAction.setEnabled(false);
    }

    private void onPasswordAdded() {
        changeIcon(R.drawable.baseline_done_24);
    }

    private void onPasswordRemoved() {
        changeIcon(R.drawable.baseline_enhanced_encryption_24);
    }

    private void changeIcon(int drawable) {
        mEnhancementOptionsEntity
                .setImage(mActivity.getResources().getDrawable(drawable));
        mView.updateView();
    }

    /**
     * @return The {@link EnhancementOptionsEntity} for this {@link swati4star.createpdf.interfaces.enhancers.Enhancer}.
     */
    @Override
    public EnhancementOptionsEntity getEnhancementOptionsEntity() {
        return mEnhancementOptionsEntity;
    }

    /**
     * reset the values this Enhancer stored.
     * For example set the Quality to the value stored in the SharedPreferences
     * <p>
     * (impl note: {@code default} cause there are some enhancers for the TextToPdfFragment
     * that i don't wanna touch right now)
     */
    @Override
    public void resetValues() {
        mPassword = null;
    }
}
