# visual-api
A powerful camera movement animation api for minecraft, mainly for bukkit

### Roadmap
- Smooth perspective rotation
- More path support
- More animation

### Examples
```java
// Create a straight line with an easeIn curve
AnimationSet testSet = VisualPlatform.getPlatform().getAnimationManager()
        .createAnimationSet()
        .append(
                VisualPlatform.getPlatform().getAnimationManager()
                        .createAnimation()
                        .start(point1) // Animation start point
                        .end(point2) // Animation end point
                        .duration(3) // How many seconds do this animation last
                        .framerate(120) // How many frames in one second
                        .perspective(direction, false) // Where player to look
                        .path(VisualPlatform.getPlatform().getAnimationManager().getPathManager().createLine(), false) // How the camera move
                        .curve(VisualPlatform.getPlatform().getCurveManager().easeIn()) // The curve of the animation
                        .build()
        )
        .build();
```