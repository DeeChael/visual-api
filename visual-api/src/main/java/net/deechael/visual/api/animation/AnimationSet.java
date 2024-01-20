package net.deechael.visual.api.animation;

import net.deechael.visual.api.Viewer;

import java.util.List;

public interface AnimationSet {

    List<Animation> listAnimations();

    void play(Viewer viewer);

}
