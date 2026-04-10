/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.vapingstampsapi

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object AuthLoginApiRequests extends ServicesConfiguration {

  val baseUrl: String = baseUrlFor("auth-login-api")
  val route: String = "/application/session/login"

  private val json = """{
                       |  "clientId": "id-123232",
                       |  "authProvider": "StandardApplication",
                       |  "applicationId":"app-1",
                       |  "applicationName": "App 1",
                       |  "enrolments": [],
                       |  "ttl": 5000
                       |}""".stripMargin

  val generateBearerToken: HttpRequestBuilder =
    http("Generate bearer token")
      .post(s"$baseUrl$route": String)
      .body(StringBody(json))
      .asJson
      .check(status.is(201))
      .check(header(HttpHeaderNames.Authorization).saveAs("bearerToken"))
}
