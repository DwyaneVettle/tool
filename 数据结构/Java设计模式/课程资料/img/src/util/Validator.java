package util;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 验证工具类
 */
public class Validator {
	/**
	 * 验证value值是否为空
	 * @param value 需要验证的值
	 * @return true：为空、false：非空
	 */
	public static boolean isNull( Object value ){
		if( value == null )return true;
		if( value instanceof String && "".equalsIgnoreCase( value.toString().trim() )){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证value值是否为非空
	 * @param value 需要验证的值
	 * @return true：非空、false：为空
	 */
	public static boolean isNotNull( Object value ){
		if( value == null ) return false;
		if( value instanceof String && "".equalsIgnoreCase( value.toString().trim() )){
			return false;
		}
		return true;
	}
	
	/**
	 * 验证array是否为空数组(元素是否全为null或"")
	 * @param array 数组对象
	 * @return true：空数组、false：非空数组
	 */
	public static boolean isEmpty( Object[] array ){
		if( array == null ) return true;
		if( array.length == 0 ) return true;
		for( Object element : array ){
			if( ! isNull( element ) ) return false;
		}
		return true;
	}
	
	/**
	 * 验证Collection是否为空集合(元素是否全为null或"")
	 * @param collection 集合对象
	 * @return true：空集合、false：非空集合
	 */
	public static boolean isEmpty( Collection<?> collection ){
		if( collection == null ) return true;
		if( collection.size() == 0 ) return true;
		for( Object element : collection ){
			if( ! isNull( element ) ) return false;
		}
		return true;
	}
	
	/**
	 * 验证Map是否为空集合
	 * @param map 集合对象
	 * @return true：空集合、false：非空集合
	 */
	public static boolean isEmpty( Map<?,?> map ){
		if( map == null ) return true;
		if( map.size() == 0 ) return true;
		for( Object value : map.values() ){
			if( ! isNull( value ) ) return false;
		}
		return true;
	}
	
	/**
	 * 验证value值是否是正整数
	 * @param value 需要验证的值
	 * @return true：正整数、false：非正整数
	 */
	public static boolean isPositiveInt( Number value ){
		if( isNull(value) )return false;
		Pattern regex = Pattern.compile( "^[1-9]\\d*$" ); 
		Matcher matcher = regex.matcher( value.toString() );
		if( matcher.matches() ){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证value值是否是正整数
	 * @param value 需要验证的值
	 * @return true：正整数、false：非正整数
	 */
	public static boolean isPositiveInt( String value ){
		if( isNull(value) )return false;
		Pattern regex = Pattern.compile( "^[1-9]\\d*$" ); 
		Matcher matcher = regex.matcher( value );
		if( matcher.matches() ){
			return true;
		}
		return false;
	}
	
	
	private static String REGEX_STR = "^[1-9]\\d{@min,@max}(\\.\\d{1,2})?$|^(0\\.)\\d{1}[1-9]$|^(0\\.)[1-9]{1}(\\d)$";
	/**
	 * 验证是否是金额格式
	 * @param value 需要进行验证的金额
	 * @param min 整数部分最小位数
	 * @param max 整数部分最大位数
	 * @return
	 */
	public static boolean isMoney( BigDecimal value, int min, int max ){
		if( min <= 0 ) throw new InvalidParameterException( "参数:min 必须为大于0的值 " );
		if( max <= min ) throw new InvalidParameterException( "参数:max 必须大于min的值 " );
		if( isNull(value) ) return false;
		String regexStr = REGEX_STR.replace("@min", (min - 1) +"").replace("@max", (max - 1) +"");
		Pattern regex = Pattern.compile( regexStr ); 
		Matcher matcher = regex.matcher( value.toString() );
		if( matcher.matches() ){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证是否是金额格式
	 * @param value 需要进行验证的金额
	 * @param min 整数部分最小位数
	 * @param max 整数部分最大位数
	 * @return
	 */
	public static boolean isMoney( String value, int min, int max ){
		if( min <= 0 ) throw new InvalidParameterException( "参数:min 必须为大于0的值 " );
		if( max < min ) throw new InvalidParameterException( "参数:max 必须大于或等于min的值 " );
		if( isNull(value) ) return false;
		String regexStr = REGEX_STR.replace("@min", (min - 1) +"").replace("@max", (max - 1) +"");
		Pattern regex = Pattern.compile( regexStr ); 
		Matcher matcher = regex.matcher( value );
		if( matcher.matches() ){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证value值是否在指定的范围类
	 * @param value 需要验证的值
	 * @param min 最小直(null：不限制最小值)
	 * @param max 最大值(null：不限制最大值)
	 * @return true：满足、false：不满足
	 */
	public static boolean isInScope( Number value, Object min, Object max ){
		if( isNull(value) )return false;
		if( min != null && !(min instanceof Number) )throw new RuntimeException( "参数值  min 的类型错误." );
		if( max != null && !(max instanceof Number) )throw new RuntimeException( "参数值  max 的类型错误." );
		if( min == null && max != null ){
			if( value.doubleValue() > ((Number)max).doubleValue() )return false;
		}else if( min != null && max == null ){
			if( value.doubleValue() < ((Number)min).doubleValue() )return false;
		}else if( min != null && max != null ){
			if(	value.doubleValue() < ((Number)min).doubleValue() 
				|| 
				value.doubleValue() > ((Number)max).doubleValue()
			){return false;}
		}
		return true;
	}
	
	/**
	 * 验证value值是否在指定的范围类
	 * @param value 需要验证的值
	 * @param min 最小直(null：不限制最小值)
	 * @param max 最大值(null：不限制最大值)
	 * @return true：满足、false：不满足
	 */
	public static boolean isInScope( String value, Object min, Object max ){
		if( isNull(value) )return false;
		if( min != null && !(min instanceof Number) )throw new RuntimeException( "参数值  min 的类型错误." );
		if( max != null && !(max instanceof Number) )throw new RuntimeException( "参数值  max 的类型错误." );
		if( min == null && max != null ){
			if( value.length() > ((Number)max).doubleValue() )return false;
		}else if( min != null && max == null ){
			if( value.length() < ((Number)min).doubleValue() )return false;
		}else if( min != null && max != null ){
			if(	value.length() < ((Number)min).doubleValue() 
				|| 
				value.length() > ((Number)max).doubleValue()
			){return false;}
		}
		return true;
	}
	
	/**
	 * 验证指定数组的长度
	 * @param array 需要验证的数组
	 * @param min 最小长度(null：不限制最小长度)
	 * @param max 最大长度(null：不限制最大长度)
	 * @return true：满足、false：不满足
	 */
	public static boolean checkLength( Object[] array, Object min, Object max ){
		if( isNull(array) )return false;
		if( min != null && !(min instanceof Number) )throw new RuntimeException( "参数值  min 的类型错误." );
		if( max != null && !(max instanceof Number) )throw new RuntimeException( "参数值  max 的类型错误." );
		if( min == null && max != null ){
			if( array.length > ((Number)max).intValue() )return false;
		}else if( min != null && max == null ){
			if( array.length < ((Number)min).intValue() )return false;
		}else if( min != null && max != null ){
			if(	array.length < ((Number)min).intValue() 
				|| 
				array.length > ((Number)max).intValue()
			){return false;}
		}
		return true;
	}
	
	/**
	 * 验证指定集合的长度
	 * @param array 需要验证的集合
	 * @param min 最小长度(null：不限制最小长度)
	 * @param max 最大长度(null：不限制最大长度)
	 * @return true：满足、false：不满足
	 */
	public static boolean checkSize( Collection<?> collection, Object min, Object max ){
		if( isNull(collection) )return false;
		if( min != null && !(min instanceof Number) )throw new RuntimeException( "参数值  min 的类型错误." );
		if( max != null && !(max instanceof Number) )throw new RuntimeException( "参数值  max 的类型错误." );
		if( min == null && max != null ){
			if( collection.size() > ((Number)max).intValue() )return false;
		}else if( min != null && max == null ){
			if( collection.size() < ((Number)min).intValue() )return false;
		}else if( min != null && max != null ){
			if(	collection.size() < ((Number)min).intValue() 
				|| 
				collection.size() > ((Number)max).intValue()
			){return false;}
		}
		return true;
	}
	
	/**
	 * 验证是否为固话
	 * @param phone 固话号码
	 * @return true：满足、false：不满足
	 */
	public static boolean isPhone( String phone ){
		if( isNull(phone) )return false;
		if( phone.contains("-") )
			return regexCheck(phone, "[0-9]{3,4}-[0-9]{8}");
		else
			return regexCheck(phone, "[0-9]{3,4}[0-9]{8}");
	}
	
	/**
	 * 验证是否为手机号
	 * @param mobile 手机号码
	 * @return true：满足、false：不满足
	 */
	public static boolean isMobile( String mobile ){
		if( isNull(mobile) )return false;
		if( mobile.length() != 11 ) return false;
		return true;
	}
	
	/**
	 * 正则表达式验证
	 * @param value 需要验证的值
	 * @param rule 正则规则
	 * @return true：满足、false：不满足
	 */
	public static boolean regexCheck( String value, String rule ){
		Pattern regex = Pattern.compile( rule ); 
		Matcher matcher = regex.matcher( value );
		if( matcher.matches() ){
			return true;
		}
		return false;
	}
	
	public static boolean isCardId( String cardId ){
		return CardIdValidator.isValidatedAllCardId(cardId);
	}
	
	
	/**
	 * 身份证验证器类
	 */
	private static class CardIdValidator{
		 static String codeAndCity[][] = { { "11", "北京" }, { "12", "天津" },   
		            { "13", "河北" }, { "14", "山西" }, { "15", "内蒙古" }, { "21", "辽宁" },   
		            { "22", "吉林" }, { "23", "黑龙江" }, { "31", "上海" }, { "32", "江苏" },   
		            { "33", "浙江" }, { "34", "安徽" }, { "35", "福建" }, { "36", "江西" },   
		            { "37", "山东" }, { "41", "河南" }, { "42", "湖北" }, { "43", "湖南" },   
		            { "44", "广东" }, { "45", "广西" }, { "46", "海南" }, { "50", "重庆" },   
		            { "51", "四川" }, { "52", "贵州" }, { "53", "云南" }, { "54", "西藏" },   
		            { "61", "陕西" }, { "62", "甘肃" }, { "63", "青海" }, { "64", "宁夏" },   
		            { "65", "新疆" }, { "71", "台湾" }, { "81", "香港" }, { "82", "澳门" },   
		            { "91", "国外" } }; 
		 
		 private static String cityCode[] = { "11", "12", "13", "14", "15", "21", "22",   
		            "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",   
		            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",   
		            "64", "65", "71", "81", "82", "91" };
		 //每位加权因子   
		 private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };  
		 //第18位校检码   
		 private static String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
		 
		 /**  
		  * 验证所有的身份证的合法性  
		  * @param idcard  
		  * @return  
		  */  
		 public static boolean isValidatedAllCardId(String cardId) {   
		     if (cardId.length() == 15) {   
		    	 cardId = convertCardIdBy15bit(cardId);   
		     }   
		     return isValidate18CardId(cardId);   
		 }
		 
		/**
		 * <p>
		 * 判断18位身份证的合法性
		 * </p>
		 * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，
		 * 由十七位数字本体码和一位数字校验码组成。 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
		 * <p>
		 * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
		 * </p>
		 * <p>
		 * 1.前1、2位数字表示：所在省份的代码； 
		 * 2.第3、4位数字表示：所在城市的代码； 
		 * 3.第5、6位数字表示：所在区县的代码；
		 * 4.第7~14位数字表示：出生年、月、日； 
		 * 5.第15、16位数字表示：所在地的派出所的代码；
		 * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
		 * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性
		 * 。校检码可以是0~9的数字，有时也用x表示。
		 * </p>
		 * <p>
		 * 第十八位数字(校验码)的计算方法为： 
		 * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
		 * 2.将这17位数字和系数相乘的结果相加。
		 * 3.用加出来和除以11，看余数是多少？
		 * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
		 * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
		 * </p>
		 * 
		 * @param cardId
		 * @return
		 */
		public static boolean isValidate18CardId(String cardId) {
			// 非18位为假
			if (cardId.length() != 18) {
				return false;
			}
			// 获取前17位
			String idcard17 = cardId.substring(0, 17);
			// 获取第18位
			String idcard18Code = cardId.substring(17, 18);
			char c[] = null;
			String checkCode = "";
			// 是否都为数字
			if (isDigital(idcard17)) {
				c = idcard17.toCharArray();
			} else {
				return false;
			}
			if (null != c) {
				int bit[] = new int[idcard17.length()];
				bit = converCharToInt(c);
				int sum17 = 0;
				sum17 = getPowerSum(bit);
				// 将和值与11取模得到余数进行校验码判断
				checkCode = getCheckCodeBySum(sum17);
				if (null == checkCode) {
					return false;
				}
				// 将身份证的第18位与算出来的校码进行匹配，不相等就为假
				if (!idcard18Code.equalsIgnoreCase(checkCode)) {
					return false;
				}
			}
			return true;
		}
		
		/**
		 * 验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断，该类中已提供。
		 * 
		 * @param cardId
		 * @return
		 */
		public static boolean isValidate15CardId(String cardId) {
			// 非15位为假
			if (cardId.length() != 15) {
				return false;
			}
			// 是否全都为数字
			if (isDigital(cardId)) {
				String provinceid = cardId.substring(0, 2);
				String birthday = cardId.substring(6, 12);
				int year = Integer.parseInt(cardId.substring(6, 8));
				int month = Integer.parseInt(cardId.substring(8, 10));
				int day = Integer.parseInt(cardId.substring(10, 12));
				// 判断是否为合法的省份
				boolean flag = false;
				for (String id : cityCode) {
					if (id.equals(provinceid)) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					return false;
				}
				// 该身份证生出日期在当前日期之后时为假
				Date birthdate = null;
				try {
					birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (birthdate == null || new Date().before(birthdate)) {
					return false;
				}
				// 判断是否为合法的年份
				GregorianCalendar curDay = new GregorianCalendar();
				int curYear = curDay.get(Calendar.YEAR);
				int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));
				// 判断该年份的两位表示法，小于50的和大于当前年份的，为假
				if ((year < 50 && year > year2bit)) {
					return false;
				}
				// 判断是否为合法的月份
				if (month < 1 || month > 12) {
					return false;
				}
				// 判断是否为合法的日期
				boolean mflag = false;
				curDay.setTime(birthdate); // 将该身份证的出生日期赋于对象curDay
				switch (month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					mflag = (day >= 1 && day <= 31);
					break;
				case 2: // 公历的2月非闰年有28天,闰年的2月是29天。
					if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {
						mflag = (day >= 1 && day <= 29);
					} else {
						mflag = (day >= 1 && day <= 28);
					}
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					mflag = (day >= 1 && day <= 30);
					break;
				}
				if (!mflag) {
					return false;
				}
			} else {
				return false;
			}
			return true;
		}
		
		/**
		 * 将15位的身份证转成18位身份证
		 * 
		 * @param cardId
		 * @return
		 */
		public static String convertCardIdBy15bit(String cardId) {
			String idcard17 = null;
			// 非15位身份证
			if (cardId.length() != 15) {
				return null;
			}
			if (isDigital(cardId)) {
				// 获取出生年月日
				String birthday = cardId.substring(6, 12);
				Date birthdate = null;
				try {
					birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Calendar cday = Calendar.getInstance();
				cday.setTime(birthdate);
				String year = String.valueOf(cday.get(Calendar.YEAR));
				idcard17 = cardId.substring(0, 6) + year + cardId.substring(8);
				char c[] = idcard17.toCharArray();
				String checkCode = "";
				if (null != c) {
					int bit[] = new int[idcard17.length()];
					// 将字符数组转为整型数组
					bit = converCharToInt(c);
					int sum17 = 0;
					sum17 = getPowerSum(bit);
					// 获取和值与11取模得到余数进行校验码
					checkCode = getCheckCodeBySum(sum17);
					// 获取不到校验位
					if (null == checkCode) {
						return null;
					}
					// 将前17位与第18位校验码拼接
					idcard17 += checkCode;
				}
			} else { // 身份证包含数字
				return null;
			}
			return idcard17;
		}
		
		/**  
	     * 15位和18位身份证号码的基本数字和位数验校  
	     *   
	     * @param cardId  
	     * @return  
	     */  
	    public static boolean isCardId(String cardId) {   
	        return cardId == null || "".equals(cardId) ? false : Pattern.matches(   
	                "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", cardId);   
	    }
	    
	    /**  
	     * 15位身份证号码的基本数字和位数验校  
	     *   
	     * @param cardId  
	     * @return  
	     */  
	    public static boolean is15CardId(String cardId) {   
	        return cardId == null || "".equals(cardId) ? false : Pattern.matches(   
	                "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$",   
	                cardId);   
	    }   
	    
	    /**  
	     * 18位身份证号码的基本数字和位数验校  
	     *   
	     * @param cardId  
	     * @return  
	     */  
	    public static boolean is18CardId(String cardId) {   
	        return Pattern   
	                .matches(   
	                        "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",   
	                        cardId);   
	    }   
	    
	    /**  
	     * 数字验证  
	     *   
	     * @param str  
	     * @return  
	     */  
	    public static boolean isDigital(String str) {   
	        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");   
	    }   
	    
	    /**  
	     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值  
	     *   
	     * @param bit  
	     * @return  
	     */  
	    public static int getPowerSum(int[] bit) {   
	        int sum = 0;   
	        if (power.length != bit.length) {   
	            return sum;   
	        }   
	        for (int i = 0; i < bit.length; i++) {   
	            for (int j = 0; j < power.length; j++) {   
	                if (i == j) {   
	                    sum = sum + bit[i] * power[j];   
	                }   
	            }   
	        }   
	        return sum;   
	    }  
	    
	    /**  
	     * 将和值与11取模得到余数进行校验码判断  
	     *   
	     * @param checkCode  
	     * @param sum17  
	     * @return 校验位  
	     */  
	    public static String getCheckCodeBySum(int sum17) {   
	        String checkCode = null;   
	        switch (sum17 % 11) {   
	        case 10:   
	            checkCode = "2";   
	            break;   
	        case 9:   
	            checkCode = "3";   
	            break;   
	        case 8:   
	            checkCode = "4";   
	            break;   
	        case 7:   
	            checkCode = "5";   
	            break;   
	        case 6:   
	            checkCode = "6";   
	            break;   
	        case 5:   
	            checkCode = "7";   
	            break;   
	        case 4:   
	            checkCode = "8";   
	            break;   
	        case 3:   
	            checkCode = "9";   
	            break;   
	        case 2:   
	            checkCode = "x";   
	            break;   
	        case 1:   
	            checkCode = "0";   
	            break;   
	        case 0:   
	            checkCode = "1";   
	            break;   
	        }   
	        return checkCode;   
	    }   
	    
	    /**  
	     * 将字符数组转为整型数组  
	     *   
	     * @param c  
	     * @return  
	     * @throws NumberFormatException  
	     */  
	    public static int[] converCharToInt(char[] c) throws NumberFormatException {   
	        int[] a = new int[c.length];   
	        int k = 0;   
	        for (char temp : c) {   
	            a[k++] = Integer.parseInt(String.valueOf(temp));   
	        }   
	        return a;   
	    }   
	}
	
	
	/**
	 * 判断对象内的所有属性是否全部为空
	 * @param values 对象属性值数组
	 * @return 	true:全部属性值为空,false:全部属性中的值不全为空
	 * waver
	 */
	public static boolean isObjFieldNull( Object[] values ){
		for (Object object : values) {
			if( isNotNull(object) ){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 验证金钱格式
	 * @param value 要验证的值
	 * @param maxLength  整数部分最大长度
	 * @return  true:验证通过,false:验证不通过
	 * waver
	 */
	public static boolean isMoneyType( Object value, Integer maxLength ){
		if( isNull(value) || maxLength == 0 ){
			return false;
		}
		String regex1 = "^[0-9]+\\.[0-9]{0,2}$";
		String regex2 = "^\\d+$";
		String str = value.toString();
		Integer pointNumber = str.indexOf(".");
		if( pointNumber > 0 ){
			//存在小数点
			if( str.split("\\.")[0].length() > maxLength ){
				return false;
			}
		}else{
			//不存在小数
			if( str.length() > maxLength ){
				return false;
			}
		}
		//格式验证
		if( str.matches(regex1) || str.matches(regex2) ){
			return true;
		}else{
			return false;
		}
	}
}