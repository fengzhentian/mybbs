package com.myapp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对提交进入的数据进行安全检查并处理
 */
public class SecurityUtil
{
	/**
	 * 对入参信息进行sql过滤，Script过滤和尖括号的过滤
	 * @param userInput 用户的入参字符串
	 * @return 过滤后的出参字符串
	 */
    public static String inPutAllFilter(String userInput)
    {
        if (userInput == null)
            return "";
        String tempInput = userInput;
        tempInput = formatDisableScripting(tempInput);
        tempInput = formatRemoveSQL(tempInput);
        tempInput = formatAngleBrackets(tempInput);
        tempInput = urnHtml(tempInput);

        return tempInput.trim();
    }
	/**
	 * 格式化script格式串
	 * @param strInput 输入字符串
	 * @return
	 */
    private static String formatDisableScripting(String strInput)
    {
    	String tempInput = strInput;
    	tempInput = filterStrings(tempInput);
        return tempInput;
    }
	/**
	 * 过滤字符串
	 * @param strInput 原始字符串
	 * @return 过滤完得字符串
	 */
    private static String filterStrings(String strInput)
	{
		String tempInput = strInput;
		List<String> listStrings = new ArrayList<String>();
		listStrings.add("<script[^>]*>.*?</script[^><]*>");
		listStrings.add("<input[^>]*>.*?</input[^><]*>");
		listStrings.add("<object[^>]*>.*?</object[^><]*>");
		listStrings.add("<embed[^>]*>.*?</embed[^><]*>");
		listStrings.add("<applet[^>]*>.*?</applet[^><]*>");
		listStrings.add("<form[^>]*>.*?</form[^><]*>");
		listStrings.add("<option[^>]*>.*?</option[^><]*>");
		listStrings.add("<select[^>]*>.*?</select[^><]*>");
		listStrings.add("<iframe[^>]*>.*?</iframe[^><]*>");
		listStrings.add("<iframe.*?<");
		listStrings.add("<ilayer[^>]*>.*?</ilayer[^><]*>");
		listStrings.add("<form[^>]*>");
		listStrings.add("</form[^><]*>");
		listStrings.add("javascript:");
		listStrings.add("vbscript:");
		listStrings.add("alert[\\s(&nbsp;)]*\\([\\s(&nbsp;)]*'?[\\s(&nbsp;)]*[\"(&quot;)]?");
		for (String s : listStrings)
		{
			Pattern p = Pattern.compile(s, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Matcher matcher = p.matcher(tempInput);
			tempInput = matcher.replaceAll(" ");
		}
		return tempInput;
	}
    /**
     * 过滤SQL
     * @param strSQL 原始字符串
     **/
    public static String formatRemoveSQL(String strSQL)
    {
        String badStatementExpression = ";|--|create|drop|select|insert|delete|update|union|sp_|xp_|exec|/\\*.*\\*/|declare";
        Pattern p = Pattern.compile(badStatementExpression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(strSQL);
		String tempInput = matcher.replaceAll(" ").replace("\'", "").replace("\\", "").replace("\"", "").replace(",","");
		return tempInput;
    }
	/**
	 * 格式化尖括号字符串
	 * @param strInput
	 * @return
	 */
	public static String formatAngleBrackets(String strInput)
    {
    	String tempInput = strInput.replace("<", "");
    	tempInput = tempInput.replace(">", "");
        return tempInput;
    }
    /**
     * 去掉xml中的一些特殊字符
     * @param strInput
     * @return
     */
    public static String urnHtml(String strInput)
    {
    	String[] aryReg = { "'", "<", ">", "\"", "||", "/", "|", " " };
        strInput = strInput.replace("%","％");
        strInput = strInput.replace("&", "＆");
        strInput = strInput.replace(",", "，");
        strInput = strInput.replace(";", "；");
        for (int i = 0; i < aryReg.length; i++)
        {
            strInput = strInput.replace(aryReg[i], "");
        }
        return strInput;
    }
}
