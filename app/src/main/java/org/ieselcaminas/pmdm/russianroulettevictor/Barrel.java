package org.ieselcaminas.pmdm.russianroulettevictor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Barrel extends LinearLayout{

    public static final int NUM_BULLETS = 6;
    private Button[] buttons;
    private Button bulletButton;

    private boolean gameOver;

    private FireListener fireListener;

    public interface FireListener {
        void fire(boolean bang);
    }

    public Barrel(Context context) {
        super(context);
        createButtons();
        insertBulletIntoTheBarrel();
    }

    public Barrel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createButtons();
        insertBulletIntoTheBarrel();
    }

    public Barrel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createButtons();
        insertBulletIntoTheBarrel();
    }

    public void setFireListener(FireListener fireListener) {
        this.fireListener = fireListener;
    }

    private void createButtons() {
        buttons = new Button[NUM_BULLETS];

        for (int i=0; i<buttons.length; i++) {

            buttons[i] = new Button(
                    getContext(),
                    null,
                    android.R.attr.buttonStyleSmall);
            buttons[i].setText("" + (i + 1));
            addView(buttons[i]);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (gameOver) return;
                    if (fireListener == null) return;

                    v.setEnabled(false);
                    if (v == bulletButton) {
                        //bang();
                        fireListener.fire(true);
                        gameOver = true;
                    } else {
                        fireListener.fire(false);
                    }
                }
            });
        }
    }

    private void insertBulletIntoTheBarrel() {
        int bulletIndex = (int) (Math.random() * NUM_BULLETS);
        bulletButton = buttons[bulletIndex];
        gameOver = false;
    }

    public void reset() {
        for(Button b: buttons) {
            b.setEnabled(true);
        }
        insertBulletIntoTheBarrel();
        gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

}
