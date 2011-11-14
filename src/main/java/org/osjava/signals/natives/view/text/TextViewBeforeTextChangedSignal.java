package org.osjava.signals.natives.view.text;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl5;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TextViewBeforeTextChangedSignal extends
		NativeSignalImpl5<TextView, CharSequence, Integer, Integer, Integer> {

	private final TargetListener _listener = new TargetListener();

	/**
	 * Private constructor
	 */
	private TextViewBeforeTextChangedSignal() {
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private TextViewBeforeTextChangedSignal(final TextView target) {
		setTarget(target);
	}

	/**
	 * Create a newInstance of TextViewBeforeTextChangedSignal
	 * 
	 * @return {@link TextViewBeforeTextChangedSignal}
	 */
	@SuppressWarnings("unchecked")
	public static TextViewBeforeTextChangedSignal newInstance() {
		return new TextViewBeforeTextChangedSignal();
	}

	/**
	 * Create a newInstance of TextViewBeforeTextChangedSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @return {@link TextViewBeforeTextChangedSignal}
	 */
	public static TextViewBeforeTextChangedSignal newInstance(final TextView target) {
		return new TextViewBeforeTextChangedSignal(target);
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
			try {
				dispatch(getTarget(), s, start, count, after);
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
	}
}
