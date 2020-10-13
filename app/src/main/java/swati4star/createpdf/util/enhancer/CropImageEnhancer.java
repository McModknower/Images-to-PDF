package swati4star.createpdf.util.enhancer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.theartofdev.edmodo.cropper.CropImage;

import swati4star.createpdf.R;
import swati4star.createpdf.activity.CropImageActivity;
import swati4star.createpdf.interfaces.enhancers.Enhancer;
import swati4star.createpdf.model.EnhancementOptionsEntity;

public class CropImageEnhancer implements Enhancer {

    private Fragment mFragment;
    private Activity mActivity;
    private EnhancementOptionsEntity mEnhancementOptionsEntity;

    public CropImageEnhancer(@NonNull final Fragment fragment,
                             @NonNull final Activity activity,
                             @NonNull final Context ctx) {
        this.mFragment = fragment;
        this.mActivity = activity;
        mEnhancementOptionsEntity = new EnhancementOptionsEntity(
                ctx, R.drawable.baseline_crop_rotate_24, R.string.edit_images_text);
    }

    /**
     * To apply an enhancement.
     */
    @Override
    public void enhance() {
        Intent intent = new Intent(mActivity, CropImageActivity.class);
        mFragment.startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    /**
     * @return The {@link EnhancementOptionsEntity} for this {@link Enhancer}.
     */
    @Override
    public EnhancementOptionsEntity getEnhancementOptionsEntity() {
        return mEnhancementOptionsEntity;
    }
}
