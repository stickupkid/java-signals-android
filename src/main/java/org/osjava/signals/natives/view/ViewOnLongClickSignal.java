package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl1;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class ViewOnLongClickSignal extends NativeSignalImpl1<View> {

	private final static String TAG_NAME = ViewOnLongClickSignal.class.getSimpleName();

	private final static boolean DEFAULT_CONSUMED_VALUE = true;

	private final TargetListener _listener = new TargetListener();

	private boolean _consumed = DEFAULT_CONSUMED_VALUE;

	/**
	 * Private constructor
	 */
	private ViewOnLongClickSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnLongClickSignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnLongClickSignal
	 * 
	 * @return {@link ViewOnLongClickSignal}
	 */
	@SuppressWarnings("unchecked")
	public static ViewOnLongClickSignal newInstance() {
		return new ViewOnLongClickSignal();
	}

	/**
	 * Create a newInstance of NativeOnLongClickSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnLongClickSignal}
	 */
	public static ViewOnLongClickSignal newInstance(final View target) {
		return new ViewOnLongClickSignal(target);
	}

	/**
	 * Is the long click consumed or not
	 * 
	 * @return boolean True if the Long click is consumed
	 */
	public boolean isConsumed() {
		return _consumed;
	}

	/**
	 * Set the consumed value of the click
	 * 
	 * @param value
	 *            True if the long click is consumed.
	 */
	public void setConsumed(boolean value) {
		_consumed = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeTargetListener() {
		getTarget().setOnLongClickListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		// Target could be null when we register it.
		View view = getTarget();
		if (null != view)
			view.setOnLongClickListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnLongClickListener {

		@Override
		public boolean onLongClick(final View v) {
			setConsumed(DEFAULT_CONSUMED_VALUE);

			try {
				dispatch(v);
			} catch (Throwable t) {
				Log.e(TAG_NAME, "Dispatch Error", t);
			}

			return isConsumed();
		}
	}
}
