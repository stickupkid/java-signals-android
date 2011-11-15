package org.osjava.signals.natives.view;

import org.osjava.signals.natives.NativeAPIAdapterSignal.NativeAPIAdapterDelegate;
import org.osjava.signals.natives.NativeAPIAdapterSignal.NativeAPIAdapterSignal5;

import android.view.View;

public class ViewOnLayoutChangeSignal extends
		NativeAPIAdapterSignal5<View, Integer, Integer, Integer, Integer> {

	private final static String ADAPTER_NAME = "android.view.View$OnLayoutChangeListener";

	private final static String LISTENER_NAME = "addOnLayoutChangeListener";

	private final static String METHOD_NAME = "onLayoutChange";

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnLayoutChangeSignal(View target) {

		final Class<?>[] parameterTypes =
				{ View.class, Integer.class, Integer.class, Integer.class, Integer.class,
						Integer.class, Integer.class, Integer.class, Integer.class };

		init(ADAPTER_NAME, LISTENER_NAME, parameterTypes, new TargetListener(), METHOD_NAME);
		setTarget(target);
	}

	/**
	 * Create a newInstance of ViewOnLayoutChangeSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnLayoutChangeSignal}
	 */
	public static ViewOnLayoutChangeSignal newInstance(final View target) {
		return new ViewOnLayoutChangeSignal(target);
	}

	private final class TargetListener implements NativeAPIAdapterDelegate {

		@SuppressWarnings("unused")
		public void
				onLayoutChange(View view, Integer left, Integer top, Integer right, Integer bottom,
						Integer oldLeft, Integer oldTop, Integer oldRight, Integer oldBottom) {

			try {
				dispatch(view, left, top, right, bottom);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
}
