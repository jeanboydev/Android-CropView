package com.jeanboy.cropview.animation;
@SuppressWarnings("unused")
public interface SimpleValueAnimator {
    void startAnimation(long duration);
    void cancelAnimation();
    boolean isAnimationStarted();
    void addAnimatorListener(com.jeanboy.cropview.animation.SimpleValueAnimatorListener animatorListener);
}
