/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gms.samples.vision.ocrreader;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.android.gms.samples.vision.ocrreader.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.text.Text;

/**
 * Graphic instance for rendering TextBlock position, size, and ID within an associated graphic
 * overlay view.
 */
public class OcrGraphic extends GraphicOverlay.Graphic {

    private int id;

    private static final int TEXT_COLOR = Color.WHITE;
    private static final int SWITCH_CODE_COLOR = Color.YELLOW;
    private static final int FOR_CODE_COLOR = Color.RED;
    private static final int IF_CODE_COLOR = Color.BLUE;
    private static final int VARIABLE_DECLARATION_CODE_COLOR = Color.GREEN;


    private static Paint rectPaint;
    private static Paint textPaint;
    private Text text;
    public boolean SwitchCodeFound = true;
    public boolean ForCodeFound = true;
    public boolean IfCodeFound = true;
    public boolean VariableCodeFound = true;


    public OcrGraphic(GraphicOverlay overlay, Text text, boolean switchCodeFound, boolean forCodeFound, boolean ifCodeFound, boolean variableCodeFound) {
        super(overlay);

        if (text != null) {
            this.text = text;
            this.SwitchCodeFound = switchCodeFound;
            this.ForCodeFound = forCodeFound;
            this.IfCodeFound = ifCodeFound;
            this.VariableCodeFound = variableCodeFound;

            if (rectPaint == null) {
                rectPaint = new Paint();
                rectPaint.setColor(TEXT_COLOR);
                rectPaint.setStyle(Paint.Style.STROKE);
                rectPaint.setStrokeWidth(4.0f);
            }

            if (textPaint == null) {
                textPaint = new Paint();
                textPaint.setColor(TEXT_COLOR);
                textPaint.setTextSize(54.0f);
            }
            // Redraw the overlay, as this graphic has been added.
            postInvalidate();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Text getTextBlock() {
        return text;
    }

    /**
     * Checks whether a point is within the bounding box of this graphic.
     * The provided point should be relative to this graphic's containing overlay.
     *
     * @param x An x parameter in the relative context of the canvas.
     * @param y A y parameter in the relative context of the canvas.
     * @return True if the provided point is contained within this graphic's bounding box.
     */
    public boolean contains(float x, float y) {
        // TODO: Check if this graphic's text contains this point.
        return false;
    }

    /**
     * Draws the text block annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        // TODO: Draw the text onto the canvas.
        if (text == null) {
            return;
        }

        // Draws the bounding box around the TextBlock.
        if (SwitchCodeFound) {
            rectPaint.setColor(SWITCH_CODE_COLOR);
        } else if (ForCodeFound) {
            rectPaint.setColor(FOR_CODE_COLOR);
        } else if (IfCodeFound) {
            rectPaint.setColor(IF_CODE_COLOR);
        } else if (VariableCodeFound) {
            rectPaint.setColor(VARIABLE_DECLARATION_CODE_COLOR);
        } else {
            rectPaint.setColor(TEXT_COLOR);
        }

        text.getBoundingBox().right += 100;
        RectF rect = new RectF(text.getBoundingBox());
        rect.top +=160;
        rect.bottom +=160;

        rect = translateRect(rect);
        canvas.drawRect(rect, rectPaint);

        // Break the text into multiple lines and draw each one according to its own bounding box.
//        List<? extends Text> textComponents = text.getComponents();

        //gets the first word by finding the first gap
//        int gap = text.getValue().indexOf(' ');
//        String word = text.getValue().substring(0, gap);


//        for (Text currentText : textComponents) {
//            float left = translateX(currentText.getBoundingBox().left);
//            float top = translateY(currentText.getBoundingBox().top);
//            canvas.drawText(currentText.getValue(), left, top, textPaint);
//        }
    }
}
