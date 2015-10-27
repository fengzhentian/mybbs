package com.myapp.poi;

import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.Element;

import com.myapp.object.ExcelObject;
import com.myapp.utils.ClassLoaderUtil;
import com.myapp.utils.DateHelper;
import com.myapp.utils.ReflectUtils;
import com.myapp.utils.XmlParser;

public class ExcelHelper
{
	/**
	 * 创建表
	 * 
	 * @param excelObj
	 * @param list
	 * @return
	 */
	public static HSSFWorkbook createWorkbook(ExcelObject excelObj,
			List<Map<String, Object>> list)
	{
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(excelObj.getSheetName());
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		// 合并单元格列
		HSSFCell firstCell = row.createCell(0);
		firstCell.setCellValue(excelObj.getTitleName());
		// 合并第一行中的所有列
		CellRangeAddress region = new CellRangeAddress(0, 0, 0,
				excelObj.getColNameArr().length - 1); // 参数都是从0开始
		sheet.addMergedRegion(region);
		row = sheet.createRow(1);
		for (int index = 0; index < excelObj.getColNameArr().length; index++)
		{
			HSSFCell cell = row.createCell(index);
			cell.setCellValue(excelObj.getColNameArr()[index]);
			cell.setCellStyle(style);
		}

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		// 设置每行每列的值
		for (int i = 0; i < list.size(); i++)
		{
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			Row row1 = sheet.createRow(i + 2);
			// 在row行上创建一个方格
			String[] keys = excelObj.getFieldArr();
			for (int j = 0; j < keys.length; j++)
			{
				Cell cell = row1.createCell(j);
				cell.setCellValue(list.get(i).get(keys[j]) == null ? " " : list
						.get(i).get(keys[j]).toString());
			}
		}
		return wb;
	}

	/**
	 * 将配置项初始化到ExcelObject类中
	 * 
	 * @param printId
	 * @return
	 * @throws MalformedURLException
	 */
	public static ExcelObject initExcelObject(String printId)
	{
		ExcelObject excelObj = new ExcelObject();
		String docPath = "";
		try
		{
			docPath = ClassLoaderUtil.getExtendResource(
					"PrintTemplate/PrintConfig.xml").getPath();
			docPath = URLDecoder.decode(docPath, "UTF-8");
		}
		catch (Exception e)
		{
			return null;
		}

		Document doc = XmlParser.getDocument(docPath);
		Element root = XmlParser.getRootNode(doc);
		List<Element> list = XmlParser.getChildList(root);
		for (Element element : list)
		{
			String id = XmlParser.attrValue(element, "ID");
			if (id.equals(printId))
			{
				String topnum = XmlParser.attrValue(element, "topnum");
				String filedValue = XmlParser.attrValue(element, "FiledValue");
				String colName = XmlParser.attrValue(element, "ColName");
				String showType = XmlParser.attrValue(element, "showType");
				String remark = XmlParser.attrValue(element, "Remark");
				String order = XmlParser.attrValue(element, "order");

				String[] colNameArr = colName.split(",");
				excelObj.setTopnum(topnum);
				excelObj.setColName(colName);
				excelObj.setColNameArr(colNameArr);
				excelObj.setFiledValue(filedValue);
				excelObj.setFieldArr(filedValue.split(","));
				excelObj.setShowArr(showType.split(","));
				excelObj.setTitleName(remark);
				excelObj.setSheetName(remark);
				excelObj.setOrder(order);
				excelObj.setHasValue(true);
				break;
			}
		}
		return excelObj;
	}

	public static Map<String, Object> getFieldMap(Object obj,
			String[] fieldArr, String[] typeArr)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		for (int index = 0; index < fieldArr.length; index++)
		{
			Object value = ReflectUtils.getter(obj, fieldArr[index]);
			if (value == null)
			{
				map.put(fieldArr[index], "");
				continue;
			}

			String valueStr = value.toString();
			if ("3".equals(typeArr[index]))
			{
				// 将long型转为yyyy-MM-dd
				valueStr = DateHelper.getLongMillisToShort(Long
						.parseLong(valueStr));
			}
			map.put(fieldArr[index], valueStr);
		}

		return map;
	}

}
