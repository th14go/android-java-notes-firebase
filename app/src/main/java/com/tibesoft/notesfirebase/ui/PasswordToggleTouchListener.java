package com.tibesoft.notesfirebase.ui;

import android.graphics.drawable.Drawable;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class PasswordToggleTouchListener implements View.OnTouchListener {
    private EditText editText;

    public PasswordToggleTouchListener(EditText editText) {
        this.editText = editText;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            Drawable drawable = editText.getCompoundDrawables()[DRAWABLE_RIGHT];
            if (drawable != null && event.getRawX() >= (editText.getRight() - drawable.getBounds().width())) {
                if (editText.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    // Mostrar senha
                    editText.setTransformationMethod(null);
                    editText.setSelection(editText.getText().length());
                    editText.setSelected(true);
                } else {
                    // Ocultar senha
                    editText.setTransformationMethod(new PasswordTransformationMethod());
                    editText.setSelection(editText.getText().length());
                    editText.setSelected(false);
                }
                return true;
            }
        }
        return false;
    }
}
