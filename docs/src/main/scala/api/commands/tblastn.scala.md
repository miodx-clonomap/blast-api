
```scala
package ohnosequences.blast.api

import ohnosequences.cosas._, types._, records._, klists._
import ohnosequences.blast.api.outputFields._

// type tblastn = tblastn.type
case object tblastn extends AnyBlastCommand {

  case object arguments extends RecordType(db :×: query :×: out :×: |[AnyBlastOption])
  type Arguments = arguments.type

  // TODO: figure out the full list of options here
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
    num_threads(1) ::
    *[AnyDenotation]
  )

  def apply[R <: AnyBlastOutputRecord.For[this.type]](
    outputRecord: R,
    argumentValues: ArgumentsVals,
    optionValues: OptionsVals
  ): BlastExpression[this.type, R] = BlastExpression(this)(outputRecord, argumentValues, optionValues)
}

```




[main/scala/api/commands/blastn.scala]: blastn.scala.md
[main/scala/api/commands/blastp.scala]: blastp.scala.md
[main/scala/api/commands/blastx.scala]: blastx.scala.md
[main/scala/api/commands/igblastn.scala]: igblastn.scala.md
[main/scala/api/commands/makeblastdb.scala]: makeblastdb.scala.md
[main/scala/api/commands/tblastn.scala]: tblastn.scala.md
[main/scala/api/commands/tblastx.scala]: tblastx.scala.md
[main/scala/api/expressions.scala]: ../expressions.scala.md
[main/scala/api/options.scala]: ../options.scala.md
[main/scala/api/outputFields.scala]: ../outputFields.scala.md
[main/scala/api/package.scala]: ../package.scala.md
[main/scala/api/parse/igblastn.scala]: ../parse/igblastn.scala.md
[test/scala/CommandGeneration.scala]: ../../../../test/scala/CommandGeneration.scala.md
[test/scala/igblastn.scala]: ../../../../test/scala/igblastn.scala.md
[test/scala/igblastnClonotypesOutput.scala]: ../../../../test/scala/igblastnClonotypesOutput.scala.md
[test/scala/OutputFieldsSpecification.scala]: ../../../../test/scala/OutputFieldsSpecification.scala.md
[test/scala/OutputParsing.scala]: ../../../../test/scala/OutputParsing.scala.md