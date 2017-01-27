/*
 * Copyright 2017 David Schmitz
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

package io.uport.recipe.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.{ IncomingConnection, ServerBinding }
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source

import scala.concurrent.{ ExecutionContextExecutor, Future }

object RecipeService extends App {

  implicit val actorSystem: ActorSystem                   = ActorSystem("recipe-service")
  implicit val materializer: ActorMaterializer            = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

  import io.uport.recipe.config.Settings._

  val service: Source[IncomingConnection, Future[ServerBinding]] = Http(actorSystem).bind(httpHost, httpPort)

//  log.info(s"\nAkka HTTP Server - Version ${actorSystem.settings.ConfigVersion} - running at http://$httpHost:$httpPort/")

}
