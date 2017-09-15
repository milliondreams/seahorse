/**
 * Copyright (c) 2015, CodiLime Inc.
 */

package io.deepsense.workflowmanager.deeplang

import com.google.inject.{AbstractModule, Provides, Scopes, Singleton}

import io.deepsense.deeplang.catalogs.doperable.DOperableCatalog
import io.deepsense.deeplang.catalogs.doperations.DOperationsCatalog

/**
 * Provides DOperableCatalog and DOperationsCatalog.
 *
 * This module is just to satisfy dependencies. It should
 * be changed to provide valid catalogs as currently no
 * objects are registered in them.
 */
class DeepLangModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[DOperableCatalog]).in(Scopes.SINGLETON)
  }

  @Singleton
  @Provides
  def provideDOperationsCatalog(): DOperationsCatalog = {
    DOperationsCatalog()
  }
}
