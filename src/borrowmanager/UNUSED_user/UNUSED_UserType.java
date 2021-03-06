package borrowmanager.UNUSED_user;

/**
 * UserType enum, listing all the possible user types
 * @author Tom Guillermin
 *
 */
public enum UNUSED_UserType {
	STUDENT,
	TEACHER,
	STOCK_MANAGER;
	
	public static int getBookingLength(UNUSED_UserType u) {
		if (u == STUDENT) {
			return 7;   
		}
		else if (u == TEACHER) {
			return 30;
		}
		else {
			return 0;
		}
	}
	
	public static int getMaxReservationLength(UNUSED_UserType u) {
		if (u == STUDENT) {
			return 10;   
		}
		else if (u == TEACHER) {
			return 60;
		}
		else {
			return 0;
		}
	}
}