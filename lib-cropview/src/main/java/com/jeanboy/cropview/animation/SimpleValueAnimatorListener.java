package com.jeanboy.cropview.animation;

public interface SimpleValueAnimatorListener {
    void onAnimationStarted();
    void onAnimationUpdated(float scale);
    void onAnimationFinished();
}
