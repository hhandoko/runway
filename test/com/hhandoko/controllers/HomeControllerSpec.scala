/**
 * File     : HomeControllerSpec.scala
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
package com.hhandoko.controllers

import com.hhandoko.runway.controllers.routes
import org.scalatestplus.play._

import play.api.test.Helpers._
import play.api.test._

/**
 * HomeController unit tests.
 */
class HomeControllerSpec extends PlaySpec
  with OneAppPerTest {

  "HomeController" when {

    "opening the landing page" should {

      "render successfully" in {
        val url = routes.HomeController.index().url
        val request = FakeRequest(GET, url).withHeaders("Host" -> "localhost")
        val page = route(app, request).get

        status(page) mustBe OK
        contentType(page) mustBe Some("text/html")
        contentAsString(page) must include("Welcome to Play")
      }

    }

    "submitting start action" should {

      // TODO: Need to mock actor system
      "redirect to landing page if successful" in {
        val url = routes.HomeController.start().url
        val request = FakeRequest(GET, url).withHeaders("Host" -> "localhost")
        val page = route(app, request).get

        status(page) mustBe SEE_OTHER
        flash(page).data.values must contain("Application started")
      }

    }

    // TODO: Need to mock actor system
    "submitting stop action" should {

      "redirect to landing page if successful" in {
        val url = routes.HomeController.stop().url
        val request = FakeRequest(GET, url).withHeaders("Host" -> "localhost")
        val page = route(app, request).get

        status(page) mustBe SEE_OTHER
        flash(page).data.values must contain("Application stopped")
      }

    }

  }

}
