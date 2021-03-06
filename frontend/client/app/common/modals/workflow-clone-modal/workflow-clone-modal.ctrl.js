/**
 * Copyright 2017 deepsense.ai (CodiLime, Inc)
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

'use strict';

/* @ngInject */
function WorkflowCloneModalCtrl($uibModalInstance, originalWorkflow) {

  const vm = this;

  vm.originalWorkflow = originalWorkflow;
  vm.workflowCopy = angular.copy(originalWorkflow);
  vm.workflowCopy.name = `Copy of ${vm.workflowCopy.name}`;

  vm.save = save;
  vm.dismiss = dismiss;

  function save() {
    $uibModalInstance.close(vm.workflowCopy);
  }

  function dismiss() {
    $uibModalInstance.dismiss();
  }
}

exports.inject = function (module) {
  module.controller('WorkflowCloneModalCtrl', WorkflowCloneModalCtrl);
};
