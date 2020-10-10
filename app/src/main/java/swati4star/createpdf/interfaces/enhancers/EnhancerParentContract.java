package swati4star.createpdf.interfaces.enhancers;

/**
 * The {@link EnhancerParentContract} is a contract used by the fragment to communicate with its
 * enhancements.
 */
public interface EnhancerParentContract {
    /**
     * Represents the view (the fragment in this case).
     */
    interface View {
        /**
         * Update the view when enhancement is changed.
         */
        void updateView();
    }
}
