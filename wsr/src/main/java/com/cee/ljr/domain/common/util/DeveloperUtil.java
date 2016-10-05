/**
 * 
 */
package com.cee.ljr.domain.common.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.cee.ljr.domain.common.Developer;

/**
 * @author chuck
 *
 */
public abstract class DeveloperUtil {

	public static Set<Developer> getAllByKeys(Collection<String> keys, Collection<Developer> developers) {
		Set<Developer> developerSet = new HashSet<Developer>();
		
		if (keys != null && !keys.isEmpty()) {
			for (Developer developer : developers) {
				String devKey = developer.getNameInJira();
				if (keys.contains(devKey)) {
					developerSet.add(developer);
				}
			}
		}
		
		return developerSet;
	}

}
