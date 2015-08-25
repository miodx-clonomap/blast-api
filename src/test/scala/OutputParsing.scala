package ohnosequences.blast.test

import ohnosequences.blast.api._
import ohnosequences.blast.api._, outputFields._
import ohnosequences.cosas._, types._, typeSets._, properties._, records._
import java.io.File

case object csvUtils {

  import com.github.tototoshi.csv._
  def csvReader(file: File): CSVReader = CSVReader.open(file)

  def lines(file: File): Iterator[Seq[String]] = csvReader(file) iterator

  // TODO much unsafe, add errors
  def rows(file: File)(headers: Seq[String]): Iterator[Map[String,String]] =
    lines(file) map { line => (headers zip line) toMap }
}

class ParseBlastOutput extends org.scalatest.FunSuite {

  case object outRecord extends BlastOutputRecord(
    qseqid  :&:
    qlen    :&:
    sseqid  :&: □
    // sgi     :&:
    // sacc    :&:
    // slen    :&:
    // qstart  :&:
    // qend    :&:
    // sstart  :&:
    // send    :&:
    // outputFields.evalue :&: □
  )

  test("can parse BLAST output") {

    import csvUtils._

    val blastOutput: File = new File("blastn.test3.out.txt")

    rows(blastOutput)(outRecord.properties mapToList typeLabel) map { row => outRecord parseFrom row } foreach {

      optRec => optRec match {

        case Right(b: ValueOf[outRecord.type]) =>  {

          println("correctly parsed record:")
          // TODO map poly
          println(s"${b get qseqid show} :~: ${b get qlen show} :~: ${b get sseqid show}")
        }

        case Left(v) => s"oh, an error: ${v}"
      }
    }
  }
}
