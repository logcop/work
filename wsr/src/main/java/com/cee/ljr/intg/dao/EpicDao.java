/**
 * 
 */
package com.cee.ljr.intg.dao;

import java.util.Collection;
import java.util.Set;

import com.cee.ljr.domain.common.Epic;

/**
 * @author chuck
 *
 */
public interface EpicDao {
	
	Set<Epic> getByKeys(Collection<String> keys);
	
	Epic getByKey(String key);
}
