package ca.qc.cgmatane.informatique.activites;

import android.content.Context;
import android.opengl.GLSurfaceView;

class DessinVue extends GLSurfaceView {

    public DessinVue(Context context){
        super(context);
        

        setEGLContextClientVersion(2);
        setRenderer(new Partie(context));
    }
}