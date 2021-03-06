package pl.metastack.metatime

import scala.concurrent.duration._

/**
  * Inspired from Monifu's scheduling code.
  */
trait Scheduler {
  def schedule(interval: FiniteDuration, r: Runnable): Cancelable
  def scheduleOnce(initialDelay: FiniteDuration, action: Runnable): Cancelable

  def schedule(interval: FiniteDuration)(action: => Unit): Cancelable =
    schedule(interval, new Runnable {
      def run(): Unit = action
    })

  def scheduleOnce(initialDelay: FiniteDuration)(action: => Unit): Cancelable =
    scheduleOnce(initialDelay, new Runnable {
      def run(): Unit = action
    })

  def currentTimeMillis(): Long = System.currentTimeMillis()

  def at(time: Time)(f: => Unit): Cancelable =
    at(time.fromNow)(f)
  def at(dateTime: DateTime)(f: => Unit): Cancelable =
    at(dateTime.fromNow)(f)
  def at(time: Offset[_])(f: => Unit): Cancelable =
    scheduleOnce(Math.abs(time.component.asInstanceOf[Component].milliseconds()).millis)(f)

  def in(time: Time)(f: => Unit): Cancelable =
    scheduleOnce(time.milliseconds.millis)(f)
  def in(date: Date)(f: => Unit): Cancelable =
    scheduleOnce(date.milliseconds.millis)(f)
  def in(dateTime: DateTime)(f: => Unit): Cancelable =
    scheduleOnce(dateTime.milliseconds.millis)(f)

  def every(time: Time)(f: => Unit): Cancelable =
    schedule(time.milliseconds.millis)(f)
  def every(dateTime: DateTime)(f: => Unit): Cancelable =
    schedule(dateTime.milliseconds.millis)(f)
  def every(time: Offset[Component])(f: => Unit): Cancelable =
    schedule(Math.abs(time.component.milliseconds()).millis)(f)
}

trait Cancelable {
  def cancel(): Boolean
}

object Cancelable {
  def apply(): Cancelable = apply({})

  def apply(callback: => Unit): Cancelable =
    new Cancelable {
      var isCanceled = false

      def cancel(): Boolean =
        if (isCanceled) false
        else {
          isCanceled = true
          callback
          true
        }
    }
}

trait Task {
  def cancel(): Unit = ???
}
