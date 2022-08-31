package org.example;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameOfLife {

  private  int verticalSize;
  private  int horizontalSize;
  private  int numberOfCycles;
  private  char[][] originalPlayingField;

   public void game(String fileNameInput, String fileNameOutput) {
   inputFile(fileNameInput);
   lifeCycle( );
   outputFile(fileNameOutput);
 }

  public  void inputFile(String fileNameInput)  {
    String path="src/test/resources/";
  try {FileReader fr = new FileReader(path+fileNameInput);
    BufferedReader reader = new BufferedReader(fr);
    String line = reader.readLine( );
    String[] taskСonditions = line.split(",");
    verticalSize=Integer.parseInt(taskСonditions[0]);
    horizontalSize= Integer.parseInt(taskСonditions[1]);
    numberOfCycles=Integer.parseInt(taskСonditions[2]);
    char[][] input = new char[verticalSize][horizontalSize];
    char[] tmp ;
    for (int i = 0; i < verticalSize; i++) {
      line = reader.readLine( );
      if (line==null){ break;}
      line = line.replace(" ", "");
      tmp = line.toCharArray( );
      for (int j = 0; j < horizontalSize; j++) {input[i][j] = tmp[j];}}
    originalPlayingField =input;}
  catch (IOException e) {
    e.printStackTrace( ); }
  }

  public  void lifeCycle() {
    char[][] newLife;
    for (int life = 0; life < numberOfCycles; life++) {
      newLife = new char[verticalSize][horizontalSize];
      for (int i = 0; i < verticalSize; i++) {
        for (int j = 0; j < horizontalSize; j++) {
          newLife[i][j] = viabilityCheck(i, j);}}
      originalPlayingField = newLife; }
  }

  private char viabilityCheck(int verticallyCoordinate, int horizontalCoordinate) {
    int counter = 0;
    char celuli = originalPlayingField[verticallyCoordinate][horizontalCoordinate];
    char tmp;
    int verticali;
    int horizontali;
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        verticali = (verticallyCoordinate + i + verticalSize) % verticalSize;
        horizontali = (horizontalCoordinate + j + horizontalSize) % horizontalSize;
        tmp = originalPlayingField[verticali][horizontali];
        if (tmp == 'X') {counter++;}}
    }
    if (celuli == 'X') { counter--; return getCellByState(shouldAliveCellLive(counter));}
    else { return getCellByState(shouldDeadCellLive(counter));}
  }

  private boolean shouldAliveCellLive(int counter) {
    return counter > 1 && counter < 4;
  }

  private boolean shouldDeadCellLive(int counter) {
    return counter == 3;
  }

  private char getCellByState(boolean state) {
    return state ? 'X' : 'O';
  }

  public void outputFile(String fileNameOutput) {
    String path = "src/test/resources/";
    StringBuilder stringBuilder = new StringBuilder( );
    char[][] tmp = originalPlayingField;
    for (int i = 0; i < verticalSize; i++) {
      for (int j = 0; j < horizontalSize; j++) {
        stringBuilder.append(tmp[i][j]).append(" ");
      }
      stringBuilder.deleteCharAt(stringBuilder.length( ) - 1);
      stringBuilder.append("\n");
      try {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileNameOutput)));
        out.append(stringBuilder);
        out.close( );
      } catch (IOException e) {
        e.printStackTrace( );}}
  }
}
