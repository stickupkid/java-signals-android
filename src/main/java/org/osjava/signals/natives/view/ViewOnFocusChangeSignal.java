package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl2;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;

public class ViewOnFocusChangeSignal extends NativeSignalImpl2<View, Boolean> {

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 */
	private ViewOnFocusChangeSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnFocusChangeSignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnFocusChangeSignal
	 * 
	 * @return {@link ViewOnFocusChangeSignal}
	 */
	@SuppressWarnings("unchecked")
	public static ViewOnFocusChangeSignal newInstance() {
		return new ViewOnFocusChangeSignal();
	}

	/**
	 * Create a newInstance of NativeOnFocusChangeSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnFocusChangeSignal}
	 */
	public static ViewOnFocusChangeSignal newInstance(final View target) {
		return new ViewOnFocusChangeSignal(target);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeTargetListener() {
		getTarget().setOnClickListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		// Target could be null when we register it.
		View view = getTarget();
		if (null != view)
			view.setOnFocusChangeListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			try {
				dispatch(v, hasFocus);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
}
