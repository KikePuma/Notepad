package practica3;

import java.text.ParseException;
import java.util.Comparator;

public class Compare implements Comparator<Travel>{
	public int compare (Travel t1, Travel t2){
		try{
			if (t1.getDeparture() > t2.getDeparture()){
				return 1;
			} else if (t1.getDeparture()  < t2.getDeparture()){
					return -1;
			}
		} catch (ParseException e) {
				e.printStackTrace();
			}
			return 0;
		}
}