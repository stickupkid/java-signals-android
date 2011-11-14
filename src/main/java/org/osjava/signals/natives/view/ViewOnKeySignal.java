package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl3;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;

public class ViewOnKeySignal extends NativeSignalImpl3<View, Integer, KeyEvent> {

	private final static boolean DEFAULT_CONSUMED_VALUE = true;

	private final TargetListener _listener = new TargetListener();

	private boolean _consumed = DEFAULT_CONSUMED_VALUE;

	/**
	 * Private constructor
	 */
	private ViewOnKeySignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnKeySignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnKeySignal
	 * 
	 * @return {@link ViewOnKeySignal}
	 */
	@SuppressWarnings("unchecked")
	public static ViewOnKeySignal newInstance() {
		return new ViewOnKeySignal();
	}

	/**
	 * Create a newInstance of NativeOnKeySignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnKeySignal}
	 */
	public static ViewOnKeySignal newInstance(final View target) {
		return new ViewOnKeySignal(target);
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
			view.setOnKeyListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnKeyListener {

		@Override
		public boolean onKey(final View v, final int keyCode, final KeyEvent event) {
			setConsumed(DEFAULT_CONSUMED_VALUE);

			try {
				dispatch(v, keyCode, event);
			} catch (Throwable t) {
				t.printStackTrace();
				setConsumed(false);
			}
			
			return isConsumed();
		}
	}
}
