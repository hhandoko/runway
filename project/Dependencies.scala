/**
 * File     : Dependencies.scala
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
import sbt._

import play.sbt.PlayImport._

/**
 * Centralised dependencies declaration.
 */
object Dependencies {

  // ----------------------------------------------------------------------------------------------------
  // Testing
  // ----------------------------------------------------------------------------------------------------
  // ScalaTest
  //   - http://www.scalatest.org/plus/play
  private val scalaTestPlay = "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test

  /** Play Framework-specific dependencies bundle */
  private val playBundle = Seq(
    filters,
    // Test
    scalaTestPlay
  )

  /** Bundled dependency */
  val dependencies: Seq[ModuleID] = playBundle

}