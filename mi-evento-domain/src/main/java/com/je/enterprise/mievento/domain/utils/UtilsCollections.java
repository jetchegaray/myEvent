package com.je.enterprise.mievento.domain.utils;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class UtilsCollections {

	public static <T> Iterable<T> shuffle(Iterable<T> iterable)
    {
        List<T> list = Lists.newArrayList(iterable);
        Collections.shuffle(list);
        return list;
    }
	
}
