package com.example.usgir.gametrial;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView,imageView2,imageView0;
    ObjectAnimator round,animatory;
    ValueAnimator animator;
    AnimatorSet set;
    float x,y;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView4);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        bitmapDrawable.setCircular(true);
        Button button = (Button)findViewById(R.id.button);
        imageView.setImageDrawable(bitmapDrawable);
        imageView0 = (ImageView)findViewById(R.id.imageView7);
        animator = new ValueAnimator().ofFloat(1200,-1200);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float xx = (float)animation.getAnimatedValue();
                if (xx<=x+25&&xx>=x-25)
                {
                    if (imageView.getY()>y-70)
                    {
                        //Toast.makeText(getApplicationContext(), "yup", Toast.LENGTH_SHORT).show();
                        animator.cancel();
                        imageView.setVisibility(View.GONE);
                        i=1;
                    }
                }
                else
                {
                    imageView0.setX(xx);
                }
            }
        });
        round = new ObjectAnimator().ofFloat(imageView,"rotation",0,360);
        round.setDuration(500);
        round.setRepeatCount(ValueAnimator.INFINITE);
        round.setRepeatMode(ValueAnimator.RESTART);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                x = imageView.getX();
                y = imageView.getY();
                i = 0;
                animatory = new ObjectAnimator().ofFloat(imageView,"Y",y,850);//bugs
                animatory.setRepeatMode(ValueAnimator.REVERSE);
                animatory.setRepeatCount(1);
                animatory.setDuration(500);
                set = new AnimatorSet();
                set.play(animatory);
                //Toast.makeText(getApplicationContext(),Float.toString(y),Toast.LENGTH_SHORT).show();
                animator.start();
                round.start();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i==0)
                {
                    set.start();
                }
            }
        });
    }
}
