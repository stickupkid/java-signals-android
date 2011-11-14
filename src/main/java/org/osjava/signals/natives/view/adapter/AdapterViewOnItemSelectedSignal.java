package org.osjava.signals.natives.view.adapter;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl4;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class AdapterViewOnItemSelectedSignal extends
		NativeSignalImpl4<AdapterView<?>, View, Integer, Long> {

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private AdapterViewOnItemSelectedSignal(final AdapterView<?> target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of AdapterViewOnItemSelectedSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link AdapterViewOnItemSelectedSignal}
	 */
	public static AdapterViewOnItemSelectedSignal newInstance(final AdapterView<?> target) {
		return new AdapterViewOnItemSelectedSignal(target);
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
			view.setOnItemSelectedListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			try {
				dispatch(parent, view, position, id);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			try {
				dispatch(parent, null, null, null);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
}
