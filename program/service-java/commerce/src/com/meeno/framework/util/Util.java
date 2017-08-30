package com.meeno.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.meeno.framework.bean.BaseEntity;
import com.meeno.framework.exception.BusinessCheckException;
import com.meeno.framework.exception.BusinessRuntimeException;

/**
 * 该类是项目中常用到的一些公共方法.
 *
 * @author winner pan
 */
public class Util {
	/**
	 * 一次读取的字节数.
	 * **/
	private static final int READ_BYTE_ONCE = 1024;
	/**
	 * 日志常量.
	 */
	private static final Logger LOGGER = Logger.getLogger(Util.class);

	/**
	 * 默认的私有的构造函数，不能生成新的实例.
	 * **/
	private Util() {

	}


	/**
	 *判断客户端请求是否是Ajax请求.
	 *
	 * @param request
	 *            servlet内置对象.
	 * @return 返回是或否
	 */
	public static boolean isAjaxRequest(final HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(header);
	}

	

	/**
	 * 根据对象获取整数.
	 *
	 * @param obj
	 *            输入对象.
	 * @return Integer
	 */
	public static int getInteger(final Object obj) {
		if (obj == null || obj.equals("")) {
			return 0;
		}
		return Integer.valueOf(obj.toString()).intValue();
	}

	/**
	 * 根据对象获取大精度数据对象.
	 *
	 * @param obj
	 *            输入对象.
	 * @return Integer
	 */
	public static BigDecimal getBigDecimal(final Object obj) {
		if (obj == null || obj.equals("")) {
			return new BigDecimal("0");
		}
		return new BigDecimal(obj.toString());
	}

	/**
	 * 根据数据ID获取数据列表中的对象.
	 *
	 * @param datas
	 *            数据列表.
	 * @param id
	 *            查询ID.
	 * @return 返回查询的数据对象.
	 */
	public static BaseEntity getObject(final List<? extends BaseEntity> datas,
			final Long id) {
		if (datas == null || id == null) {
			return null;
		}
		for (int i = 0; i < datas.size(); i++) {
			if (datas.get(i).getId().intValue() == id.intValue()) {
				return datas.get(i);
			}
		}
		return null;
	}



