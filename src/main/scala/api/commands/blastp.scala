package ohnosequences.blast.api

import ohnosequences.cosas._, types._, records._, klists._
import ohnosequences.blast.api.outputFields._

// type blastp = blastp.type
case object blastp extends AnyBlastCommand {

  case object arguments extends RecordType(db :×: query :×: out :×: |[AnyBlastOption])
  type Arguments = arguments.type

  case object options extends RecordType(num_threads :×: |[AnyBlastOption])
  type Options = options.type

  type ValidOutputFields =
    qseqid.type    :×:
    sseqid.type    :×:
    sgi.type       :×:
    qstart.type    :×:
    qend.type      :×:
    sstart.type    :×:
    send.type      :×:
    qlen.type      :×:
    slen.type      :×:
    bitscore.type  :×:
    score.type     :×:
    |[AnyOutputField]

  type ArgumentsVals =
    (db.type    := db.Raw)    ::
    (query.type := query.Raw) ::
    (out.type   := out.Raw)   ::
    *[AnyDenotation]

  type OptionsVals =
    (num_threads.type := num_threads.Raw) ::
    *[AnyDenotation]

  val defaults: Options := OptionsVals = options (
    num_threads(1) :: *[AnyDenotation]
  )

  case object task extends BlastOption[Task](t => t.name) {
    def apply(t: Task): this.type := Task = this := t
  }
  sealed abstract class Task(val name: String)
  case object blastp      extends Task( "blastp" )
  case object blastpFast  extends Task( "blastp-fast" )
  case object blastpShort extends Task( "blastp-short" )

  def apply[R <: AnyBlastOutputRecord.For[this.type]](
    outputRecord: R,
    argumentValues: ArgumentsVals,
    optionValues: OptionsVals
  ): BlastExpression[this.type, R] = BlastExpression(this)(outputRecord, argumentValues, optionValues)
}
