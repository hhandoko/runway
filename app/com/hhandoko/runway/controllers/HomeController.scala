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
package com.hhandoko.runway.controllers

import javax.inject._

import akka.actor.{ActorSystem, Props}
import com.hhandoko.runway.process.ProcessRunner
import com.hhandoko.runway.process.ProcessRunner.{Run, Stop}

import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (system: ActorSystem) extends Controller {

  val runner = system.actorOf(Props[ProcessRunner], "runner")

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  def start = Action { implicit request =>
    runner ! Run
    Ok(views.html.index("Started"))
  }

  def stop = Action { implicit request =>
    runner ! Stop
    Ok(views.html.index("Stopped"))
  }

}
