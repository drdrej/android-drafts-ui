package com.touchableheroes.drafts.ui;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ComponentQueryEngine {

	private Activity activity;

    public ComponentQueryEngine(final Activity activity) {
		this.activity = activity;
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T findView(final int id, final Class<T> ofType) {
		final View view = activity.findViewById(id);
		if (view == null) {
			throw new IllegalStateException("Can't find view with passed [id = " + id
					+ "] in activity [class = " + this.activity.getClass());
		}

		if (!ofType.isInstance(view))
			throw new IllegalStateException("View of [type = "
					+ view.getClass().getName() + "] with passed [id = " + id
					+ "] is not of passed [type = " + ofType.getName());

		return (T) view;
	}
	
	public Button findButton(final int id) {
		return (Button) findView(id, Button.class);
	}
	
	public TextView findText(final int id) {
		return (TextView) findView(id, TextView.class);
	}

}
