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

package io.deepsense.deeplang.doperables.spark.wrappers.models

import org.apache.spark.ml.feature.{StandardScaler => SparkStandardScaler, StandardScalerModel => SparkStandardScalerModel}

import io.deepsense.deeplang.doperables.SparkModelWrapper
import io.deepsense.deeplang.doperables.report.CommonTablesGenerators.SparkSummaryEntry
import io.deepsense.deeplang.doperables.report.{CommonTablesGenerators, Report}
import io.deepsense.deeplang.doperables.spark.wrappers.params.common.{HasInputColumn, HasOutputColumn}
import io.deepsense.deeplang.params.Param

class StandardScalerModel
  extends SparkModelWrapper[SparkStandardScalerModel, SparkStandardScaler]
  with HasInputColumn
  with HasOutputColumn {

  override val params: Array[Param[_]] = declareParams(inputColumn, outputColumn)

  override def report: Report = {
    val summary =
      List(
        SparkSummaryEntry(
          name = "std",
          value = model.std,
          description = "Vector of standard deviations of the model."),
        SparkSummaryEntry(
          name = "mean",
          value = model.mean,
          description = "Vector of means of the model."))


    super.report
      .withAdditionalTable(CommonTablesGenerators.modelSummary(summary))
  }
}
