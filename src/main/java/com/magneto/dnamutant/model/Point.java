package com.magneto.dnamutant.model;

public class Point {
	public char dnaStructure[][];
	public int row;
	public int column;
	public char lastChar;
	public char currentChar;
	public int lastIndex;
	
	public Point(char[][] dnaStructure, int row, int column) {
		super();
		this.dnaStructure = dnaStructure;
		this.lastIndex = dnaStructure.length - 1;
		this.row = row;
		this.column = column;
		this.lastChar = dnaStructure[row][column];
	}


	public void setCoordinates(int row, int column) {
		this.row = row;
		this.column = column;
	}
}
