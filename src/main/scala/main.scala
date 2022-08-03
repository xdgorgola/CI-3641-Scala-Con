import file.concurrent.FileCounterThread
import vector.concurrent.operations
import java.io.File

@main def main(path: String): Unit = {
  // productos punto
  println(operations.dotProductFutures(Array[Int](1,2,3,4,5,6,7), Array[Int](2,2,2,2,2,2,2)))
  println(operations.dotProductParallel(Array[Int](1,2,3,4,5,6,7), Array[Int](2,2,2,2,2,2,2)))

  val f = File(path)
  if !f.exists() || f.isFile then
    println(s"Path $path invalido o es un archivo.")
    return ()

  // Conteo de archivos
  val t = FileCounterThread(f)
  t.start()
  t.join()
  println(s"Numero de archivos encontrados ${t.count}")
}