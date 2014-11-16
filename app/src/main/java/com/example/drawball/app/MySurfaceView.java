package com.example.drawball.app;

/**
 * Created by wl on 2014/11/14.
 */

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MySurfaceView extends GLSurfaceView
{

    private final MyGLRenderer mRenderer;
    private final float TOUCH_SCALE_FACTOR = 90.0f / 320;
    private float mangle;
    private float mPreviousX;
    private float mPreviousY;

    public MySurfaceView (Context context, AttributeSet attrs)
    {
        super (context, attrs);

        // Create an OpenGL ES 2.0 context.
        //setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer (true);
        setRenderer (mRenderer);

        // Render the view only when there is a change in the drawing data
        // setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setRenderMode (GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void GetInfo (float bmpw, float bmph, double sfs[][])
    {
        mRenderer.GetInfo (bmpw, bmph, sfs);

        requestRender ();
        //requestRender();
    }

    @Override
    public boolean onTouchEvent (MotionEvent e)
    {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, we are only
        // interested in events where the touch position changed.

        float x = e.getX ();
        float y = e.getY ();

        switch (e.getAction ())
        {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight () / 2)
                {
                    dx = dx * -1;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth () / 2)
                {
                    dy = dy * -1;
                }
                mangle = mRenderer.getAngle () + ((dx + dy) * TOUCH_SCALE_FACTOR);
                mRenderer.setAngle (mangle);  // = 180.0f / 320
                requestRender ();
        }


        mPreviousX = x;
        mPreviousY = y;
        Log.v ("TOUCH", "" + mangle);
        return true;
    }
}

