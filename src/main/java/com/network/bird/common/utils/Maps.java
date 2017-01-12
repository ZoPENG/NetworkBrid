package com.network.bird.common.utils;

import java.util.HashMap;

/**
 * Created by zhoupeng on 2017/1/6.
 */
public final class Maps {

    private Maps(){}

    public static <K, V> HashMap<K, V> newHashMap(){
        return new HashMap<K, V>();
    }
}
