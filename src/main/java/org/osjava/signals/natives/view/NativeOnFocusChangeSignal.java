package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl2;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;

public class NativeOnFocusChangeSignal extends NativeSignalImpl2<View, Boolean> {

	private final static String TAG_NAME = NativeOnFocusChangeSignal.class.getSimpleName();

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 */
	private NativeOnFocusChangeSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private NativeOnFocusChangeSignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnFocusChangeSignal
	 * 
	 * @return {@link NativeOnFocusChangeSignal}
	 */
	@SuppressWarnings("unchecked")
	public static NativeOnFocusChangeSignal newInstance() {
		return new NativeOnFocusChangeSignal();
	}

	/**
	 * Create a newInstance of NativeOnFocusChangeSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link NativeOnFocusChangeSignal}
	 */
	public static NativeOnFocusChangeSignal newInstance(final View target) {
		return new NativeOnFocusChangeSignal(target);
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
				Log.e(TAG_NAME, "Dispatch Error", t);
			}
		}
	}
}
