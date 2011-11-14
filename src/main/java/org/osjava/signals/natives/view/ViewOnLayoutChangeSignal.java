package org.osjava.signals.natives.view;

import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl3;

import android.view.View;

public class ViewOnLayoutChangeSignal extends NativeSignalImpl3<View, Rectangle, Rectangle> {

	private final static String CLASS_METHOD_NAME = "setOnLayoutChangeListener";

	private final TargetListener _listener = new TargetListener();

	private Method _method;

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnLayoutChangeSignal(View target) {
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

	/**
	 * {@inheritDoc}
	 * 
	 * @return True if the native signal is available
	 */
	@Override
	public boolean isAvailable() {
		return null != _method;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeTargetListener() {
		View view = getTarget();
		if (null != view && null != _method) {
			try {
				if (_method.isAccessible())
					_method.setAccessible(true);

				_method.invoke(view);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		View view = getTarget();
		if (null != view) {
			if (null == _method) {
				try {
					final Method[] methods = view.getClass().getMethods();
					for (final Method method : methods) {
						if (method.getName().equals(CLASS_METHOD_NAME)) {
							// We know the contact so we can skip the method
							// name
							_method = method;
							break;
						}
					}
				} catch (SecurityException e) {
					throw new RuntimeException(e);
				}
			}

			// If we've got the method invoke on it
			if (null != _method) {
				try {
					if (_method.isAccessible())
						_method.setAccessible(true);

					_method.invoke(view, _listener);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * Private target listener class which implements the
	 */
	private final class TargetListener {

		@SuppressWarnings("unused")
		public void onLayoutChange(final View v, final int left, final int top, final int right,
				final int bottom, final int oldLeft, final int oldTop, final int oldRight,
				final int oldBottom) {
			try {
				dispatch(v, new Rectangle(left, top, right, bottom), new Rectangle(oldLeft, oldTop,
						oldRight, oldBottom));
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
}
