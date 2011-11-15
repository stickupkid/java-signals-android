package org.osjava.signals.natives.view;

import org.osjava.signals.natives.NativeAPIAdapterSignal.NativeAPIAdapterDelegate;
import org.osjava.signals.natives.NativeAPIAdapterSignal.NativeAPIAdapterSignal2;

import android.view.View;

public class ViewOnViewDetachedFromWindowSignal extends NativeAPIAdapterSignal2<View, View> {

	private final static String ADAPTER_NAME = "android.view.View$OnAttachStateChangeListener";

	private final static String LISTENER_NAME = "addOnAttachStateChangeListener";

	private final static String METHOD_NAME = "onViewDetachedToWindow";

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnViewDetachedFromWindowSignal(View target) {

		final Class<?>[] parameterTypes = { View.class };

		init(ADAPTER_NAME, LISTENER_NAME, parameterTypes, new TargetListener(), METHOD_NAME);
		setTarget(target);
	}

	/**
	 * Create a newInstance of ViewOnViewDetachedFromWindowSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnViewDetachedFromWindowSignal}
	 */
	public static ViewOnViewDetachedFromWindowSignal newInstance(final View target) {
		return new ViewOnViewDetachedFromWindowSignal(target);
	}

	private final class TargetListener implements NativeAPIAdapterDelegate {

		@SuppressWarnings("unused")
		public void onViewAttachedToWindow(View v) {

		}

		@SuppressWarnings("unused")
		public void onViewDetachedFromWindow(View v) {
			try {
				dispatch(getTarget(), v);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
}
