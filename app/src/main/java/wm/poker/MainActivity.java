package wm.poker;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageView card;
    ImageView card2;
    boolean isBack = true;
    boolean isBack2 = true;
    boolean isDeal = true;
    boolean isVisible = true;
    LinearLayout background;
    AccelerateDecelerateInterpolator accelerateDecelerateInterpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        card = (ImageView)findViewById(R.id.card_back);
        card2 = (ImageView)findViewById(R.id.card2_back);
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

    public void flip2(View v)
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
                if(isBack2)
                {
                    card2.setImageResource(R.drawable.card_front);
                    isBack2 = false;
                }
                else
                {
                    card2.setImageResource(R.drawable.card_back);
                    isBack2 = true;
                }
                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(600);
                fadeIn.setFillAfter(true);
                card2.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        card2.startAnimation(fadeOut);

    }

    public void dropCard(View v)
    {
        prepareObjectAnimator(accelerateDecelerateInterpolator);
        prepareObjectAnimator2(accelerateDecelerateInterpolator);
    }

    public void prepareObjectAnimator(TimeInterpolator timeInterpolator){
        float w = (float)background.getWidth();
        float h = (float)background.getHeight();
        float propertyEnd = 0f;
        float propertyStart = -(h/2 - (float)card.getHeight()/2);
        float propertyEndX = 0f;
        float propertyStartX = -(w/2 - (float)card.getWidth()/2);
        Log.i("MainActivity","Background_Width:"+w+"\nBackground_Height:"+h+"\nProp_Start&End:"+propertyStart+","+propertyEnd+"\n" +
                "PropXStart&End"+propertyStartX+","+propertyEndX);
        String propertyName = "translationY";
        String propertyNameX = "translationX";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(card, propertyName, propertyStart, propertyEnd);
        ObjectAnimator objectAnimatorX
                = ObjectAnimator.ofFloat(card, propertyNameX,propertyStartX,propertyEndX );
        objectAnimator.setDuration(500);
        objectAnimatorX.setDuration(500);
        objectAnimator.setRepeatCount(0);
        //objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimatorX.setInterpolator(timeInterpolator);
        objectAnimator.start();
        objectAnimatorX.start();

    }


    public void dropCard2(View v)
    {
        Button deal = (Button)v;
        Button drop = findViewById(R.id.dropButton);
        if(isDeal)
        {
            isDeal = false;
            deal.setText("OK");
            prepareObjectAnimator(accelerateDecelerateInterpolator);
            prepareObjectAnimator2(accelerateDecelerateInterpolator);
        }
        else
        {
            isDeal = true;
            deal.setText("DEAL");
            //prepareObjectAnimator2(accelerateDecelerateInterpolator);
        }
        if(isVisible)
        {
            drop.setVisibility(Button.INVISIBLE);
            isVisible = false;
        }
        else
        {
            drop.setVisibility(Button.VISIBLE);
            isVisible = true;
        }
    }

    public void prepareObjectAnimator2(TimeInterpolator timeInterpolator){
        float w = (float)background.getWidth();
        float h = (float)background.getHeight();
        float propertyEnd = 0f;
        float propertyStart = -(h/2 - (float)card2.getHeight()/2);
        float propertyEndX = 0f;
        float propertyStartX = (w/2 - (float)card2.getWidth()/2);
        Log.i("MainActivity","Background_Width:"+w+"\nBackground_Height:"+h+"\nProp_Start&End:"+propertyStart+","+propertyEnd+"\n" +
                "PropXStart&End"+propertyStartX+","+propertyEndX);
        String propertyName = "translationY";
        String propertyNameX = "translationX";
        ObjectAnimator objectAnimator
                = ObjectAnimator.ofFloat(card2, propertyName, propertyStart, propertyEnd);
        ObjectAnimator objectAnimatorX
                = ObjectAnimator.ofFloat(card2, propertyNameX,propertyStartX,propertyEndX );
        objectAnimator.setDuration(500);
        objectAnimatorX.setDuration(500);
        objectAnimator.setRepeatCount(0);
        //objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(timeInterpolator);
        objectAnimatorX.setInterpolator(timeInterpolator);
        objectAnimator.start();
        objectAnimatorX.start();

    }

}
