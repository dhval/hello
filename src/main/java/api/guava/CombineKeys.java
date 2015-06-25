package api.guava;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dhval
 */
public class CombineKeys {
    
    static Map<String, Object> buildMap(String[] keys, String val) {
        Map<String, Object> data = new HashMap<String, Object>();
        int len = keys.length;
        data.put(keys[len - 1], val);
         for (int i = len-2; i >= 0; i--) {
             Map<String, Object> tmp = new HashMap<String, Object>();
             tmp.put(keys[i], data);
             data = tmp;
         }
         return data;
    }

    static Map<String, Object> addToMap(Map<String, Object> map, String key, String val) {
        String[] array = key.split("_");
        int len = array.length;
        if (len == 1) {
            map.put(key, val);
            return map;
        }
        Map<String, Object> newMap = buildMap(array, val);
        Map<String, Object> tmp = map;
        for (int i = 0; i < len; i++) {
            if (!tmp.containsKey(array[i])) {
                tmp.putAll(newMap);
                break;
            }
            tmp  = (Map<String, Object>) tmp.get(array[i]);
            newMap = (Map<String, Object>) newMap.get(array[i]);
        }
         return map;
    }

    public static void main(String[] args) {

        Map<String, Object> data = new HashMap<String, Object>();

        addToMap(data, "Name", "Venkat");
        addToMap(data, "Organization", "SLCS");
        addToMap(data, "customerinfo_cuts_customerid", "10");
        addToMap(data, "departmentinfo_departmentid", "1");
        addToMap(data, "departmentinfo_departmentname", "3");
        addToMap(data, "departmentinfo_departmentname", "3");
        addToMap(data, "customerinfo_cust_customername", "Nelluri");
        addToMap(data, "classinfo_classid", "322");

        System.out.println(data);

    }

}
