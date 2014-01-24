package borrowmanager.view.menu;

import java.util.LinkedList;
import java.util.List;

import borrowmanager.model.Manager;
import borrowmanager.model.element.State;
import borrowmanager.model.material.Material;
import borrowmanager.view.ItemPicker;
import borrowmanager.view.MaterialPicker;
import borrowmanager.view.TextInterfacePage;

public class NeedRepairList extends TextInterfacePage {

	private Manager manager;
	public NeedRepairList(Manager m) {
		manager = m;
	}
	
	@Override
	public TextInterfacePage display() {
		System.out.println("Here is the list of the material that needs to be repaired.");
		System.out.println("Select the one that you want to send to repair.");
		// List of the destroyed material
		List<Material> destroyedMaterials = manager.getMaterialWithState(State.DESTROYED);
		// Filter it to get the list of the material that can be repaired
		List<Material> repairableMaterials = new LinkedList<Material>();
		for (Material m : destroyedMaterials) {
			if (! m.isInRepair()) {
				repairableMaterials.add(m);
			}
		}
		MaterialPicker picker = new MaterialPicker(repairableMaterials);
		picker.display();
		Material m = picker.getPickedItemId();
		
		if (m != null) {
			if (question("Repair duration is "+m.getRepairDuration()+" days. Do you want to send "+m.getFullName()+" to repair ?")) {
				m.sendInRepair(Manager.now);
				System.out.println("The material was sent to repair");
			}
		}
		else {
			return null;
		}
		
		return this;
	}

}
