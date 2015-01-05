package com.fheebiy.http.lite.request.query;

import com.fheebiy.http.lite.data.Json;

/**
 * when uri query parameter's value is complicated, build value into json.
 * in this case, value will intelligently translate to json string.
 * <p/>
 * such as:
 * http://def.so? key1 = value.toJsonString() & key2 = value.toJsonString()
 *
 * @author MaTianyu
 *         2014-1-4下午5:06:37
 */
public class JsonQueryBuilder extends AbstractQueryBuilder {

    @Override
    protected CharSequence buildSencondaryValue(Object model) {
        try {
            return Json.get().toJson(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
