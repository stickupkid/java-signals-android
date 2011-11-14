package org.osjava.signals.natives.view.text;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl3;

import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class TextViewOnEditorActionSignal extends NativeSignalImpl3<TextView, Integer, KeyEvent> {

	private final static boolean DEFAULT_CONSUMED_VALUE = true;

	private final TargetListener _listener = new TargetListener();

	private boolean _consumed = DEFAULT_CONSUMED_VALUE;

	/**
	 * Private constructor
	 */
	private TextViewOnEditorActionSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private TextViewOnEditorActionSignal(final TextView target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of TextViewOnEditorActionListener
	 * 
	 * @return {@link TextViewOnEditorActionSignal}
	 */
	@SuppressWarnings("unchecked")
	public static TextViewOnEditorActionSignal newInstance() {
		return new TextViewOnEditorActionSignal();
	}

	/**
	 * Create a newInstance of TextViewOnEditorActionListener
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link TextViewOnEditorActionSignal}
	 */
	public static TextViewOnEditorActionSignal newInstance(final TextView target) {
		return new TextViewOnEditorActionSignal(target);
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
	public void setConsumed(final boolean value) {
		_consumed = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeTargetListener() {
		TextView view = getTarget();
		if (null != view)
			view.setOnEditorActionListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		TextView view = getTarget();
		if (null != view)
			view.setOnEditorActionListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnEditorActionListener {

		@Override
		public boolean onEditorAction(final TextView v, final int actionId, final KeyEvent event) {
			setConsumed(DEFAULT_CONSUMED_VALUE);

			try {
				dispatch(v, actionId, event);
			} catch (Throwable t) {
				t.printStackTrace();
				setConsumed(false);
			}

			return isConsumed();
		}
	}
}
