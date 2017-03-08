/**
 * File     : Filters.scala
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
package com.hhandoko.runway.http

import javax.inject.Inject

import play.api.http.DefaultHttpFilters
import play.filters.csrf.CSRFFilter
import play.filters.headers.SecurityHeadersFilter
import play.filters.hosts.AllowedHostsFilter

/**
 * Add the following filters by default to all projects.
 *
 * Refer to the following documentation:
 *   - https://www.playframework.com/documentation/latest/ScalaCsrf
 *   - https://www.playframework.com/documentation/latest/AllowedHostsFilter
 *   - https://www.playframework.com/documentation/latest/SecurityHeaders
 *
 * @param csrfFilter The CSRF filter.
 * @param allowedHostsFilter The host whitelist filter.
 * @param securityHeadersFilter The security headers filter.
 */
class Filters @Inject() (
  csrfFilter: CSRFFilter,
  allowedHostsFilter: AllowedHostsFilter,
  securityHeadersFilter: SecurityHeadersFilter
) extends DefaultHttpFilters(
  csrfFilter, 
  allowedHostsFilter, 
  securityHeadersFilter
)