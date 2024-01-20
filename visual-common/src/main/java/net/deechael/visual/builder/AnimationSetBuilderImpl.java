package net.deechael.visual.builder;

import net.deechael.visual.animation.AnimationSetImpl;
import net.deechael.visual.api.animation.Animation;
import net.deechael.visual.api.animation.AnimationSet;
import net.deechael.visual.api.builder.AnimationSetBuilder;
import net.deechael.visual.util.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class AnimationSetBuilderImpl implements AnimationSetBuilder {

    private final List<Animation> animations = new ArrayList<>();

    @Override
    public AnimationSetBuilder append(Animation animation) {
        Preconditions.notNull(animation, "Animation cannot be null!");

        this.animations.add(animation);
        return this;
    }

    @Override
    public AnimationSet build() {
        return new AnimationSetImpl(animations);
    }

}
