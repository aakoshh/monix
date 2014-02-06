package monifu.concurrent.atomic

import java.util.concurrent.atomic.AtomicInteger
import scala.annotation.tailrec

final class AtomicShort private (ref: AtomicInteger) extends AtomicNumber[Short] {
  type Underlying = AtomicInteger
  def asJava = ref

  def get: Short =
    (ref.get() & mask).asInstanceOf[Short]

  def set(update: Short) = ref.set(update)

  def lazySet(update: Short) = ref.lazySet(update)

  def compareAndSet(expect: Short, update: Short): Boolean =
    ref.compareAndSet(expect, update)

  def weakCompareAndSet(expect: Short, update: Short): Boolean =
    ref.weakCompareAndSet(expect, update)

  def getAndSet(update: Short): Short =
    (ref.getAndSet(update) & mask).asInstanceOf[Short]

  def plusOp(a: Short, b: Short): Short = ((a + b) & mask).asInstanceOf[Short]
  def minusOp(a: Short, b: Short): Short = ((a - b) & mask).asInstanceOf[Short]
  def incrOp(a: Short, b: Int): Short = ((a + b) & mask).asInstanceOf[Short]

  private[this] val mask = 255 + 255 * 256
}

object AtomicShort {
  def apply(initialValue: Short): AtomicShort =
    new AtomicShort(new AtomicInteger(initialValue))
}