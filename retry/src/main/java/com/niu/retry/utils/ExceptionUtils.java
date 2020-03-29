package com.niu.retry.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author niuhaijun
 * @date 2020-03-29 15:07
 * @version 1.0
 * @description: xxx
 */
public class ExceptionUtils {

	public static String getStackTrace(final Throwable throwable) {

		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}
}
