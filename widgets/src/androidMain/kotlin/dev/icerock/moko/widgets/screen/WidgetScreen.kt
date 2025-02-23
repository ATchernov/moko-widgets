/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets.screen

import android.content.Context
import android.view.ViewGroup
import dev.icerock.moko.widgets.core.View
import dev.icerock.moko.widgets.core.ViewFactoryContext
import dev.icerock.moko.widgets.core.Widget

actual abstract class WidgetScreen<Arg : Args> actual constructor() : Screen<Arg>() {
    actual abstract fun createContentWidget(): Widget

    override fun createView(context: Context, parent: ViewGroup?): View {
        val widget = createContentWidget()
        return widget.buildView(
            ViewFactoryContext(
                context = context,
                lifecycleOwner = this,
                parent = parent
            )
        )
    }
}