package borrowmanager.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class TextInterfaceOptionPage extends TextInterfacePage implements EventObjectListener {
	// call this method whenever you want to notify
	// the event listeners of the particular event
	private synchronized void fireEvent(TextCommand command) {
		EventObject event = new EventObject(command);
		this.handleEvent(event);
	}

	private Map<String, TextCommand> commands = new HashMap<String, TextCommand>();
	private boolean hasGoBackOption = false;
	private final TextCommand backCommand = new TextCommand("back", "Go back");

	protected abstract void build();
	
	/**
	 * Add an option to the page with an auto incremented number as reference.
	 * 
	 * @param commandName
	 * @param text
	 */
	protected void addOption(String commandName, String text) {
		int choiceName = getAutoIncrementValue();
		addOption("" + choiceName, commandName, text);
	}

	/**
	 * Add option to the page.
	 * 
	 * @param choiceName
	 *            The string that the user has to type to select the option
	 * @param commandName
	 *            The internal command name
	 * @param text
	 *            The string displayed to describe the function
	 */
	protected void addOption(String choiceName, String commandName, String text) {
		commands.put("" + choiceName, new TextCommand(commandName, text));
	}

	protected void setHasGoBackOption(boolean b) {
		hasGoBackOption = b;
	}

	/**
	 * Returns the smallest integer that is greater than all the command keys
	 * 
	 * @return Auto increment value
	 */
	private int getAutoIncrementValue() {
		int current = -1;
		for (String s : commands.keySet()) {
			int parsed;
			try {
				parsed = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				continue;
			}
			// Parsed successfully
			if (parsed <= current) {
				parsed = current + 1;
			}
		}
		return current;
	}

	public boolean show() {
		// Display the commands
		for (String key : commands.keySet()) {
			displayCommand(key, commands.get(key));
		}

		// Display the go back command
		if (hasGoBackOption) {
			displayCommand("B", backCommand);
		}
		
		// Prompt the user
		System.out.println("Please type your choice : ");
		String in = input();
		
		// Go back
		if (hasGoBackOption && in.equals("B")) {
			return false ;
		}
		if (commands.containsKey(in)) {
			TextCommand command = commands.get(in);
			fireEvent(command);
		}
		
		// Run in loop unless go back has been called
		return true;
	}

	private void displayCommand(String key, TextCommand c) {
		System.out.println(key + " - " + c.getText());
	}

	public void welcome() {
		EventObject event = new EventObject(3);

	}

}