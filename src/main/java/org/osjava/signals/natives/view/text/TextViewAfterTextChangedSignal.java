package org.osjava.signals.natives.view.text;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl2;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TextViewAfterTextChangedSignal extends NativeSignalImpl2<TextView, Editable> {

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 */
	private TextViewAfterTextChangedSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private TextViewAfterTextChangedSignal(final TextView target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of TextViewAfterTextChangedSignal
	 * 
	 * @return {@link TextViewAfterTextChangedSignal}
	 */
	@SuppressWarnings("unchecked")
	public static TextViewAfterTextChangedSignal newInstance() {
		return new TextViewAfterTextChangedSignal();
	}

	/**
	 * Create a newInstance of TextViewOnTextChangedSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link TextViewOnTextChangedSignal}
	 */
	public static TextViewAfterTextChangedSignal newInstance(final TextView target) {
		return new TextViewAfterTextChangedSignal(target);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeTargetListener() {
		TextView view = getTarget();
		if (null != view)
			view.addTextChangedListener(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerTargetListener() {
		TextView view = getTarget();
		if (null != view)
			view.addTextChangedListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements TextWatcher {

		@Override
		public void afterTextChanged(Editable s) {
			try {
				dispatch(getTarget(), s);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
	}
}
