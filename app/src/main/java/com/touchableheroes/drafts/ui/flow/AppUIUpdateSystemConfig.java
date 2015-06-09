package com.touchableheroes.drafts.ui.flow;

import android.view.View;

import com.touchableheroes.drafts.ui.MainActivity;
import com.touchableheroes.drafts.ui.R;
import com.touchableheroes.drafts.ui.events.ExampleEventHandler;

/**
 * Created by asiebert on 10.09.14.
 */
public class AppUIUpdateSystemConfig {

    public void config() {
        app()
                .when(AppLoaded.class)
                .then(ExampleEventHandler.class);

        view(R.id.progress_horizontal)
                .when(View.OnClickListener.class)
                .forwardTo(MainActivity.class);


    }

    private EventStart activity() {
        return null;
    }

    private EventStart app() {
        return null;
    }


    protected EventStart view(int id) {
        return null;
    }
}
