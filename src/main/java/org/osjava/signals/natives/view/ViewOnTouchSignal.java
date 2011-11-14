package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl2;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class ViewOnTouchSignal extends NativeSignalImpl2<View, MotionEvent> {

	private final static boolean DEFAULT_CONSUMED_VALUE = true;

	private final TargetListener _listener = new TargetListener();

	private boolean _consumed = DEFAULT_CONSUMED_VALUE;

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnTouchSignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnLongClickSignalImpl
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnLongClickSignal}
	 */
	public static ViewOnTouchSignal newInstance(final View target) {
		return new ViewOnTouchSignal(target);
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
		View view = getTarget();
		if (null != view)
			view.setOnTouchListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		View view = getTarget();
		if (null != view)
			view.setOnTouchListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnTouchListener {

		@Override
		public boolean onTouch(final View v, final MotionEvent event) {
			setConsumed(DEFAULT_CONSUMED_VALUE);

			try {
				dispatch(v, event);
			} catch (Throwable t) {
				setConsumed(false);
				throw new RuntimeException(t);
			}

			return isConsumed();
		}
	}
}
