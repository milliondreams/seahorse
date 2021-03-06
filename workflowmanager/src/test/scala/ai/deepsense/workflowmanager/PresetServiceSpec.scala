/**
 * Copyright 2016 deepsense.ai (CodiLime, Inc)
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

package ai.deepsense.workflowmanager

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar.mock

import ai.deepsense.commons.StandardSpec
import ai.deepsense.commons.auth.exceptions.NoRoleException
import ai.deepsense.commons.auth.usercontext.UserContext
import ai.deepsense.commons.auth.{Authorizator, AuthorizatorProvider}
import ai.deepsense.models.workflows.Workflow
import ai.deepsense.workflowmanager.model.WorkflowPreset
import ai.deepsense.workflowmanager.storage.WorkflowStorage
import ai.deepsense.workflowmanager.storage.impl.PresetsDao

class PresetServiceSpec extends StandardSpec
{
  "saveWorkflowsPreset" should {
    "throw NoRoleException" in {
      val presetStore = mock[PresetsDao]
      val workflowStorage = mock[WorkflowStorage]
      val authorizatorProvider = mock[AuthorizatorProvider]
      val authorizator = mock[Authorizator]
      when(authorizatorProvider.forContext(any())).thenReturn(authorizator)
      val userContext = mock[UserContext]
      val badRoleException = new NoRoleException(userContext, "any")
      when(authorizator.withRole(any())(any())).thenReturn(Future.failed(badRoleException))


      val userContextFuture = Future.successful(mock[UserContext])
      val presetService = new PresetService(presetStore, workflowStorage,
        authorizatorProvider, "any")
      val workflowId = Workflow.Id.randomId
      val wp = new WorkflowPreset(workflowId, 2L)

      val f = presetService.saveWorkflowsPreset(userContextFuture, workflowId, wp)

      whenReady(f.failed) { ex =>
        ex shouldBe an[NoRoleException]
      }
    }
  }
}
