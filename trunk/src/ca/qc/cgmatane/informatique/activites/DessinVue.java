package ca.qc.cgmatane.informatique.activites;

import android.content.Context;
import android.opengl.GLSurfaceView;

class DessinVue extends GLSurfaceView {

    public DessinVue(Context context){
        super(context);
        
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        // java.lang.IllegalArgumentException: No configs match configSpec 
        // at android.opengl.GLSurfaceView$BaseConfigChooser.chooseConfig(GLSurfaceView.java:863)
        // http://stackoverflow.com/questions/8199067/android-opengl-apps-stop-working-after-being-loaded-in-emulator
        
        
        // Render the view only when there is a change in the drawing data
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);        

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new Partie(context));
    }
}