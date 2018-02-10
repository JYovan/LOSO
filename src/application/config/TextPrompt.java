package application.config;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 *  The TextPrompt class will display a prompt over top of a text component when
 *  the Document of the text field is empty. The Show property is used to
 *  determine the visibility of the prompt.
 *
 *  The Font and foreground Color of the prompt will default to those properties
 *  of the parent text component. You are free to change the properties after
 *  class construction.
 */
public class TextPrompt extends JLabel
	implements FocusListener, DocumentListener
{
	public enum Show
	{
		ALWAYS,
		FOCUS_GAINED,
		FOCUS_LOST;
	}

	private JTextComponent component;
	private Document document;

	private Show show;
	private boolean showPromptOnce;
	private int focusLost;

	public TextPrompt(String text, JTextComponent component)
	{
		this(text, component, Show.ALWAYS);
	}

	public TextPrompt(String text, JTextComponent component, Show show)
	{
		this.component = component;
		setShow( show );
		document = component.getDocument();

		setText( text );
		setFont( component.getFont() );
		setForeground( component.getForeground() );
		setBorder( new EmptyBorder(component.getInsets()) );
		setHorizontalAlignment(JLabel.LEADING);

		component.addFocusListener( this );
		document.addDocumentListener( this );

		component.setLayout( new BorderLayout() );
		component.add( this );
		checkForPrompt();
	}

	/**
	 *  Convenience method to change the alpha value of the current foreground
	 *  Color to the specifice value.
	 *
	 *  @param alpha value in the range of 0 - 1.0.
	 */
	public void changeAlpha(float alpha)
	{
		changeAlpha( (int)(alpha * 255) );
	}

	/**
	 *  Convenience method to change the alpha value of the current foreground
	 *  Color to the specifice value.
	 *
	 *  @param alpha value in the range of 0 - 255.
	 */
	public void changeAlpha(int alpha)
	{
		alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

		Color foreground = getForeground();
		int red = foreground.getRed();
		int green = foreground.getGreen();
		int blue = foreground.getBlue();

		Color withAlpha = new Color(red, green, blue, alpha);
		super.setForeground( withAlpha );
	}

	/**
	 *  Convenience method to change the style of the current Font. The style
	 *  values are found in the Font class. Common values might be:
	 *  Font.BOLD, Font.ITALIC and Font.BOLD + Font.ITALIC.
	 *
	 *  @param style value representing the the new style of the Font.
	 */
	public void changeStyle(int style)
	{
		setFont( getFont().deriveFont( style ) );
	}

	/**
	 *  Get the Show property
	 *
	 *  @return the Show property.
	 */
	public Show getShow()
	{
		return show;
	}

	/**
	 *  Set the prompt Show property to control when the promt is shown.
	 *  Valid values are:
	 *
	 *  Show.AWLAYS (default) - always show the prompt
	 *  Show.Focus_GAINED - show the prompt when the component gains focus
	 *      (and hide the prompt when focus is lost)
	 *  Show.Focus_LOST - show the prompt when the component loses focus
	 *      (and hide the prompt when focus is gained)
	 *
	 *  @param show a valid Show enum
	 */
	public void setShow(Show show)
	{
		this.show = show;
	}

	/**
	 *  Get the showPromptOnce property
	 *
	 *  @return the showPromptOnce property.
	 */
	public boolean getShowPromptOnce()
	{
		return showPromptOnce;
	}

	/**
	 *  Show the prompt once. Once the component has gained/lost focus
	 *  once, the prompt will not be shown again.
	 *
	 *  @param showPromptOnce  when true the prompt will only be shown once,
	 *                         otherwise it will be shown repeatedly.
	 */
	public void setShowPromptOnce(boolean showPromptOnce)
	{
		this.showPromptOnce = showPromptOnce;
	}

	/**
	 *	Check whether the prompt should be visible or not. The visibility
	 *  will change on updates to the Document and on focus changes.
	 */
	private void checkForPrompt()
	{
		//  Text has been entered, remove the prompt

		if (document.getLength() > 0)
		{
			setVisible( false );
			return;
		}

		//  Prompt has already been shown once, remove it

		if (showPromptOnce && focusLost > 0)
		{
			setVisible(false);
			return;
		}

		//  Check the Show property and component focus to determine if the
		//  prompt should be displayed.

        if (component.hasFocus())
        {
        	if (show == Show.ALWAYS
        	||  show ==	Show.FOCUS_GAINED)
        		setVisible( true );
        	else
        		setVisible( false );
        }
        else
        {
        	if (show == Show.ALWAYS
        	||  show ==	Show.FOCUS_LOST)
        		setVisible( true );
        	else
        		setVisible( false );
        }
	}

//  Implement FocusListener

	public void focusGained(FocusEvent e)
	{
		checkForPrompt();
	}

	public void focusLost(FocusEvent e)
	{
		focusLost++;
		checkForPrompt();
	}

//  Implement DocumentListener

	public void insertUpdate(DocumentEvent e)
	{
		checkForPrompt();
	}

	public void removeUpdate(DocumentEvent e)
	{
		checkForPrompt();
	}

	public void changedUpdate(DocumentEvent e) {}
}
/*Text Prompt*/

/*

The TextPrompt class allows you to add this functionality to a text field. The implementation takes advantage of the Swing design that allows you to add child components to any Swing component. So a JLabel containing the prompt text is added to the text field. The main functionality of the TextPrompt class is to determine whether the prompt should be visible or not. The prompt will be removed as soon as text is entered into the Document.

When the Document is empty, the setShow method allows yout to control when the prompt is visible:

ALWAYS – the prompt is displayed whether the text field has focus or not.
FOCUS_GAINED – the prompt is displayed when the text field gains focus (and is hidden when focus is lost)
FOCUS_LOST – the prompt is displayed when the text field loses focus (and is hidden when focus is gained)
There is only one other method that allows you control the behaviour of the prompt:

setShowPromptOnce – will only display the prompt once. After the text field loses focus for the first time the prompt will no longer be visible.
The Font and foreground Color of the TextPrompt will default from the text field at the time the TextPrompt is created. However, there are a couple of convenience methods that will allow you to customize the appearance of the text in the prompt:

changeAlpha – allows you to change the alpha value of the foreground Color to provide some transparency
changeStyle – allows you to change the style of the Font. The values allowed are those supported by the Font class. Common values might be Font.BOLD, Font.ITALIC or Font.BOLD + Font.ITALIC
The code to create the last prompt in the above image was:

JTextField tf7 = new JTextField(10);
TextPrompt tp7 = new TextPrompt("First Name", tf7);
tp7.setForeground( Color.RED );
tp7.changeAlpha(0.5f);
tp7.changeStyle(Font.BOLD + Font.ITALIC);
tp7.setIcon( ... );

Notice the setIcon() method? As mentioned earlier, the TextPrompt simply extends JLabel to provide its functionality so you can change any property of the label to achieve your desired effect. Be creative, who knows what effect you can achieve!

Although this discussion has dealt with using the TextPrompt on a text field, it can also be used on a JTextArea or JTextPane as well. Using it on a JFormattedTextField that contains formatting characters may prove to be more difficult. I’ll let you experiment with that on your own.

Another option would be to use the PromptSupport class found in the SwingX project. SwingX provides many enhancements to base Swing.

*/