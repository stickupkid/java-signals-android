package org.osjava.signals.natives.view;

import java.util.ArrayList;
import java.util.List;

import org.osjava.signals.natives.NativeAPIAdapterSignal.NativeAPIAdapterDelegate;
import org.osjava.signals.natives.NativeAPIAdapterSignal.NativeAPIAdapterSignal2;

import android.view.View;

public class ViewOnSystemUiVisibilityChangeSignal extends NativeAPIAdapterSignal2<View, Integer> {

	private final static String ADAPTER_NAME =
			"android.view.View$OnSystemUiVisibilityChangeListener";

	private final static String LISTENER_NAME = "setOnSystemUiVisibilityChangeListener";

	private final static List<String> METHOD_NAME = new ArrayList<String>();

	static {
		METHOD_NAME.add("onSystemUiVisibilityChange");
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnSystemUiVisibilityChangeSignal(View target) {

		final Class<?>[] parameterTypes = { Integer.class };

		createProxy(ADAPTER_NAME, LISTENER_NAME, parameterTypes, new TargetListener(), METHOD_NAME);
		setTarget(target);
	}

	/**
	 * Create a newInstance of ViewOnSystemUiVisibilityChangeSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnSystemUiVisibilityChangeSignal}
	 */
	public static ViewOnSystemUiVisibilityChangeSignal newInstance(final View target) {
		return new ViewOnSystemUiVisibilityChangeSignal(target);
	}

	private final class TargetListener implements NativeAPIAdapterDelegate {

		@SuppressWarnings("unused")
		public void onSystemUiVisibilityChange(Integer visibility) {

			try {
				dispatch(getTarget(), visibility);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
}
