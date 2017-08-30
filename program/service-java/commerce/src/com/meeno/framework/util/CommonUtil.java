package com.meeno.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;

import sun.misc.BASE64Encoder;

public class CommonUtil {

	public static boolean isZeroLengthTrimString(String value) {
		return value == null || value.trim().length() == 0;
	}

	public static boolean isNotZeroLengthTrimString(String value) {
		return !(isZeroLengthTrimString(value));
	}

	public static String getParamValue(PageContext pageContext, String name) {
		String satrribute = pageContext.findAttribute(name).toString();
		String value = pageContext.getRequest().getParameter(satrribute);
		if (value == null || value.trim().length() == 0) {
			return "";
		}
		return value;
	}

	public static String getParamValue(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		if (isZeroLengthTrimString(value)) {
			return "";
		}
		return value;
	}

	public static Date getAddDate(String yyyy_MM_DD, int days) {
		if (yyyy_MM_DD == null) {
			return new Date();
		}
		String[] calendar = yyyy_MM_DD.split("-");
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Integer.parseInt(calendar[0]), Integer.parseInt(calendar[1]) - 1, Integer.parseInt(calendar[2]), 0, 0,
				0);
		gc.add(5, days);
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return java.sql.Date.valueOf(sdf.format(gc.getTime()));
	}

	public static String getIdSQLParam(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return null;
		}
		StringBuffer param = new StringBuffer("");
		for (int i = 0; i < ids.length; i++) {
			if (i > 0) {
				param.append(",");
			}
			param.append("?");
		}
		return param.toString();
	}

	public static String getCategoryOrderStr(List<String> ids) {
		if (ids == null || ids.size() == 0) {
			return "";
		}
		StringBuffer param = new StringBuffer("");
		for (int i = 0; i < ids.size(); i++) {
			if (i > 0) {
				param.append(",");
			}
			param.append(ids.get(i));
		}
		return param.toString();
	}

	public static String getIdSQLValue(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return null;
		}
		StringBuffer param = new StringBuffer("");
		for (int i = 0; i < ids.length; i++) {
			if (i > 0) {
				param.append(",");
			}
			param.append(String.valueOf(ids[i]));
		}
		return param.toString();
	}
	
	public static String getArrSQLValue(Integer[] values) {
		if (values == null || values.length == 0) {
			return null;
		}
		StringBuffer param = new StringBuffer("");
		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				param.append(",");
			}
			param.append(String.valueOf(values[i]));
		}
		return param.toString();
	}

	public static Date getDateValue(String value) {
		boolean isTime = value.trim().indexOf(" ") > 0;
		String formatStyle = "";
		if (isTime) {
			formatStyle = Parameter.getInstance().getProp().getProperty(Constant.FORMAT_DATE_STYLE,
					"yyyy-MM-dd HH:mm:ss");
		} else {
			formatStyle = Parameter.getInstance().getProp().getProperty(Constant.FORMAT_DATE_STYLE, "yyyy-MM-dd");
		}
		try {
			return getSampleDateFormat(formatStyle).parse(value);
		} catch (ParseException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}

	public static SimpleDateFormat getSampleDateFormat(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf;
	}

	public static String getURL(String url, String menuId, String method) {
		return url + "?actionMethod=" + method + "&menuId=" + menuId;
	}

	/**
	 * 密码加密的方法
	 * 
	 * @param password
	 * @return
	 */
	public static String encrypt(String password) {
		String enStr = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update("meeno".getBytes());
			byte[] pswKey = md.digest();
			DESKeySpec desKeySpec = new DESKeySpec(pswKey);
			SecretKeyFactory desKeyFac = SecretKeyFactory.getInstance("DES");
			SecretKey desKey = desKeyFac.generateSecret(desKeySpec);
			Cipher encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, desKey);
			byte[] enPassword = encryptCipher.doFinal(password.getBytes("UTF8"));
			enStr = new BASE64Encoder().encode(enPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enStr;
	}

	/**
	 * 32位md5加密方法
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static String MD5_32(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
			System.out.println("MD5(" + sourceStr + ",32) = " + result);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * 中文乱码转换
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeStr(String str) {
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			String retValue = str;
			// Map<String,String> keyMap = DictionaryFactory.getKeyWordMap();
			// if(keyMap!=null){
			// if (!keyMap.isEmpty()) {
			// Set<String> keys = keyMap.keySet();
			// Iterator<String> iter = keys.iterator();
			// while (iter.hasNext()) {
			// String key = iter.next();
			// String value = (String) keyMap.get(key);
			// if (retValue.contains(key)) {
			// retValue = retValue.replace(key, value); // 对于符合map中的key值实现替换功能
			// }
			// }
			// }
			// }
			return retValue;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 向客户端浏览器输出字符串.
	 * 
	 * @param response
	 * @param bean
	 */
	public static void toJson(HttpServletResponse response, Object bean) {
		try {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter()
					.write(JSON.toJSONString(bean, SerializerFeature.WriteDateUseDateFormat,
							SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue,
							SerializerFeature.WriteNullStringAsEmpty));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 
	 * @param response
	 * @param bean
	 */
	public static String toJson(Object bean) {

			return JSON.toJSONString(bean, SerializerFeature.WriteDateUseDateFormat,
							SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue,
							SerializerFeature.WriteNullStringAsEmpty);
	}

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成随机数字字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumString(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 判断字符串是否匹配正则表达式.
	 *
	 * @param aimStr
	 *            目标字符串
	 * @param regex
	 *            正则表达式
	 * @return 匹配结果(true:匹配, false:不匹配)
	 **/
	public static boolean getMatchResult(final String aimStr, final String regex) {
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(aimStr);
			return matcher.matches();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	/**
	 * 获取分页信息
	 * 
	 * @param requestData
	 *            请求数据
	 * @return 分页信息对象
	 */
	public static CutPageBean getCutPageBean(JSONObject requestData) {
		CutPageBean pageBean = new CutPageBean();
		pageBean.setPageSize(10);
		pageBean.setCurrentPage(1);
		if (requestData == null) {
			return pageBean;
		}
		String pageIndex = requestData.getString("pageIndex");
		if (!StringUtils.isEmpty(pageIndex)) {
			pageBean.setCurrentPage(Integer.valueOf(pageIndex));
		}
		String pageSize = requestData.getString("pageSize");
		if (!StringUtils.isEmpty(pageSize)) {
			pageBean.setPageSize(Integer.valueOf(pageSize));
		}
		return pageBean;
	}

	/**
	 * 将json数组转化为Long型
	 * 
	 * @param str
	 * @return
	 */
	public static Long[] getJsonToLongArray(JSONArray jsonArray) {
		try {
			Long[] arr = new Long[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				arr[i] = jsonArray.getLong(i);
			}
			return arr;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将json数组转化为Long型
	 * 
	 * @param str
	 * @return
	 */
	public static Integer[] parseIntegerArr(JSONArray jsonArray) {
		try {
			Integer[] arr = new Integer[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				arr[i] = jsonArray.getInteger(i);
			}
			return arr;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 检查字符串是否为null，为null则返回空字符串
	 * @param src
	 * @return
	 */
	public static String replaceNullWithEmptyStr(String src){
		if (src == null) {
			return "";
		}
		return src;
	}

	/**
     * 发送https请求
     * 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
            System.out.println(jsonObject);
        } catch (ConnectException ce) {
//            log.error("连接超时：{}", ce);
        } catch (Exception e) {
//            log.error("https请求异常：{}", e);
        }
        return jsonObject;
    }
    
    /**
     * URL编码（utf-8）
     * 
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static <T> List<T> subList(List<T> list, Integer pageIndex, Integer pageSize){
    	if ((list == null) || (list.size() == 0)) {
			return list;
		}
    	if ((pageIndex != null) && (pageSize != null)) {
			//需要分页
			if ((pageIndex - 1)*pageSize > (list.size() - 1)) {
				//起始位置已越界
				return list;
			}else {
				int startIndex = (pageIndex - 1)*pageSize;
				int endIndex = (pageIndex*pageSize > list.size()) ? list.size() : pageIndex*pageSize;
				return list.subList(startIndex, endIndex);
			}
		}
    	return list;
    }
    
    /**
     * 获取发起请求的客户端地址
     * @param request
     * @return
     */
    public static String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
    
    /**
     * 获取匿名name
     * @param name
     * @return
     */
    public static String getAnonymousName(String name){
    	try {
    		if (CommonUtil.isZeroLengthTrimString(name)) return "";
        	String x = "***";
        	if (name.length() == 1) {
    			return name + x;
    		}
        	StringBuffer an  = new StringBuffer();
        	an.append(name.charAt(0))
        	.append(x)
        	.append(name.charAt(name.length() - 1));
        	return an.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
    }
}
