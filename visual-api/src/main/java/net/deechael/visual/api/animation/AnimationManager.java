package net.deechael.visual.api.animation;

import net.deechael.visual.api.animation.path.PathManager;
import net.deechael.visual.api.builder.AnimationBuilder;
import net.deechael.visual.api.builder.AnimationSetBuilder;

public interface AnimationManager {

    AnimationBuilder createAnimation();

    AnimationSetBuilder createAnimationSet();

    PathManager getPathManager();

}
