package swati4star.createpdf.interfaces.enhancers;

import swati4star.createpdf.model.EnhancementOptionsEntity;

public class MethodEnhancer implements Enhancer {

    private Runnable mMethod;
    private EnhancementOptionsEntity mOptionsEntity;

    public MethodEnhancer(Runnable enhanceFunction, EnhancementOptionsEntity optionsEntity) {
        mMethod = enhanceFunction;
        this.mOptionsEntity = optionsEntity;
    }


    /**
     * To apply an enhancement.
     */
    @Override
    public void enhance() {
        mMethod.run();
    }

    /**
     * @return The {@link EnhancementOptionsEntity} for this {@link Enhancer}.
     */
    @Override
    public EnhancementOptionsEntity getEnhancementOptionsEntity() {
        return mOptionsEntity;
    }
}
