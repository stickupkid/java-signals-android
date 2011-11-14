package org.osjava.signals.natives.view.adapter;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl4;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class AdapterViewOnItemClickSignal extends NativeSignalImpl4<AdapterView<?>, View, Integer, Long> {

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 */
	private AdapterViewOnItemClickSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private AdapterViewOnItemClickSignal(final AdapterView<?> target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of AdapterViewOnItemClickSignal
	 * 
	 * @return {@link AdapterViewOnItemClickSignal}
	 */
	@SuppressWarnings("unchecked")
	public static AdapterViewOnItemClickSignal newInstance() {
		return new AdapterViewOnItemClickSignal();
	}

	/**
	 * Create a newInstance of AdapterViewOnItemClickSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link AdapterViewOnItemClickSignal}
	 */
	public static AdapterViewOnItemClickSignal newInstance(final AdapterView<?> target) {
		return new AdapterViewOnItemClickSignal(target);
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
			view.setOnItemClickListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			try {
				dispatch(parent, view, position, id);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
}
