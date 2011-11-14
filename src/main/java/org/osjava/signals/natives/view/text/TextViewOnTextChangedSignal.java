package org.osjava.signals.natives.view.text;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl5;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TextViewOnTextChangedSignal extends
		NativeSignalImpl5<TextView, CharSequence, Integer, Integer, Integer> {

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 */
	private TextViewOnTextChangedSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private TextViewOnTextChangedSignal(final TextView target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of TextViewOnTextChangedSignal
	 * 
	 * @return {@link TextViewOnTextChangedSignal}
	 */
	@SuppressWarnings("unchecked")
	public static TextViewOnTextChangedSignal newInstance() {
		return new TextViewOnTextChangedSignal();
	}

	/**
	 * Create a newInstance of TextViewOnTextChangedSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link TextViewOnTextChangedSignal}
	 */
	public static TextViewOnTextChangedSignal newInstance(final TextView target) {
		return new TextViewOnTextChangedSignal(target);
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
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			try {
				dispatch(getTarget(), s, start, before, count);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
}
