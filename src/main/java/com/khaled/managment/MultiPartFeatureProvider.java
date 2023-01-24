package com.khaled.managment;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * {@link MultiPartFeature} is not auto-discovered. This {@link Feature} is discovered with {@link @Provider}
 * and registers {@link MultiPartFeature} manually.
 */
@Provider
public class MultiPartFeatureProvider implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        return new MultiPartFeature().configure(context);
    }
}
