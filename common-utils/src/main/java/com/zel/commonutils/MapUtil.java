package com.zel.commonutils;

import com.zel.commonutils.client.UrlUtil;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
public class MapUtil {
    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return !isNotEmpty(map);
    }

    //public static <K, V> void forEach(Map<K, V> map, BiConsumer<? super K, ? super V> action) {
    //    if (!isEmpty(map) && action != null) {
    //        Object k;
    //        Object v;
    //        for(Iterator var2 = map.entrySet().iterator(); var2.hasNext(); action.accept(k, v)) {
    //            Map.Entry entry = (Map.Entry)var2.next();
    //
    //            try {
    //                k = entry.getKey();
    //                v = entry.getValue();
    //            } catch (IllegalStateException var7) {
    //                throw new ConcurrentModificationException(var7);
    //            }
    //        }
    //
    //    }
    //}

    public static String parseMapToString(Map<String, String> params, boolean encode) {
        List<String> paramList = new ArrayList();

        params.forEach((k, v) -> {
            if (v == null) {
                paramList.add(k + "=");
            } else {
                paramList.add(k + "=" + (encode ? UrlUtil.urlEncode(v) : v));
            }
        });
        //forEach(params, (k, v) -> {
        //    if (v == null) {
        //        paramList.add(k + "=");
        //    } else {
        //        paramList.add(k + "=" + (encode ? UrlUtil.urlEncode(v) : v));
        //    }
        //
        //});
        return String.join("&", paramList);
    }

}
