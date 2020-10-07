package ru.appline.logic;

public class Result {
	
	private double result;
	
	public Result(double leftArg, double rightArg, char MathOp) {
		if(MathOp == '*') {
			this.result = leftArg * rightArg;
		} else if(MathOp == '+') {
			this.result = leftArg + rightArg;
		} else if(MathOp == '-') {
			this.result = leftArg - rightArg;
		} else if(MathOp == '/') {
			this.result = leftArg / rightArg;
		}
	}
	
	public double getResult() {
		return result;
	}
	
	public void setResult(double result) {
		this.result = result;
	};
	
	
}
