package net.deechael.visual.animation;

import net.deechael.visual.animation.path.PathManagerImpl;
import net.deechael.visual.api.animation.AnimationManager;
import net.deechael.visual.api.animation.path.PathManager;
import net.deechael.visual.api.builder.AnimationBuilder;
import net.deechael.visual.api.builder.AnimationSetBuilder;
import net.deechael.visual.builder.AnimationBuilderImpl;
import net.deechael.visual.builder.AnimationSetBuilderImpl;

public class AnimationManagerImpl implements AnimationManager {

    private final PathManager pathManager = new PathManagerImpl();

    @Override
    public AnimationBuilder createAnimation() {
        return new AnimationBuilderImpl();
    }

    @Override
    public AnimationSetBuilder createAnimationSet() {
        return new AnimationSetBuilderImpl();
    }

    @Override
    public PathManager getPathManager() {
        return this.pathManager;
    }

}
