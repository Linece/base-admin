package cn.huanzi.qch.baseadmin.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class StringToolUtil {

	public static Map<String,String> dataMap(String sourceData){
		Map<String,String> returnData = new HashMap<String,String>();
		if(StringUtils.isEmpty(sourceData) && sourceData.contains("=")){
			throw new  IllegalArgumentException("源数据不能为空或者格式是否aa=bb格式");
		}
		String[] dataArr = sourceData.split("=");
		returnData.put("sourceName", dataArr[0]);
		returnData.put("sourceValue", dataArr[1]);
		return returnData;
 	}
}
