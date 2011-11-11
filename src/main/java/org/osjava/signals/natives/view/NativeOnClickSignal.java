package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl1;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class NativeOnClickSignal extends NativeSignalImpl1<View> {

	private final static String TAG_NAME = NativeOnClickSignal.class.getSimpleName();

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 */
	private NativeOnClickSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private NativeOnClickSignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnClickSignalImpl
	 * 
	 * @return {@link NativeOnClickSignal}
	 */
	@SuppressWarnings("unchecked")
	public static NativeOnClickSignal newInstance() {
		return new NativeOnClickSignal();
	}

	/**
	 * Create a newInstance of NativeOnClickSignalImpl
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link NativeOnClickSignal}
	 */
	public static NativeOnClickSignal newInstance(final View target) {
		return new NativeOnClickSignal(target);
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
				Log.e(TAG_NAME, "Dispatch Error", t);
			}
		}
	}
}
