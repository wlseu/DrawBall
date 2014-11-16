package com.example.drawball.app;

/**
 * Created by wl on 2014/11/14.
 */


import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

class MyGLRenderer implements GLSurfaceView.Renderer
{
    private boolean mTranslucentBackground;
   // private Square mSquare;
    private float mTransY;
    private float mAngle;
    private float bmpw, bmph;
    private double sfs[][];
    private Sphere msphere;

    public MyGLRenderer (boolean useTranslucentBackground)
    {
        mTranslucentBackground = useTranslucentBackground;
     //   mSquare = new Square ();
        msphere = new Sphere ();
    }

    public void onDrawFrame (GL10 gl)
    {
        gl.glClear (GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor (0.0f, 0.0f, 0.0f, 1.0f);
        gl.glMatrixMode (GL10.GL_MODELVIEW);
        gl.glLoadIdentity ();
        gl.glTranslatef (0.0f, (float) Math.sin (mTransY), -7.0f);

        gl.glEnableClientState (GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState (GL10.GL_COLOR_ARRAY);
        //  gl.glRotatef(mAngle, 0.0f, 0.0f, 1.0f);
        gl.glRotatef (mAngle, 0.0f, 1.0f, 0.0f);
        gl.glRotatef (mAngle, 1.0f, 0.0f, 0.0f);
        // 清除深度和颜色缓存
//        gl.glClearColor(0f, 0f, 0f, 0.5f);
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//        gl.glMatrixMode(GL10.GL_MODELVIEW);  //设置矩阵模式
        //  draw(gl);
   //     mSquare.draw (gl);
        //   gl.glTranslatef (0.0f,0.0f,-5.0f);
        //   mydrawHight.draw (gl);
        //  myobject.draw (gl);
        msphere.draw (gl);

    }

    public void onSurfaceChanged (GL10 gl, int width, int height)
    {
        gl.glViewport (0, 0, width, height);

        float ratio = (float) width / height;
        gl.glMatrixMode (GL11.GL_PROJECTION);
        gl.glLoadIdentity ();
        //gl.gluLookAt();
        gl.glFrustumf (-ratio, ratio, -1, 1, 1, 10);
    }

    public void onSurfaceCreated (GL10 gl, EGLConfig config)
    {

        gl.glDisable (GL11.GL_DITHER);

        gl.glHint (GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);

        if (mTranslucentBackground)
        {
            gl.glClearColor (0, 0, 0, 0);
        } else
        {
            gl.glClearColor (1, 1, 1, 1);
        }

        gl.glEnable (GL11.GL_CULL_FACE);
        gl.glShadeModel (GL11.GL_SMOOTH);
        gl.glEnable (GL11.GL_DEPTH_TEST);
//        //启用深度测试
//        gl.glEnable(GL10.GL_DEPTH_TEST);
//        // 所做深度测试的类型
//        gl.glDepthFunc(GL10.GL_DITHER);
//        //黑色背景
//        gl.glClearColor(0f, 0f, 0f, 0.5f);
//        //启用阴影平滑
//        gl.glShadeModel(GL10.GL_SMOOTH);
//        //清除深度缓存
//        gl.glClearDepthf(1.0f);
//
//        gl.glEnable(GL10.GL_TEXTURE_2D);
//        //告诉系统对透视进行修正
//        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
    }

    public void GetInfo (float bmpw2, float bmph2, double sfs2[][])
    {
        //    mydrawHight.object_init (bmpw2,bmph2,sfs2);
     //   mSquare.GetInfo (bmpw2, bmph2, sfs2);
        // myobject.GetInfo (bmpw2,bmph2,sfs2);
        Log.v ("bmpw2", "" + bmpw2);
        Log.v ("bmph2", "" + bmph2);

    }

    public float getAngle ()
    {
        return mAngle;
    }

    /**
     * Sets the rotation angle of the triangle shape (mTriangle).
     */
    public void setAngle (float angle)
    {
        mAngle = angle;
    }
}
