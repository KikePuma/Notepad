package practica4;

import java.text.ParseException;
import java.util.Comparator;

public class ComparadorAutobusSalida implements Comparator<Autobus> {

	public int compare(Autobus a1, Autobus a2) {
		try {
			if (a1.getLongOut() > a2.getLongOut()) {
				return 1;
			} else if (a1.getLongOut() < a2.getLongOut()) {
				return -1;
			} else {
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
