package org.osjava.signals.natives.view;

import org.osjava.signals.natives.NativeAPIAdapterSignal.NativeAPIAdapterDelegate;
import org.osjava.signals.natives.NativeAPIAdapterSignal.NativeAPIAdapterSignal2;

import android.view.View;

public class ViewOnViewAttachedToWindowSignal extends NativeAPIAdapterSignal2<View, View> {

	private final static String ADAPTER_NAME = "android.view.View$OnAttachStateChangeListener";

	private final static String LISTENER_NAME = "addOnAttachStateChangeListener";

	private final static String METHOD_NAME = "onViewAttachedToWindow";

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnViewAttachedToWindowSignal(View target) {

		final Class<?>[] parameterTypes = { View.class };

		init(ADAPTER_NAME, LISTENER_NAME, parameterTypes, new TargetListener(), METHOD_NAME);
		setTarget(target);
	}

	/**
	 * Create a newInstance of ViewOnViewAttachedToWindowSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnViewAttachedToWindowSignal}
	 */
	public static ViewOnViewAttachedToWindowSignal newInstance(final View target) {
		return new ViewOnViewAttachedToWindowSignal(target);
	}

	private final class TargetListener implements NativeAPIAdapterDelegate {

		@SuppressWarnings("unused")
		public void onViewAttachedToWindow(View v) {

			try {
				dispatch(getTarget(), v);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		@SuppressWarnings("unused")
		public void onViewDetachedFromWindow(View v) {

		}
	}
}
