package com.taf.base;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyLogger.class);

	public static Logger getCurrentLog() {
		
		return LOGGER;
	}

	public static String getCallInfo() {

		String callInfo;
		String className = Thread.currentThread().getStackTrace()[3].getClassName();
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
		int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

		callInfo = className + ":" + methodName + " " + lineNumber+ "==>  ";
		return callInfo;

	}



	public static void debug(Object message) {

		getCurrentLog().debug(getCallInfo() + message);
	}

	public static void debug(Object message, Throwable t) {
		getCurrentLog().debug(getCallInfo() + message, t);
	}

	public static void error(String message) {

		getCurrentLog().error(message);
	}

	public static void error(Object message, Throwable t) {
		getCurrentLog().error(getCallInfo() + message, t);
	}



	public static void info(String message) {

		getCurrentLog().info(message);
	}
	
	public static void info(Object message , Throwable t) {

		getCurrentLog().info(getCallInfo()+message, t);
	}



	public static void warn(String message) {
		getCurrentLog().warn(message);
	}

	public static void warn(Object message, Throwable t) {
		getCurrentLog().warn(getCallInfo() + message, t);
	}
}
