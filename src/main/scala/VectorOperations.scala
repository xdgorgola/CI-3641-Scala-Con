package vector.concurrent.operations

import collection.parallel.CollectionConverters.ArrayIsParallelizable
import concurrent.ExecutionContext.Implicits.global
import scala.collection.parallel.mutable.ParArray
import java.util.concurrent.atomic.AtomicInteger
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


/** Producto punto de dos arreglos usando Futures
 *
 * @param v1 Arreglo 1
 * @param v2 Arreglo 2
 *
 * @return Producto punto entre v1 y v2
 */
def dotProductFutures(v1: Array[Int], v2: Array[Int]): Int=
  var fut: List[Future[Unit]] = List()
  val res = AtomicInteger()
  for i <- v1.indices
  do
    fut = fut :+ Future { res.addAndGet(v1(i) * v2(i)) }

  for f <- fut do Await.ready(f, Duration.Inf)
  res.intValue()


/**
 * Producto punto entre dos arreglos usando parallel collections
 *
 * @param v1 Arreglo 1
 * @param v2 Arreglo 2
 *
 * @return Producto munto entre v1 y v2
 */
def dotProductParallel(v1: Array[Int], v2: Array[Int]): Int =
    ParArray[Int](v1.indices: _*).map(i => v1(i) * v2(i)).sum



