package com.cfysu.junit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class ModuleSite {

	@Test
	public void testGetModuleIds(){
		
		/*String moduleSite = "{\"head\":\"\",\"body\":\"21482,21483,21484,21485,\",\"foot\":\"21486,\"}";
		StringBuffer sb = new StringBuffer();
		LinkedHashMap<String, String> moduleSiteMap = JSON.parseObject(moduleSite, new TypeReference<LinkedHashMap<String, String>>() {});
		for(Map.Entry<String, String> entry : moduleSiteMap.entrySet()){
			sb.append(entry.getValue());
		}
		System.out.println("moduleIds:" + sb.toString());*/
		
		Map<Long, Long> moduleIdMap = new HashMap<Long, Long>();
		moduleIdMap.put(111L, 222L);
		String moduleSite = "{\"head\":\"\",\"body\":\"21482,21483,21484,21485,\",\"foot\":\"21486,\"}";
		
		LinkedHashMap<String, String> moduleSiteMap = JSON.parseObject(moduleSite, new TypeReference<LinkedHashMap<String, String>>() {});
		
		for(Map.Entry<String, String> entry : moduleSiteMap.entrySet()){
			StringBuffer sb = new StringBuffer();
			String[] oldModuleIds = entry.getValue().split(",");
			for(String oldModuleId : oldModuleIds){
				if(StringUtils.isBlank(oldModuleId)){
					continue;
				}
				if(moduleIdMap.get(Long.valueOf(oldModuleId)) != null){
					sb.append(moduleIdMap.get(Long.valueOf(oldModuleId)) + ",");
				}else{
					sb.append(oldModuleId + ",");
				}
			}
			entry.setValue(sb.toString());
		}
		System.out.println("res:" + JSON.toJSONString(moduleSiteMap));
	}
}
