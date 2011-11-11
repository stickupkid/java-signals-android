package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl2;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class NativeOnTouchSignal extends NativeSignalImpl2<View, MotionEvent> {

	private final static String TAG_NAME = NativeOnTouchSignal.class.getSimpleName();

	private final static boolean INIT_CONSUMED_VALUE = true;

	private final TargetListener _listener = new TargetListener();

	private boolean _consumed = INIT_CONSUMED_VALUE;

	/**
	 * Private constructor
	 */
	private NativeOnTouchSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private NativeOnTouchSignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnTouchSignal
	 * 
	 * @return {@link NativeOnTouchSignal}
	 */
	@SuppressWarnings("unchecked")
	public static NativeOnTouchSignal newInstance() {
		return new NativeOnTouchSignal();
	}

	/**
	 * Create a newInstance of NativeOnLongClickSignalImpl
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link NativeOnLongClickSignal}
	 */
	public static NativeOnTouchSignal newInstance(final View target) {
		return new NativeOnTouchSignal(target);
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
		getTarget().setOnTouchListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		// Target could be null when we register it.
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
			setConsumed(INIT_CONSUMED_VALUE);

			try {
				dispatch(v, event);
			} catch (Throwable t) {
				Log.e(TAG_NAME, "Dispatch Error", t);
			}

			return isConsumed();
		}
	}
}
