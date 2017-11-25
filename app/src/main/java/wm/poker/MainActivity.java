package wm.poker;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageView card;
    boolean isBack = true;
    LinearLayout background;
    AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card = (ImageView)findViewById(R.id.card_back);
        background = (LinearLayout)findViewById(R.id.linearBackground);
        accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    }

    public void flip(View v)
    {
        //card = (ImageView)findViewById(R.id.card_back);
        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(300);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(isBack)
                {
                    card.setImageResource(R.drawable.card_front);
                    isBack = false;
                }
                else
                {
                    card.setImageResource(R.drawable.card_back);
                    isBack = true;
                }
                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(600);
                fadeIn.setFillAfter(true);
                card.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        card.startAnimation(fadeOut);

    }

    public void dropCard(View v)
    {
        prepareObjectAnimator(accelerateDecelerateInterpolator);
    }

    public void prepareObjectAnimator(TimeInterpolator timeInterpolator){
        //float w = (float)playGround.getWidth();
        float h = (float)background.getHeight();
        float propertyEnd = 0f;
        float propertyStart = -(h/2 - (float)card.getHeight()/2);
        String propertyName = "translationY";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(card, propertyName, propertyStart, propertyEnd);
        objectAnimator.setDuration(500);
        objectAnimator.setRepeatCount(0);
        //objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimator.start();
    }

}
