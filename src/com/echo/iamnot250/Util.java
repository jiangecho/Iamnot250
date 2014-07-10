package com.echo.iamnot250;

import java.util.Random;

import android.R.string;

public class Util {
	private static Random random = new Random();
	
	private static int a, b, c;
	private static float fa, fb, fc;
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
		case 2:
			expression = generateRightExpressionLevel2();
			break;
		case 3:
			expression = generateRightExpressionLevel3();
			break;
		case 4:
			expression = generateRightExpressionLevle4();
			break;
		case 5:
			expression = generateRightExpressionLevle5();
			break;
		case 6:
			expression = generateRightExpressionLevle6();
			break;
		case 7:
			expression = generateRightExpressionLevel7();
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
		case 2:
			expression = generateWrongExpressionLevel2();
			break;
		case 3:
			expression = generateWrongExpressionLevel3();
			break;
		case 4:
			expression = generateWrongExpressionLevle4();
			break;
		case 5:
			expression = generateWrongExpressionLevle5();
			break;
		case 6:
			expression = generateWrongExpressionLevle6();
			break;
		case 7:
			expression = generateWrongExpressionLevel7();
			break;

		default:
			expression = null;
			break;
		}
		return expression;
	}
	
	// int a + b
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
	
	// int a - b
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
		a = DES_VALUE + b - random.nextInt(250) - 1;
		sb.append(a).append(" - ").append(b);
		
		return sb.toString();
	}
	
	// int a + b, negative
	private static String generateRightExpressionLevel2(){
		int tmp;
		sb.setLength(0);
		a = random.nextInt(100000);
		a = a < 250 ? (a + 250) : a;
		b = a + 250;
		
		if (random.nextBoolean()) {
			sb.append("-").append(a).append(" + ").append(b);
		}else {
			sb.append(b).append(" + (").append("-").append(a).append(")");
		}
		
		return sb.toString();
	}

	// negative
	private static String generateWrongExpressionLevel2(){
		int tmp;
		sb.setLength(0);
		a = random.nextInt(100000);
		a = a < 250 ? (a + 250) : a;
		b = a + 250;
		
		if (random.nextBoolean()) {
			b += random.nextInt(10) + 1;
			sb.append("-").append(a).append(" + ").append(b);
		}else {
			a += random.nextInt(10) + 1;
			sb.append(b).append(" + (").append("-").append(a).append(")");
		}
		
		return sb.toString();
	}

	// int a - b, negative
	private static String generateRightExpressionLevel3(){
		sb.setLength(0);
		a = random.nextInt(100000);
		a = a < 250 ? (a + 250) : a;
		b = a - 250;
		
		if (random.nextBoolean()) {
			int tmp = b - 2 * a;
			if (tmp > 0) {
				sb.append("-").append(a).append(" - ").append(b - 2 * a);
			}else {
				sb.append("-").append(a).append(" - (").append(b - 2 * a).append(")");
				
			}
		}else {
			sb.append(a - 2 * b).append(" - (").append("-").append(b).append(")");
		}
		
		return sb.toString();
	}

	// negative
	private static String generateWrongExpressionLevel3(){
		sb.setLength(0);
		a = random.nextInt(100000);
		a = a < 250 ? (a + 250) : a;
		b = a - 250 - random.nextInt(250) + 1;
		
		if (random.nextBoolean()) {
			sb.append("-").append(a).append(" - ").append(b - 2 * a);
		}else {
			sb.append(a - 2 * b).append(" - (").append("-").append(b).append(")");
		}
		
		return sb.toString();
	}

	// fa + fb
	private static String generateRightExpressionLevle4(){
		sb.setLength(0);
		fa = random.nextFloat();
		
		fa = 250 * fa;
		fa = (float)Math.round(fa * 100) / 100;
		fb = 250f - fa;

		sb.append(fa).append(" + ").append(fb);
		
		return sb.toString();
	}

	// fa + fb
	private static String generateWrongExpressionLevle4(){
		sb.setLength(0);
		fa = random.nextFloat();
		
		fa = (float)Math.round(fa * 100) / 100;
		fa = 250 * fa;
		fb = 250 - fa + random.nextInt(5) + 1;

		sb.append(fa).append(" + ").append(fb);
		
		return sb.toString();
	}
	
	//fa - fb
	private static String generateRightExpressionLevle5(){
		sb.setLength(0);
		fa = random.nextFloat();
		
		fa = (float)Math.round(fa * 100) / 100;
		fa = 250 * fa;
		fb = 250 + fa;

		sb.append(fb).append(" - ").append(fa);
		
		return sb.toString();
	}

	// fa - fb
	private static String generateWrongExpressionLevle5(){
		sb.setLength(0);
		fa = random.nextFloat();
		
		fa = (float)Math.round(fa * 100) / 100;
		fa = 250 * fa;
		fb = 250 + fa + random.nextInt(5) + 1;

		sb.append(fb).append(" - ").append(fa);
		
		return sb.toString();
	}
	
	//fa + fb & negative
	private static String generateRightExpressionLevle6(){
		sb.setLength(0);
		fa = random.nextFloat();
		
		fa = (float)Math.round(fa * 250) / 100;
		fb = 250 + fa;

		if (random.nextBoolean()) {
			sb.append("-").append(fa).append(" + ").append(fb);
		}else {
			sb.append(fb).append(" + (").append("-").append(fa).append(")");
		}
		
		return sb.toString();
	}

	//fa + fb & negative
	private static String generateWrongExpressionLevle6(){
		sb.setLength(0);
		fa = random.nextFloat();
		
		fa = (float)Math.round(fa * 250) / 100;
		fb = 250 + fa + random.nextInt(250) + 1;

		if (random.nextBoolean()) {
			sb.append("-").append(fa).append(" + ").append(fb);
		}else {
			sb.append(fb).append(" + (").append("-").append(fa).append(")");
		}
		
		return sb.toString();
	}
	
	// fa - fb, negative
	private static String generateRightExpressionLevel7(){
		sb.setLength(0);

		fa = random.nextFloat();
		fa = (float)Math.round(fa * 250) / 100;
		fa = a < 250 ? (a + 250) : a;
		fb = fa - 250;

		if (random.nextBoolean()) {
			sb.append("-").append(fa).append(" - ").append(fb - 2 * fa);
		}else {
			sb.append(fa - 2 * fb).append(" - (").append("-").append(fb).append(")");
		}
		
		return sb.toString();
	}
	
	// fa - fb, negative
	private static String generateWrongExpressionLevel7(){
		sb.setLength(0);

		fa = random.nextFloat();
		fa = (float)Math.round(fa * 250) / 100;
		fa = a < 250 ? (a + 250) : a;
		fb = fa - 250 + random.nextInt(250) + 1;

		if (random.nextBoolean()) {
			sb.append("-").append(fa).append(" - ").append(fb - 2 * fa);
		}else {
			sb.append(fa - 2 * fb).append(" - (").append("-").append(fb).append(")");
		}
		
		return sb.toString();
	}

	
}
