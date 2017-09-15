/**
 * Copyright 2015, deepsense.io
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

package io.deepsense.commons.exception

import io.deepsense.commons.exception.FailureCode.FailureCode

/**
 * Base exception for all DeepSense exceptions
 */
abstract class DeepSenseException(
  val code: FailureCode,
  val title: String,
  val message: String,
  val cause: Option[Throwable] = None,
  val details: Map[String, String] = Map()) extends Exception(message, cause.orNull) {

  val id = DeepSenseFailure.Id.randomId

  def failureDescription: FailureDescription = FailureDescription(
    id,
    code,
    title,
    Some(message),
    details ++ additionalDetails)

  protected def additionalDetails: Map[String, String] = Map()
}
