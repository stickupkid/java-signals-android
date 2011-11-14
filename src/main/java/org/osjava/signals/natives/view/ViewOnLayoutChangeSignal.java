package org.osjava.signals.natives.view;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl5;

import android.view.View;

public class ViewOnLayoutChangeSignal extends
		NativeSignalImpl5<View, Integer, Integer, Integer, Integer> {

	private final static String CLASS_NAME = "android.view.View$OnLayoutChangeListener";

	private final static String CLASS_SUBSCRIBER_NAME = "addOnLayoutChangeListener";

	private final static String CLASS_LISTENER_NAME = "onLayoutChange";

	private TargetListener _listener = new TargetListener();

	private Method _method;

	private Method _delegateMethod;

	private Class<?> _interface;

	private Object _proxy;

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnLayoutChangeSignal(View target) {
		init();
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
		return null != _interface && null != _delegateMethod && null != _proxy && null != _method;
	}

	/**
	 * Initialise method for the signal
	 */
	protected void init() {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();

		try {
			_interface = classLoader.loadClass(CLASS_NAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		final Class<?>[] parameterTypes = { View.class, Integer.class, Integer.class,
				Integer.class, Integer.class, Integer.class, Integer.class, Integer.class,
				Integer.class };

		try {
			_delegateMethod = _listener.getClass().getMethod(CLASS_LISTENER_NAME, parameterTypes);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}

		final Class<?>[] proxyInterfaces = { _interface };
		_proxy = Proxy.newProxyInstance(classLoader, proxyInterfaces, new TargetProxyListener(
				_listener, _delegateMethod));
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

				Object[] arguments = { null };
				_method.invoke(view, arguments);
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
		if (null != _interface && null != _proxy) {
			View view = getTarget();
			if (null != view) {
				if (null == _method) {
					try {
						final Method[] methods = view.getClass().getMethods();
						for (final Method method : methods) {
							if (method.getName().equals(CLASS_SUBSCRIBER_NAME)) {
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

				if (null != _method) {
					try {
						Object[] arguments = { _proxy };
						_method.invoke(view, arguments);
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
	}

	/**
	 * Private target listener class which implements the
	 */
	private final class TargetProxyListener implements InvocationHandler {

		private final TargetListener _delegate;

		private final Method _delegateMethod;

		public TargetProxyListener(TargetListener delegate, Method delegateMethod) {
			assert null != delegate : "TargetListener can not be null";
			assert null != delegateMethod : "DelegateMethod can not be null";

			_delegate = delegate;
			_delegateMethod = delegateMethod;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
			if (null != _delegateMethod) {
				if (method.getName().equals(CLASS_LISTENER_NAME)) {
					try {
						if (!_delegateMethod.isAccessible())
							_delegateMethod.setAccessible(true);

						return _delegateMethod.invoke(_delegate, arguments);
					} catch (InvocationTargetException e) {
						throw e.getTargetException();
					}
				}
			}

			throw new InternalError("Unexpected method dispatched: " + method);
		}
	}

	private final class TargetListener {

		@SuppressWarnings("unused")
		public void onLayoutChange(View view, Integer left, Integer top, Integer right,
				Integer bottom, Integer oldLeft, Integer oldTop, Integer oldRight, Integer oldBottom) {

			try {
				ViewOnLayoutChangeSignal.this.dispatch(view, left, top, right, bottom);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
}
