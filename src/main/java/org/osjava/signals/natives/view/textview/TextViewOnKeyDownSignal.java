package org.osjava.signals.natives.view.textview;

import org.osjava.signals.impl.NativeSignalImpl.NativeSignalImpl4;
import org.osjava.signals.natives.view.ViewOnKeySignal;

import android.text.Editable;
import android.text.InputType;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TextViewOnKeyDownSignal extends
		NativeSignalImpl4<TextView, Editable, Integer, KeyEvent> {

	private final static String TAG_NAME = ViewOnKeySignal.class.getSimpleName();

	private final static boolean DEFAULT_CONSUMED_VALUE = true;

	private final static int DEFAULT_INPUT_TYPE = InputType.TYPE_CLASS_TEXT;

	private final TargetListener _listener = new TargetListener();

	private boolean _consumed = DEFAULT_CONSUMED_VALUE;

	private final int _inputType;

	/**
	 * Private constructor
	 */
	private TextViewOnKeyDownSignal() {
		_inputType = DEFAULT_INPUT_TYPE;
	}

	/**
	 * Private constructor
	 * 
	 * @param target
	 *            to be used when applying the listeners
	 */
	private TextViewOnKeyDownSignal(TextView target, int inputType) {
		setTarget(target);

		_inputType = inputType;
	}

	/**
	 * Create a newInstance of TextViewOnKeyDownSignal
	 * 
	 * @return {@link TextViewOnKeyDownSignal}
	 */
	@SuppressWarnings("unchecked")
	public static TextViewOnKeyDownSignal newInstance() {
		return new TextViewOnKeyDownSignal();
	}

	/**
	 * Create a newInstance of TextViewOnKeyDownSignal
	 * 
	 * @param View
	 *            target to apply the listener when executing a dispatch
	 * @param inputType
	 *            the type of text that this key listener is manipulating, as
	 *            per {@link InputType}.
	 * 
	 * @return {@link TextViewOnKeyDownSignal}
	 */
	public static TextViewOnKeyDownSignal newInstance(final TextView target, final int inputType) {
		return new TextViewOnKeyDownSignal(target, inputType);
	}

	/**
	 * Is the long click consumed or not
	 * 
	 * @return boolean True if the Long click is consumed
	 */
	public boolean isConsumed() {
		return _consumed;
	}

	/**
	 * Set the consumed value of the click
	 * 
	 * @param value
	 *            True if the long click is consumed.
	 */
	public void setConsumed(boolean value) {
		_consumed = value;
	}

	/**
	 * Return the type of text that this key listener is manipulating, as per
	 * InputType.
	 * 
	 * @return Return the type of text that this key listener is manipulating,
	 *         as per {@link InputType}.
	 */
	public int getInputType() {
		return _inputType;
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
		TextView textView = getTarget();
		if (null != textView)
			textView.setKeyListener(_listener);
	}

	/**
	 * Private target listener class which implements the
	 * {@link OnClickListener}
	 */
	private final class TargetListener implements KeyListener {

		@Override
		public void clearMetaKeyState(View v, Editable text, int states) {
		}

		@Override
		public int getInputType() {
			return _inputType;
		}

		@Override
		public boolean onKeyDown(View v, Editable text, int keyCode, KeyEvent event) {
			setConsumed(DEFAULT_CONSUMED_VALUE);

			try {
				dispatch((TextView) v, text, keyCode, event);
			} catch (Throwable t) {
				Log.e(TAG_NAME, "Dispatch Error", t);
			}

			return isConsumed();
		}

		@Override
		public boolean onKeyOther(View v, Editable text, KeyEvent event) {
			return false;
		}

		@Override
		public boolean onKeyUp(View v, Editable text, int keyCode, KeyEvent event) {
			return false;
		}
	}
}
