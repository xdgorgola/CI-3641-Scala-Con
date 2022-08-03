package vector.concurrent.operations

import java.util.concurrent.atomic.AtomicInteger
import concurrent.ExecutionContext.Implicits.global
import collection.parallel.CollectionConverters.ArrayIsParallelizable
import scala.collection.parallel.mutable.ParArray
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


def dotProductFutures(v1: Array[Int], v2: Array[Int]): Int=
  var fut: List[Future[Unit]] = List()
  val res = AtomicInteger()
  for i <- v1.indices
  do
    fut = fut :+ Future { res.addAndGet(v1(i) * v2(i)) }

  for f <- fut do Await.ready(f, Duration.Inf)
  res.intValue()


def dotProductParallel(v1: Array[Int], v2: Array[Int]): Int =
    ParArray[Int](v1.indices: _*).map(i => v1(i) * v2(i)).sum



