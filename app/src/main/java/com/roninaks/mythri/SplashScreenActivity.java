package com.roninaks.mythri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {
    View view,view1,view2,view3,view4,view5,view6,view7;
    ImageView image,imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_splash_screen);
            view = (View) findViewById(R.id.view);
            view1 = (View) findViewById(R.id.view1);
            view2 = (View) findViewById(R.id.view2);
            view3 = (View) findViewById(R.id.view3);
            view4 = (View) findViewById(R.id.view4);
            view5 = (View) findViewById(R.id.view5);
            view6 = (View) findViewById(R.id.view6);
            view7 = (View) findViewById(R.id.view7);
            image = (ImageView) findViewById(R.id.image);
            imageView = (ImageView) findViewById(R.id.imageView);
        /*Adding an animation to the concentric circles and images using loadAnimation() method
        and used startAnimation() method to apply the defined animation to imageview and view object.
         */
            final android.view.animation.Animation colorAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            final android.view.animation.Animation imageAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            final android.view.animation.Animation circle = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            final android.view.animation.Animation circle1 = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            final android.view.animation.Animation circle2 = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            final android.view.animation.Animation circle3 = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            final android.view.animation.Animation circle4 = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            final android.view.animation.Animation circle5 = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            final android.view.animation.Animation circle6 = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
//            Animation delays are set using startOffset method.
            colorAnim.setStartOffset(0);
            view7.startAnimation(colorAnim);
            imageAnim.setStartOffset(200);
            image.startAnimation(imageAnim);
            circle.setStartOffset(300);
            view.startAnimation(circle);
            circle1.setStartOffset(400);
            view1.startAnimation(circle1);
            circle2.setStartOffset(500);
            view2.startAnimation(circle2);
            circle3.setStartOffset(600);
            view3.startAnimation(circle3);
            circle4.setStartOffset(700);
            view4.startAnimation(circle4);
            circle5.setStartOffset(800);
            view5.startAnimation(circle5);
            circle6.setStartOffset(900);
            view6.startAnimation(circle6);
            /*End of zoom_in animation and starting of next block of animation. The animation starting at the end of the last circle_filled_yellow animation
             * so, after the zoom_in animation completes the next block of animation will starts */
            circle6.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    try {
//                        start of heart_beat animation section.
                        final android.view.animation.Animation imageAnim = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.heart_beat);
                        final android.view.animation.Animation circle = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.heart_beat);
                        final android.view.animation.Animation circle1 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.heart_beat);
                        final android.view.animation.Animation circle2 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.heart_beat);
                        final android.view.animation.Animation circle3 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.heart_beat);
                        final android.view.animation.Animation circle4 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.heart_beat);
                        final android.view.animation.Animation circle5 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.heart_beat);
                        final android.view.animation.Animation circle6 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.heart_beat);
//                       The number of times the animation should repeat is set using repeatCount method.
                        imageAnim.setStartOffset(200);
                        imageAnim.setRepeatCount(3);
                        image.startAnimation(imageAnim);
                        circle.setStartOffset(300);
                        circle.setRepeatCount(3);
                        view.startAnimation(circle);
                        circle1.setStartOffset(400);
                        circle1.setRepeatCount(3);
                        view1.startAnimation(circle1);
                        circle2.setStartOffset(500);
                        circle2.setRepeatCount(3);
                        view2.startAnimation(circle2);
                        circle3.setStartOffset(600);
                        circle3.setRepeatCount(3);
                        view3.startAnimation(circle3);
                        circle4.setStartOffset(700);
                        circle4.setRepeatCount(3);
                        view4.startAnimation(circle4);
                        circle5.setStartOffset(800);
                        circle5.setRepeatCount(3);
                        view5.startAnimation(circle5);
                        circle6.setStartOffset(900);
                        circle6.setRepeatCount(3);
                        view6.startAnimation(circle6);
                        /*End of heart_beat animation*/
                        circle6.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                try {
//                                    starting
                                    final android.view.animation.Animation txtAnim = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fade_out);
                                    txtAnim.setStartOffset(750);
                                    imageView.startAnimation(txtAnim);
//                                    Set the visibility of imageView using setVisibility method.
//                                    while using visibility INVISIBLE the widget will be invisible but space for the widget will be show.
                                    imageView.setVisibility(View.INVISIBLE);
                                    final android.view.animation.Animation imageAnim = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.zoom_out);
                                    final android.view.animation.Animation circle = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.zoom_out);
                                    final android.view.animation.Animation circle1 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.zoom_out);
                                    final android.view.animation.Animation circle2 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.zoom_out);
                                    final android.view.animation.Animation circle3 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.zoom_out);
                                    final android.view.animation.Animation circle4 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.zoom_out);
                                    final android.view.animation.Animation circle5 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.zoom_out);
                                    final android.view.animation.Animation circle6 = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.zoom_out);
                                    final android.view.animation.Animation colorAnim = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.zoom_out);
                                    imageAnim.setStartOffset(200);
//                                  The animation transformation is applied after the animation is over is set using the FillAfter method
                                    imageAnim.setFillAfter(true);
                                    image.startAnimation(imageAnim);
                                    circle.setStartOffset(300);
                                    circle.setFillAfter(true);
                                    view.startAnimation(circle);
                                    circle1.setStartOffset(400);
                                    circle1.setFillAfter(true);
                                    view1.startAnimation(circle1);
                                    circle2.setStartOffset(500);
                                    circle2.setFillAfter(true);
                                    view2.startAnimation(circle2);
                                    circle3.setStartOffset(600);
                                    circle3.setFillAfter(true);
                                    view3.startAnimation(circle3);
                                    circle4.setStartOffset(700);
                                    circle4.setFillAfter(true);
                                    view4.startAnimation(circle4);
                                    circle5.setStartOffset(800);
                                    circle5.setFillAfter(true);
                                    view5.startAnimation(circle5);
                                    circle6.setStartOffset(900);
                                    circle6.setFillAfter(true);
                                    view6.startAnimation(circle6);
                                    colorAnim.setStartOffset(1000);
                                    colorAnim.setFillAfter(true);
                                    final double currTime = System.currentTimeMillis();
                                    view7.startAnimation(colorAnim);
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            while (System.currentTimeMillis() - currTime < 1500) {
                                                Log.d("SPINNING", "Spinning");
                                            }
                                            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }
                                    }).start();
                                }catch (Exception e){

                                }

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }catch (Exception e) {
                    }

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }catch (Exception e){}

    }
}
