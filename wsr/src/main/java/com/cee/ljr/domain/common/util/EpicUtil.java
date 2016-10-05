/**
 * 
 */
package com.cee.ljr.domain.common.util;

import java.util.Set;

import com.cee.ljr.domain.common.Epic;

/**
 * @author chuck
 *
 */
public abstract class EpicUtil {

	/**
	 * 
	 */
	public static Epic getByKey(Set<Epic> epics, String epicKey) {
		if (epicKey != null) {
			
			for (Epic epic : epics) {
				
				if(epicKey.equals(epic.getKey())) {
					return epic;
				}
			}
		}
		
		return null;
	}

}