	/**
	 * 向客户端浏览器输出字符串.
	 *
	 * @param outputStr
	 *            需要输出的字符串.
	 * @return 页面信息
	 */
	public static String printString(final String outputStr) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.setAttribute(Constant.AJAX_OUT_DATA, outputStr);
		return "frame/out_data.jsp";
	}

	/**
	 * 向客户端浏览器输出字符串.
	 *
	 * @param outputStr
	 *            需要输出的字符串.
	 * @return 页面信息
	 */
	public static String printErrorString(final String outputStr) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.setAttribute(Constant.AJAX_ERROR, "true");
		request.setAttribute(Constant.AJAX_OUT_DATA, outputStr);
		return "frame/out_data.jsp";
	}

	/**
	 * 根据页面变量名，获取页面上JSTL中的变量的数值.
	 *
	 * @param pageContext
	 *            页面内置对象.
	 *@param name
	 *            页面上JSTL变量名
	 *@return 返回变量名的值
	 * **/
	public static String getParamValue(final PageContext pageContext,
			final String name) {
		String satrribute = pageContext.findAttribute(name).toString();
		String value = pageContext.getRequest().getParameter(satrribute);
		if (value == null || value.trim().length() == 0) {
			return "";
		}
		return value;
	}

	/**
	 * 根据页面变量名，获取页面上JSTL中的变量的数值.
	 *
	 * @param pageContext
	 *            页面内置对象.
	 *@param name
	 *            页面上JSTL变量名
	 *@return 返回变量名的值
	 * **/
	public static BigDecimal getBigDecimal(final PageContext pageContext,
			final String name) {
		String value = null;
		if (pageContext.findAttribute(name) == null) {
			value = "1";
		} else {
			value = pageContext.findAttribute(name).toString();
		}
		return new BigDecimal(value);
	}

	/**
	 * 根据页面变量名，获取页面上Request中的变量的数值.
	 *
	 * @param request
	 *            页面内置对象.
	 *@param name
	 *            页面变量名
	 *@return 返回变量名的值
	 * **/
	public static String getParamValue(final HttpServletRequest request,
			final String name) {
		String value = request.getParameter(name);
		if (value == null || value.trim().length() == 0) {
			return "";
		}
		return value;
	}

	/**
	 * 根据文件路径下载文件.
	 *
	 * @param response
	 *            Servlet 内置对象.
	 * @param fileName
	 *            文件路径
	 * @return 返回是否下载
	 * @throws BusinessCheckException
	 *             抛出错误信息.
	 */
	public static boolean downloadFile(final HttpServletResponse response,
			final String fileName) throws BusinessCheckException {
		// 获取系统配置路径
		String dataPath = "";
		File exportDataFloder = new File(dataPath);
		if (!exportDataFloder.exists()) {
			throw new BusinessRuntimeException("系统设置中'指定基础数据导出目录'地址不存在.");
		}
		if (!dataPath.endsWith("/") || dataPath.endsWith("\\")) {
			dataPath = dataPath + "/";
		}
		File file = new File(dataPath + fileName);
		if (!file.exists()) {
			return false;
		}
		try {
			FileInputStream fileStream = new FileInputStream(file);
			int i = fileStream.available();
			byte[] data = new byte[i];
			fileStream.read(data);
			fileStream.close();
			response.reset();
			response.setContentType("application/octet-stream"); // 设置返回的文件类型
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ file.getName() + "\"");
			ServletOutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
			throw new BusinessCheckException("下载文件出错！");
		}
		return true;
	}

	


	/**
	 * 判断参数是否是Number型.
	 *
	 * @param str
	 *            参数
	 * @return 返回真和假
	 */
	public static boolean isNumeric(final String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否匹配正则表达式.
	 *
	 *@param aimStr
	 *            目标字符串
	 *@param regex
	 *            正则表达式
	 *@return 匹配结果(true:匹配, false:不匹配)
	 * **/
	public static boolean getMatchResult(final String aimStr, final String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(aimStr);
		return matcher.matches();
	}

	/***
	 * 把数组转为字符串.
	 *
	 * @param objs
	 *            输入的数组
	 * @return 返回字符串
	 */
	public static String getStringFromArray(final Long[] objs) {
		if (objs == null || objs.length == 0) {
			return "";
		}
		StringBuffer strBf = new StringBuffer(objs[0] + "");
		for (int i = 1; i < objs.length; i++) {
			strBf.append(",").append(objs[i] + "");
		}
		return strBf.toString();
	}

	/***
	 * 把数组转为字符串.
	 *
	 * @param objs
	 *            输入的数组
	 * @return 返回字符串
	 */
	public static String getStringFromArray(final long[] objs) {
		if (objs == null || objs.length == 0) {
			return "";
		}
		StringBuffer strBf = new StringBuffer(objs[0] + "");
		for (int i = 1; i < objs.length; i++) {
			strBf.append(",").append(objs[i] + "");
		}
		return strBf.toString();
	}

	/**
	 * 按指定长度截断字符串.
	 *
	 * @param sourceStr 源字符串
	 * @param size 长度
	 * @return 截取长度后的字符串
	 */
	public static String substring(final String sourceStr, final int size) {
		String s = sourceStr;
		if (size <= 0) {
			return "";
		} else if (s == null || s.getBytes().length <= size) {
			return s;
		}

		int index = 0;
		byte[] bs = s.getBytes();
		if ((bs[size] >> 7) == 0x0 || ((bs[size] >> 6) & 0x03) == 0x03) {
			index = size;
		} else {
			for (int i = size - 1; i >= 0; i--) {
				if (((bs[i] >> 6) & 0x03) == 0x03) {
					index = i;
					break;
				}
			}
		}
		return new String(Arrays.copyOf(s.getBytes(), index));
	}

	static final String SEARCH_KEY = "new Ext.grid.TableGrid(";
	static int searchKeyLen = SEARCH_KEY.length();

	public static void main(String[] args) throws IOException {
		String dirs = "c:\\logs\\";// "D:\\workspace3.4\\ops2\\WebContent\\WEB-INF\\jsp\\ops2";
		File director = new File(dirs);
		FileWriter requestContentFile = new FileWriter(dirs + "request.txt");

		for (File file : director.listFiles()) {
			if (file.getName().startsWith("ops2.")) {
				BufferedReader input = new BufferedReader(new FileReader(file));
				String line = null;
				while ((line = input.readLine()) != null) {
					if (line.indexOf("请求访问:/") > 0) {
						line = line.substring(0, line.indexOf("INFO")) + "\t"
								+ line.substring(line.indexOf(":/") + 2) + "\n";
						requestContentFile.write(line);
					}

				}
				input.close();
			}
		}
		requestContentFile.close();

	}

	/**
	 * 截取字符串长度.
	 * @param sourceStr 字符串
	 * @param assignedLength 长度
	 * @return 截取长度后的字符串
	 */
	public static String getAssignedLengthStr(final String sourceStr,
			final int assignedLength) {
		return substring(sourceStr, assignedLength);
	}

	/**
	 * 获取Excel单元格内容字符串.
	 *
	 * @param column
	 *            单元格的X坐标值.
	 * @param row
	 *            Excel单元个所在行对象.
	 * @return 返回单元格内容字符串.
	 */
	public static String getCellValueNew(final int column, final HSSFRow row) {
		HSSFCell cell = row.getCell((short) column);

		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			return cell.getRichStringCellValue().getString().trim();
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			return  String.format("%.0f",cell.getNumericCellValue());
		}
		return "";
	}
	public static String getCellValue(final int column, final HSSFRow row) {
		HSSFCell cell = row.getCell((short) column);

		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			return cell.getRichStringCellValue().getString().trim();
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue()).trim();
		}
		return "";
	}
	
	public static String getCellValueNotTrim(final int column, final HSSFRow row) {
		HSSFCell cell = row.getCell((short) column);

		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			return cell.getRichStringCellValue().getString();
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return "";
	}
	/**
	 * 获取某一列的所有值
	 * @param colum 列
	 * @param sheet Excel文件
	 * @return 值(字符串数组)
	 */
	public static String[] getColumCellValues(final int colum , final HSSFSheet sheet){
		if(sheet == null){
			return new String[]{};
		}
		int rows = sheet.getPhysicalNumberOfRows();
		if(rows < 2){//第一行为表头，值从第二行开始
			return new String[]{};
		}
		List<String> list = new ArrayList<String>();
		for(int i = 1 ; i < rows ; i ++ ){
			HSSFRow row = sheet.getRow(i);
			if(row == null){
				continue ;
			}
			String cellValue = Util.getCellValueNew(colum, row).trim();
			if(CommonUtil.isZeroLengthTrimString(cellValue)){
				continue ;
			}
			list.add(cellValue);
		}
		return list.toArray(new String[]{});
	}
	
	/**
	 * 向客户端浏览器输出字符串.
	 *
	 * @param response
	 *            servlet内置对象.
	 *@param outputStr
	 *            需要输出的字符串.
	 *@throws IOException
	 *             输出报错信息.
	 * **/
	public static void printString(final HttpServletResponse response, final String outputStr) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(outputStr);
		pw.flush();
	}
	
	/**
	 * 向客户端浏览器输出JSON字符串
	 * 
	 * @param response
	 *            HttpServletResponse内置对象.
	 * @param outputStr
	 *            JSON字符串
	 * @throws IOException
	 *             异常信息
	 */
	public static void printJsonStr(final HttpServletResponse response, final String outputStr) throws IOException{
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(outputStr);
		pw.flush();
	}
	
	/**
	 * 日期时间转换成XMLGregorianCalendar
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            gc.setTimezone(1);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return gc;
    }
}
