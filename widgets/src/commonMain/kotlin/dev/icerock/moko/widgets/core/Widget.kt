/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets.core

abstract class Widget {
    abstract fun buildView(viewFactoryContext: ViewFactoryContext): View

    interface Style
}
