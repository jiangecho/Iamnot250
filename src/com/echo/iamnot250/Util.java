package com.echo.iamnot250;

import java.util.Random;

import android.R.string;

public class Util {
	private static Random random = new Random();
	
	private static int a, b, c;
	private static StringBuilder sb = new StringBuilder();
	private static final int DES_VALUE = 250;
	
	public static String getRightExpression(int level){
		
		String expression;
		switch (level) {
		case 0:
			expression = generateRightExpressionLevel0();
			break;
		case 1:
			expression = generateRightExpressionLevel1();
			break;

		default:
			expression = null;
			break;
		}
		return expression;
	}
	
	public static String getWrongExpression(int level){
		
		String expression;
		switch (level) {
		case 0:
			expression = generateWrongExpressionLevel0();
			break;
		case 1:
			expression = generateWrongExpressionLevel1();
			break;

		default:
			expression = null;
			break;
		}
		return expression;
	}
	
	private static String generateRightExpressionLevel0(){
		sb.setLength(0);
		
		a = random.nextInt(DES_VALUE);
		b = DES_VALUE - a;
		sb.append(a).append(" + ").append(b);
		
		return sb.toString();
	}
	
	private static String generateWrongExpressionLevel0(){
		sb.setLength(0);
		a = random.nextInt(DES_VALUE);
		b = DES_VALUE - a + random.nextInt(10) + 1;

		sb.append(a).append(" + ").append(b);
		
		return sb.toString();
	}
	
	private static String generateRightExpressionLevel1(){
		sb.setLength(0);
		
		b = random.nextInt(DES_VALUE);
		a = DES_VALUE + b;
		sb.append(a).append(" - ").append(b);
		
		return sb.toString();
	}
	
	private static String generateWrongExpressionLevel1(){
		sb.setLength(0);
		b = random.nextInt(DES_VALUE);
		a = DES_VALUE + b - random.nextInt(5) - 1;
		sb.append(a).append(" - ").append(b);
		
		return sb.toString();
	}
	

}
