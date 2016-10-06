/**
 * 
 */
package com.cee.ljr.intg.dao;

import java.util.List;

import com.cee.ljr.domain.common.Epic;

/**
 * @author chuck
 *
 */
public interface EpicDao {
	
	List<Epic> getByKeys(String filePaths, List<String> keys);
	
	Epic getByKey(String filePaths, String key);
}
