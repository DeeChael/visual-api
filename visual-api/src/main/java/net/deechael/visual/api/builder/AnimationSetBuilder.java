package net.deechael.visual.api.builder;

import net.deechael.visual.api.animation.Animation;
import net.deechael.visual.api.animation.AnimationSet;

public interface AnimationSetBuilder {

    AnimationSetBuilder append(Animation animation);

    AnimationSet build();

}
