package com.myapp.common;

/**
 * 自定义返回结果
 *
 */
public class ResultDefined
{
	// 返回结果标识
	private String result;
	// 返回结果说明
	private String message;

	public String isResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}