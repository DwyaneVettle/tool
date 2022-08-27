package demo;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 栅栏加/解密算法
 * @author waver
 *
 */
public class FenceUtil {
	
	/**
	 * 栅栏加密
	 * @param str		明文
	 * @param fenceLen	秘钥
	 * @return
	 */
	public static String enCodeStr(String str, int fenceLen){
		if( str == null || str.length() <= 0 )throw new RuntimeException("明文不能为空！");
		if( fenceLen <= 0 || fenceLen >= str.length() )return str;
		int fenceNum = str.length() % fenceLen == 0 ? str.length() / fenceLen : (str.length() / fenceLen) + 1;
		Queue<String> queue = str2Queue(str);
		List<Queue<String>> itemList = itemList(queue, fenceLen);
		List<Queue<String>> reCommonHeader = reCommonHeader(itemList, fenceLen, fenceNum);
		return list2Str(reCommonHeader);
	}
	
	/**
	 * 栅栏解密
	 * @param str		密文
	 * @param fenceLen	秘钥
	 * @return
	 */
	public static String deCodeStr(String str, int fenceLen){
		String mwStr = null;
		try {
			if( str == null || str.length() <= 0 )return mwStr;
			if( fenceLen <= 0 || fenceLen >= str.length() )throw new RuntimeException("解密失败！");
			int fenceNum = str.length() % fenceLen == 0 ? str.length() / fenceLen : (str.length() / fenceLen) + 1;
			
			Queue<String> str2Queue = str2Queue(str);
			List<Queue<String>> itemList = itemList(str2Queue, fenceNum);
			List<Queue<String>> reCommonHeader = reCommonHeader(itemList, fenceNum, fenceLen);
			mwStr = list2Str(reCommonHeader);
		} catch (Exception e) {
			throw e;
		}
		return mwStr;
	}
	
	
	/**
	 * 字符串转队列
	 * @param str	
	 * @return
	 */
	private static Queue<String> str2Queue(String str){
		Queue<String> queue = new LinkedList<String>();
		for (int i = 0; i < str.length(); i++) 
			queue.offer(String.valueOf(str.charAt(i)));
		return queue;
	}
	
	/**
	 * 集合转字符串
	 * @param list
	 * @return
	 */
	private static String list2Str(List<Queue<String>> list){
		StringBuilder strB = new StringBuilder();
		for (Queue<String> it : list) {
			while (it.size() > 0) {
				String s = it.poll();
				strB.append( s == null ? "" : s );
			}
		}
		return strB.toString();
	}
	
	/**
	 * 按照长度分组
	 * @param queue
	 * @param strLen
	 * @return
	 */
	private static List<Queue<String>> itemList(Queue<String> queue, int strLen){
		List<Queue<String>> listQueue = new ArrayList<Queue<String>>();
		Queue<String> item = null;
		while ( queue.size() > 0 ) {
			item = new LinkedList<String>();
			for (int i = 0; i < strLen; i++) {
				item.offer(queue.poll());
			}
			listQueue.add(item);
		}
		return listQueue;
	}
	
	/**
	 * 重组数据
	 * @param itemList	分组数据
	 * @param strLen	栅栏长度
	 * @param strNum	栅栏数量	
	 * @return
	 */
	private static List<Queue<String>> reCommonHeader(List<Queue<String>> itemList, int strLen, int strNum){
		List<Queue<String>> list = new ArrayList<Queue<String>>();
		Queue<String> item = null;
		for (int i = 0; i < strLen; i++) {
			item = new LinkedList<String>();
			for (int j = 0; j < strNum; j++) {
				item.offer(itemList.get(j).poll());
			}
			list.add(item);
		}
		return list;
	}
	
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String str = "abcd987654";
		int key = 2;
		
		String mw = enCodeStr(str, key);
		System.out.println(mw);
		
		String yw = deCodeStr(mw, key);
		System.out.println(yw);
		
		
	}
	
	
	
	
}
