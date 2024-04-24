package com.github.Sangarru11.ProyectoFinal.View;

import java.io.IOException;
import com.github.Sangarru11.ProyectoFinal.App;

public abstract class Controller {
    App app;
    public void setApp(App app){
        this.app=app;
    }

    public abstract void onOpen(Object input) throws IOException;
    public abstract void onClose(Object output);
}
