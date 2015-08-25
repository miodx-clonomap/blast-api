package ohnosequences.blast

import ohnosequences.datasets._, dataSets._
import api._

case object data {

  trait AnyBlastOutputType extends AnyDataType {

    // get the output record from here
    // could be unconvenient in that you need a concrete expression here
    type OutputRecord <: AnyBlastOutputRecord
    val outputRecord: OutputRecord
  }
  abstract class BlastOutputType[OR <: AnyBlastOutputRecord](val outputRecord: OR) extends AnyBlastOutputType {

    type OutputRecord = OR
  }

  trait AnyBlastOutput extends AnyData {

    type DataType <: AnyBlastOutputType
    val dataType: DataType

    type BlastExpression <: AnyBlastExpression { type OutputRecord = DataType#OutputRecord }
    val blastExpression: BlastExpression
  }

  abstract class BlastOutput[
    BOT <: AnyBlastOutputType,
    BE <: AnyBlastExpression { type OutputRecord = BOT#OutputRecord }
  ](
    val dataType: BOT,
    val blastExpression: BE
  ) extends AnyBlastOutput {

    type DataType = BOT
    type BlastExpression = BE
  }
}
