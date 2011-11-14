package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl3;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;

public class ViewOnCreateContextMenuSignal extends
		NativeSignalImpl3<View, ContextMenu, ContextMenuInfo> {

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 */
	private ViewOnCreateContextMenuSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private ViewOnCreateContextMenuSignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnCreateContextMenuSignal
	 * 
	 * @return {@link ViewOnCreateContextMenuSignal}
	 */
	@SuppressWarnings("unchecked")
	public static ViewOnCreateContextMenuSignal newInstance() {
		return new ViewOnCreateContextMenuSignal();
	}

	/**
	 * Create a newInstance of NativeOnCreateContextMenuSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link ViewOnCreateContextMenuSignal}
	 */
	public static ViewOnCreateContextMenuSignal newInstance(final View target) {
		return new ViewOnCreateContextMenuSignal(target);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeTargetListener() {
		View view = getTarget();
		if (null != view)
			view.setOnCreateContextMenuListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		View view = getTarget();
		if (null != view)
			view.setOnCreateContextMenuListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements OnCreateContextMenuListener {

		@Override
		public void onCreateContextMenu(final ContextMenu menu, final View v,
				final ContextMenuInfo menuInfo) {

			try {
				dispatch(v, menu, menuInfo);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
}
