package file.concurrent

import scala.collection.mutable.ArrayBuffer
import java.io.File


/**
 * Thread que se encarga de contar todos los archivos de un directorio
 * y sus subdirectorios.
 *
 * @param path Path del directorio a contar
 * @param count Cuenta inicial del directorio
 */
class FileCounterThread(val path: File, var count: Int = 0) extends Thread {

  override def run() =
    val files = path.listFiles().toList
    val threads = ArrayBuffer[FileCounterThread]()

    // Revisamos si los archivos son directorios o archivos per se.
    // Si son directorios, inicia un thread contador para explorarlo.
    // En caso contrario, suma 1 al contador.
    files.foreach(f =>
      f.isFile match
        case true => count += 1
        case _ => {
          val nt = FileCounterThread(File(path, f.getName))
          nt.start()
          threads.append(nt)
        }
    )

    // Esperamos que terminen los threads hijos y sumamos su cuenta
    threads.foreach(ft => {
        ft.join()
        count += ft.count
      }
    )
}
