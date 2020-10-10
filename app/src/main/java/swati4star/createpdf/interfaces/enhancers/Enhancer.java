package swati4star.createpdf.interfaces.enhancers;

import swati4star.createpdf.model.EnhancementOptionsEntity;

/**
 * The {@link Enhancer} is a functional interface for all enhancements.
 */
public interface Enhancer {
    /**
     * To apply an enhancement.
     */
    void enhance();

    /**
     * @return The {@link EnhancementOptionsEntity} for this {@link Enhancer}.
     */
    EnhancementOptionsEntity getEnhancementOptionsEntity();

    /**
     * reset the values this Enhancer stored.
     * For example set the Quality to the value stored in the SharedPreferences
     *
     * (impl note: {@code default} cause there are some enhancers for the TextToPdfFragment
     * that i don't wanna touch right now)
     */
    default void resetValues() { }
}
