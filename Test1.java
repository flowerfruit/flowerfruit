package com.mengxiang.producer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.gson.Gson;

public class Test1 {
	public static void main(String[] args) {
		int dataNumbers=100;
		int uuidNumbers=10;
		//行为类型
		List<String> activeList=new ArrayList<>();
		activeList.add("buy");
		activeList.add("sell");
		activeList.add("assess");//评价
		activeList.add("registe");//注册
		activeList.add("login");//登录
		activeList.add("logout");//登出
		
		//uuid  
		List<String> uuidList=new ArrayList<>();
		for(int i=0;i<uuidNumbers;i++){
			String uuid = UUID.randomUUID().toString();
			uuidList.add(uuid);
			
		}
		
		//
		
		 Random r = new Random(); 
		 String uuid="";
		 Behaviour bh=null;
		 BehaviourData data=null;
		 Message mess=null;
		 List<Behaviour> behaviourList=new ArrayList<>();
		for(int i=0;i<dataNumbers;i++){
			bh=new Behaviour();
			String ip="192.168."+(r.nextInt(2)+1)+"."+(r.nextInt(100)+1);
			bh.setIp(ip);
			uuid=uuidList.get(r.nextInt(uuidList.size()));
			bh.setMac(uuid);
			bh.setUuid(uuid);
			bh.setUid(uuid);
			bh.setDateStamp(randomDate("2018-03-01","2018-03-10").getTime()+"");
			data=new BehaviourData();
			data.setAcive(activeList.get(r.nextInt(activeList.size())));
			mess=new Message();
			mess.setUsername("zhangsan"+r.nextInt(10));
			mess.setOrder_num("112233");
			mess.setOrder_time(new Date());
			data.setMessage(mess);
			bh.setData(data);
			
			behaviourList.add(bh);
			
		}
		Gson gson = new Gson();
		
		String json = gson.toJson(behaviourList);
		
		
		System.out.println(json);
		
		
		
	}
	
	 /*// 返回2007-01-01到2007-03-01的一个随机日期  
    public static void main(String[] args) {  
        Date randomDate = randomDate("2007-01-01", "2007-03-01");  
        System.out.println(randomDate.toString());  
    }  */
  
    /** 
     * 获取随机日期 
     *  
     * @param beginDate 
     *            起始日期，格式为：yyyy-MM-dd 
     * @param endDate 
     *            结束日期，格式为：yyyy-MM-dd 
     * @return 
     */  
  
    private static Date randomDate(String beginDate, String endDate) {  
        try {  
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
            Date start = format.parse(beginDate);// 构造开始日期  
            Date end = format.parse(endDate);// 构造结束日期  
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  
            if (start.getTime() >= end.getTime()) {  
                return null;  
            }  
            long date = random(start.getTime(), end.getTime());  
  
            return new Date(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    private static long random(long begin, long end) {  
        long rtn = begin + (long) (Math.random() * (end - begin));  
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值  
        if (rtn == begin || rtn == end) {  
            return random(begin, end);  
        }  
        return rtn;  
    }  
  
} 
	

