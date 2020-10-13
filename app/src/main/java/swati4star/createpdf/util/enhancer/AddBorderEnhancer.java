package swati4star.createpdf.util.enhancer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;

import swati4star.createpdf.R;
import swati4star.createpdf.interfaces.enhancers.Enhancer;
import swati4star.createpdf.interfaces.enhancers.EnhancerParentContract;
import swati4star.createpdf.interfaces.enhancers.ImageEnhancer;
import swati4star.createpdf.model.EnhancementOptionsEntity;
import swati4star.createpdf.util.Constants;
import swati4star.createpdf.util.DialogUtils;
import swati4star.createpdf.util.StringUtils;

import static swati4star.createpdf.util.Constants.DEFAULT_BORDER_WIDTH;
import static swati4star.createpdf.util.Constants.DEFAULT_IMAGE_BORDER_TEXT;

public class AddBorderEnhancer implements ImageEnhancer {
    @NonNull
    private final Context mContext;
    private final Activity mActivity;
    private final EnhancementOptionsEntity mEnhancementOptionsEntity;
    private final SharedPreferences mSharedPreferences;
    @NonNull
    private final EnhancerParentContract.View mView;
    private int mBorderWidth;

    public AddBorderEnhancer(@NonNull final Context ctx,
                             @NonNull final Activity activity,
                             @NonNull final EnhancerParentContract.View view) {
        mContext = ctx;
        mActivity = activity;
        mView = view;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);

        //THe name here is null because resetValues() fills in the name
        mEnhancementOptionsEntity = new EnhancementOptionsEntity(ctx,
                R.drawable.ic_border_image_black_24dp, null);

        resetValues();
    }

    @Override
    public void enhanceImage(Image image) {
        image.setBorder(Rectangle.BOX);
        image.setBorderWidth(mBorderWidth);
    }

    /**
     * To apply an enhancement.
     */
    @Override
    public void enhance() {
        DialogUtils.getInstance().createCustomDialogWithoutContent(mActivity, R.string.border)
                .customView(R.layout.dialog_border_image, true)
                .onPositive((dialog1, which) -> {
                    View view = dialog1.getCustomView();
                    final EditText input = view.findViewById(R.id.border_width);
                    int value = 0;
                    try {
                        value = Integer.parseInt(String.valueOf(input.getText()));
                        if (value > 200 || value < 0) {
                            StringUtils.getInstance().showSnackbar(mActivity, R.string.invalid_entry);
                        } else {
                            mBorderWidth = value;
                            updateName();
                            mView.updateView();
                        }
                    } catch (NumberFormatException e) {
                        StringUtils.getInstance().showSnackbar(mActivity, R.string.invalid_entry);
                    }
                    final CheckBox cbSetDefault = view.findViewById(R.id.cbSetDefault);
                    if (cbSetDefault.isChecked()) {
                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                        editor.putInt(Constants.DEFAULT_IMAGE_BORDER_TEXT, value);
                        editor.apply();
                    }
                }).build().show();
    }

    /**
     * Update the Name of the {@link EnhancementOptionsEntity}
     */
    private void updateName() {
        mEnhancementOptionsEntity.setName(
                String.format(mContext.getResources().getString(R.string.border_dialog_title),
                    mBorderWidth));
    }

    /**
     * @return The {@link EnhancementOptionsEntity} for this {@link Enhancer}.
     */
    @Override
    public EnhancementOptionsEntity getEnhancementOptionsEntity() {
        return mEnhancementOptionsEntity;
    }

    @Override
    public void resetValues() {
        mBorderWidth = mSharedPreferences.getInt(DEFAULT_IMAGE_BORDER_TEXT,
                DEFAULT_BORDER_WIDTH);
        updateName();
    }
}
