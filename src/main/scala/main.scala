import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import file.concurrent.FileCounterThread
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import vector.concurrent.operations
import java.io.File


@main def main(path: String): Unit = {
  // Arreglos vectores
  val v1 = Array[Int](1,2,3,4,5,6,7)
  val v2 = Array[Int](2,2,2,2,2,2,2)

  // productos punto
  println(operations.dotProductFutures(v1, v2))
  println(operations.dotProductParallel(v1, v2))

  // producto punto con valor en callback!
  val fDot = operations.dotProductAllFuture(v1, v2)
  fDot.onComplete {
    case Success(v) => println(v)
    case Failure(exception) => println("Fallo el calculo del producto punto :(")
  }

  val f = File(path)
  if !f.exists() || f.isFile then
    println(s"Path $path invalido o es un archivo.")
    // En caso de no haber completado el producto punto aun
    if !fDot.isCompleted then Await.ready(fDot, Duration.Inf)
    return ()


  // Conteo de archivos
  val t = FileCounterThread(f)
  t.start()
  t.join()
  println(s"Numero de archivos encontrados ${t.count}")

  // En caso de no haber completado el producto punto aun
  if !fDot.isCompleted then Await.ready(fDot, Duration.Inf)
}