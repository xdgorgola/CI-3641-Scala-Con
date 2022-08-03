package file.concurrent

import scala.collection.mutable.ArrayBuffer
import java.io.File


class FileCounterThread(val path: File, var count: Int = 0) extends Thread {
  override def run() =
    val files = path.listFiles().toList
    val threads = ArrayBuffer[FileCounterThread]()
    files.foreach(f =>
      f.isFile match
        case true => count += 1
        case _ => {
          val nt = FileCounterThread(File(path, f.getName))
          nt.start()
          threads.append(nt)
        }
    )

    threads.foreach(ft => {
        ft.join()
        count += ft.count
      }
    )
}
