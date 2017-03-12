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
import scala.collection.concurrent.TrieMap

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

  /** Application name and value store */
  val apps: TrieMap[String, ActorRef] = TrieMap.empty

  /**
   * GET `/`
   *
   * @return Homepage / landing page.
   */
  def index: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.index(apps.keys))
  }

  /**
   * GET `/add`
   * Add a new application into the registry.
   *
   * @return Redirect to landing page if successful.
   */
  def add: Action[AnyContent] = Action { implicit request =>
    val key  = "runway"
    apps.get(key) match {
      case Some(_) =>
        Redirect(routes.HomeController.index())
          .flashing("warning" -> s"Application with the ID: '${key}' is already registered")

      case _ =>
        apps += key -> system.actorOf(Props(new ProcessRunner(key + "-0.1.0-SNAPSHOT")), key)
        Redirect(routes.HomeController.index())
          .flashing("success" -> "Application added")
    }
  }

  /**
   * GET `/:key/start`
   * Handles an application start command given an application key.
   *
   * @param key The application key.
   * @return Redirect to landing page if successful.
   */
  // TODO: Convert to Form POST
  def start(key: String): Action[AnyContent] = Action { implicit request =>
    apps.get(key) match {
      case Some(actor) =>
        actor ! Start
        Redirect(routes.HomeController.index())
          .flashing("success" -> "Application started")

      case _ =>
        Redirect(routes.HomeController.index())
          .flashing("error" -> s"Application with ID: '${key}' is not registered")
    }
  }

  /**
   * GET `/:key/stop`
   * Handles an application stop command given an application key.
   *
   * @param key The application key.
   * @return Redirect to landing page if successful.
   */
  // TODO: Convert to Form POST
  def stop(key: String): Action[AnyContent] = Action { implicit request =>
    apps.get(key) match {
      case Some(actor) =>
        actor ! Stop
        Redirect(routes.HomeController.index())
          .flashing("success" -> "Application stopped")

      case _ =>
        Redirect(routes.HomeController.index())
          .flashing("error" -> s"Application with ID: '${key}' is not registered")
    }
  }

}
