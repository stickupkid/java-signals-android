package org.osjava.signals.natives.view;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl3;

import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;

public class NativeOnCreateContextMenuSignal extends
		NativeSignalImpl3<View, ContextMenu, ContextMenuInfo> {

	private final static String TAG_NAME = NativeOnCreateContextMenuSignal.class.getSimpleName();

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 */
	private NativeOnCreateContextMenuSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private NativeOnCreateContextMenuSignal(View target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of NativeOnCreateContextMenuSignal
	 * 
	 * @return {@link NativeOnCreateContextMenuSignal}
	 */
	@SuppressWarnings("unchecked")
	public static NativeOnCreateContextMenuSignal newInstance() {
		return new NativeOnCreateContextMenuSignal();
	}

	/**
	 * Create a newInstance of NativeOnCreateContextMenuSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link NativeOnCreateContextMenuSignal}
	 */
	public static NativeOnCreateContextMenuSignal newInstance(final View target) {
		return new NativeOnCreateContextMenuSignal(target);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeTargetListener() {
		getTarget().setOnLongClickListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		// Target could be null when we register it.
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
				Log.e(TAG_NAME, "Dispatch Error", t);
			}
		}
	}
}
