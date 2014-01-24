package borrowmanager;

import borrowmanager.model.Manager;
import borrowmanager.view.TextInterfacePage;
import borrowmanager.view.menu.MainMenu;

public class App {
	public static void main(String args[]) {
		Manager manager = new Manager();
		TextInterfacePage main = new MainMenu(manager);
	}
}
