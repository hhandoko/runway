/**
 * File     : HomeController.scala
 * License  :
 *   Copyright (c) 2017 Herdy Handoko
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.hhandoko.runway.process

import scala.sys.process._

import akka.actor.Actor

/**
 * Process runner actor.
 */
class ProcessRunner extends Actor {

  import ProcessRunner._

  // TODO: Add location as Props args
  val appPath = "../runway-0.1.0-SNAPSHOT"

  def receive: PartialFunction[Any, Unit] = {
    case Run  =>
      // TODO: Add port as Props args
      (appPath + "/bin/runway -Dhttp.port=8080 -Dplay.crypto.secret=S3cret").run

    case Stop =>
      (("cat " + appPath + "/RUNNING_PID") #| "xargs kill -SIGTERM").run
  }

  /**
   * Run a command, collecting the stdout, stderr and exit status.
   *
   * @note See http://stackoverflow.com/a/6013932
   * @param in The command to run.
   * @return stdout, stderr, and exit status.
   */
  private def run(in: String): (List[String], List[String], Int) = {
    val qb = Process(in)
    var out = List[String]()
    var err = List[String]()

    val exit = qb ! ProcessLogger((s) => out ::= s, (s) => err ::= s)

    (out.reverse, err.reverse, exit)
  }

}

/**
 * Process runner messages.
 */
object ProcessRunner {

  case object Run
  case object Stop

}
