package ohnosequences.blast

import ohnosequences.cosas._, types._, records._, klists._

package object api {

  type AnyBlastOptionsRecord = AnyRecordType { type Keys <: AnyProductType { type Types <: AnyKList.Of[AnyBlastOption] } }

  implicit def blastOptionsOps[L <: AnyKList.withBound[AnyDenotation]](l: L):
    BlastOptionsOps[L] =
    BlastOptionsOps[L](l)

  /*
    Given a BLAST command, we can choose an output record made out of output fields. Each command specifies through its `ValidOutputFields` which fields can be used for it; this is checked when you construct a `BlastExpression`.
  */
  type AnyBlastOutputFields = AnyProductType { type Types <: AnyKList.Of[AnyOutputField] }
  type AnyBlastOutputRecord = AnyRecordType { type Keys <: AnyBlastOutputFields }
  type BlastOutputRecord[OFs <: AnyBlastOutputFields] = RecordType[OFs]

  case object AnyBlastOutputRecord {

    /* expressing that the record has only those keys that are in the command's ValidOutputFields product */
    type For[C <: AnyBlastCommand] =
      AnyBlastOutputRecord {
        type Keys <: AnyBlastOutputFields {
          type Types <: AnyKList {
            type Bound <: AnyOutputField
            type Union <: C#ValidOutputFields#Types#Union
          }
        }
      }
  }

  implicit def blastOutputRecordOps[R <: AnyBlastOutputRecord](r: R):
    BlastOutputRecordOps[R] =
    BlastOutputRecordOps[R](r)
}


case class BlastOptionsOps[L <: AnyKList.withBound[AnyDenotation]](val l: L) extends AnyVal {

  def toSeq(implicit opsToSeq: api.BlastOptionsToSeq[L]): Seq[String] = opsToSeq(l)
}


case class BlastOutputRecordOps[R <: api.AnyBlastOutputRecord](val rec: R) extends AnyVal {

  def toSeq: Seq[String] = {
    val fieldsSeq: Seq[String] = rec.keys.types.asList.map{ _.label }
    if (fieldsSeq.isEmpty) Seq()
    else {
      // NOTE: '10' is the code for csv output
      Seq("-outfmt", ("10" +: fieldsSeq).mkString(" "))
    }
  }
}
