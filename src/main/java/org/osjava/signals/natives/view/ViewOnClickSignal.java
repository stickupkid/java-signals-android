package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl1;

import android.view.View;
import android.view.View.OnClickListener;

public class ViewOnClickSignal extends NativeSignalImpl1<View> {

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnClickSignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnClickSignalImpl
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnClickSignal}
	 */
	public static ViewOnClickSignal newInstance(final View target) {
		return new ViewOnClickSignal(target);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeTargetListener() {
		View view = getTarget();
		if (null != view)
			view.setOnClickListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		View view = getTarget();
		if (null != view)
			view.setOnClickListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnClickListener {

		@Override
		public void onClick(final View v) {
			try {
				dispatch(v);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
}
