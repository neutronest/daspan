/*
 * Copyright (c) 2018.
 */

package com.framex.io

import com.framex.core.Expr.{ExDouble, ExString}
import com.framex.core.{ElemX, FrameX}
import com.framex.utils.{CSVHandler, Constants}
import org.nd4j.linalg.api.ndarray.INDArray

import scala.collection.mutable.ListBuffer


object FrameXIO {


  def readCSV(fileName: String,
              sep: String): FrameX = {

    val bufferedSource = CSVHandler.getSourceFromCSV(fileName)
    val headerNames = CSVHandler.getHeaderFromSource(bufferedSource)
    val csvData = CSVHandler.getDataFromSource(bufferedSource)
    val csvDataT = CSVHandler.getColumnBasedData(csvData)

    var dataBuffer = new ListBuffer[List[ElemX]]()
    val columnTypes = csvDataT.map(CSVHandler.getDataTypeByColumn)
    for ((columnData, columnType) <- csvDataT.zip(columnTypes)) {
      if (columnType == Constants.ELEMX_TYPE_DOUBLE) {
        dataBuffer += columnData.map(s => ElemX(ExDouble(s.toDouble)))
      } else {
        dataBuffer += columnData.map(s => ElemX(ExString(s)))
      }
    }
    FrameX(dataBuffer.toList, headerNames)
  }

  def toCSV(df: FrameX, fileName: String): Unit = {
    ???
  }

  def readND4J(nd4jData: INDArray): FrameX = {
    val data = nd4jData.transpose().toDoubleMatrix().map(f => f.map(ElemX.wrapper).toVector).toVector
    FrameX(data)
  }

  def toND4J(df: FrameX): Any = {
    ???
  }


}
