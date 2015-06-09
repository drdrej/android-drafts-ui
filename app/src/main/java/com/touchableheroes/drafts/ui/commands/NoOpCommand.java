package com.touchableheroes.drafts.ui.commands;

import com.touchableheroes.drafts.Command;

/**
 * Created by asiebert on 22.07.14.
 */
public class NoOpCommand<Void> extends Command {

    @Override
    public Void exec(Object... values) {

        return null;
    }
}
