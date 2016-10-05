package com.cee.ljr.intg.fileparser;

import java.util.Collection;

public interface DeveloperFileParser<T> {
	
	Iterable<T> parseAll();
	
	T parseForName(String nameInJira);
	
	Iterable<T> parseByKeys(Collection<String> keys);
}
