package org.osjava.signals.natives.view.adapter;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl4;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

public class AdapterViewOnItemLongClickSignal extends
		NativeSignalImpl4<AdapterView<?>, View, Integer, Long> {

	private final static boolean DEFAULT_CONSUMED_VALUE = true;

	private final TargetListener _listener = new TargetListener();

	private boolean _consumed = DEFAULT_CONSUMED_VALUE;

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private AdapterViewOnItemLongClickSignal(final AdapterView<?> target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of AdapterViewOnItemLongClickSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link AdapterViewOnItemLongClickSignal}
	 */
	public static AdapterViewOnItemLongClickSignal newInstance(final AdapterView<?> target) {
		return new AdapterViewOnItemLongClickSignal(target);
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
		AdapterView<?> view = getTarget();
		if (null != view)
			view.setOnItemClickListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		AdapterView<?> view = getTarget();
		if (null != view)
			view.setOnItemLongClickListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			setConsumed(DEFAULT_CONSUMED_VALUE);

			try {
				dispatch(parent, view, position, id);
			} catch (Throwable t) {
				setConsumed(false);
				throw new RuntimeException(t);
			}

			return isConsumed();
		}
	}
}
