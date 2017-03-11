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

import akka.actor.{ActorRef, ActorSystem, Props}
import com.hhandoko.runway.process.ProcessRunner
import com.hhandoko.runway.process.ProcessRunner.{Start, Stop}

import play.api.mvc._

/**
 * Homepage / landing page controller.
 *
 * @param system The actor system.
 */
@Singleton
class HomeController @Inject() (system: ActorSystem) extends Controller {

  /** Process runner actor */
  val runner: ActorRef = system.actorOf(Props[ProcessRunner], "runner")

  /**
   * GET `/`
   *
   * @return Homepage / landing page.
   */
  def index: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.index())
  }

  /**
   * GET `/start`
   * Handles an application start command.
   *
   * @return Redirect to landing page if successful.
   */
  def start: Action[AnyContent] = Action { implicit request =>
    runner ! Start
    Redirect(routes.HomeController.index())
      .flashing("success" -> "Application started")
  }

  /**
   * GET `/stop`
   * Handles an application stop command.
   *
   * @return Redirect to landing page if successful.
   */
  def stop: Action[AnyContent] = Action { implicit request =>
    runner ! Stop
    Redirect(routes.HomeController.index())
      .flashing("success" -> "Application stopped")
  }

}
