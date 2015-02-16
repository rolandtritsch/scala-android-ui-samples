/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.common.logger

/**
  * Helper class for a list (or tree) of LoggerNodes.
  *
  * <p>When this is set as the head of the list,
  * an instance of it can function as a drop-in replacement for {@link android.util.Log}.
  * Most of the methods in this class server only to map a method call in Log to its equivalent
  * in LogNode.</p>
  */
object Log {
  // Grabbing the native values from Android's native logging facilities,
  // to make for easy migration and interop.
  val NONE = -1
  val VERBOSE = android.util.Log.VERBOSE
  val DEBUG = android.util.Log.DEBUG
  val INFO = android.util.Log.INFO
  val WARN = android.util.Log.WARN
  val ERROR = android.util.Log.ERROR
  val ASSERT = android.util.Log.ASSERT

  // Stores the beginning of the LogNode topology.
  private var mLogNode: LogNode = null

  /**
    * Returns the next LogNode in the linked list.
    */
  def getLogNode(): LogNode = {
    mLogNode
  }

  /**
    * Sets the LogNode data will be sent to.
    */
  def setLogNode(node: LogNode): Unit = {
    mLogNode = node
  }

  /**
    * Instructs the LogNode to print the log data provided. Other LogNodes can
    * be chained to the end of the LogNode as desired.
    *
    * @param priority Log level of the data being logged. Verbose, Error, etc.
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    * @param tr If an exception was thrown, this can be sent along for the logging facilities
    *           to extract and print useful information.
    */
  def println(priority: Int, tag: String, msg: String, tr: Throwable): Unit = {
    if(mLogNode != null) {
      mLogNode.println(priority, tag, msg, tr)
    }
  }

  /**
    * Instructs the LogNode to print the log data provided. Other LogNodes can
    * be chained to the end of the LogNode as desired.
    *
    * @param priority Log level of the data being logged. Verbose, Error, etc.
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged. The actual message to be logged.
    */
  def println(priority: Int, tag: String, msg: String): Unit = {
    println(priority, tag, msg, null)
  }

  /**
    * Prints a message at VERBOSE priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    * @param tr If an exception was thrown, this can be sent along for the logging facilities
    *           to extract and print useful information.
    */
  def v(tag: String, msg: String, tr: Throwable): Unit = {
    println(VERBOSE, tag, msg, tr)
  }

  /**
    * Prints a message at VERBOSE priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    */
  def v(tag: String, msg: String): Unit = {
    v(tag, msg, null)
  }


  /**
    * Prints a message at DEBUG priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    * @param tr If an exception was thrown, this can be sent along for the logging facilities
    *           to extract and print useful information.
    */
  def d(tag: String, msg: String, tr: Throwable): Unit = {
    println(DEBUG, tag, msg, tr)
  }

  /**
    * Prints a message at DEBUG priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    */
  def d(tag: String, msg: String): Unit = {
    d(tag, msg, null)
  }

  /**
    * Prints a message at INFO priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    * @param tr If an exception was thrown, this can be sent along for the logging facilities
    *           to extract and print useful information.
    */
  def i(tag: String, msg: String, tr: Throwable): Unit = {
    println(INFO, tag, msg, tr)
  }

  /**
    * Prints a message at INFO priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    */
  def i(tag: String, msg: String): Unit = {
    i(tag, msg, null)
  }

  /**
    * Prints a message at WARN priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    * @param tr If an exception was thrown, this can be sent along for the logging facilities
    *           to extract and print useful information.
    */
  def w(tag: String, msg: String, tr: Throwable): Unit = {
    println(WARN, tag, msg, tr)
  }

  /**
    * Prints a message at WARN priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    */
  def w(tag: String, msg: String): Unit = {
    w(tag, msg, null)
  }

  /**
    * Prints a message at WARN priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param tr If an exception was thrown, this can be sent along for the logging facilities
    *           to extract and print useful information.
    */
  def w(tag: String, tr: Throwable): Unit = {
    w(tag, null, tr)
  }

  /**
    * Prints a message at ERROR priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    * @param tr If an exception was thrown, this can be sent along for the logging facilities
    *           to extract and print useful information.
    */
  def e(tag: String, msg: String, tr: Throwable): Unit = {
    println(ERROR, tag, msg, tr)
  }

  /**
    * Prints a message at ERROR priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    */
  def e(tag: String, msg: String): Unit = {
    e(tag, msg, null)
  }

  /**
    * Prints a message at ASSERT priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    * @param tr If an exception was thrown, this can be sent along for the logging facilities
    *           to extract and print useful information.
    */
  def wtf(tag: String, msg: String, tr: Throwable): Unit = {
    println(ASSERT, tag, msg, tr)
  }

  /**
    * Prints a message at ASSERT priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param msg The actual message to be logged.
    */
  def wtf(tag: String, msg: String): Unit = {
    wtf(tag, msg, null)
  }

  /**
    * Prints a message at ASSERT priority.
    *
    * @param tag Tag for for the log data. Can be used to organize log statements.
    * @param tr If an exception was thrown, this can be sent along for the logging facilities
    *           to extract and print useful information.
    */
  def wtf(tag: String, tr: Throwable): Unit = {
    wtf(tag, null, tr)
  }
}
