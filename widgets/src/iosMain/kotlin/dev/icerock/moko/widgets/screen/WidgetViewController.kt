/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets.screen

import dev.icerock.moko.widgets.core.Widget
import platform.UIKit.UIColor
import platform.UIKit.UIViewController
import platform.UIKit.addSubview
import platform.UIKit.backgroundColor
import platform.UIKit.bottomAnchor
import platform.UIKit.leadingAnchor
import platform.UIKit.safeAreaLayoutGuide
import platform.UIKit.topAnchor
import platform.UIKit.trailingAnchor
import platform.UIKit.translatesAutoresizingMaskIntoConstraints

class WidgetViewController : UIViewController(nibName = null, bundle = null) {

    lateinit var widget: Widget

    override fun viewDidLoad() {
        super.viewDidLoad()

        val widgetView = widget.buildView(this)
        widgetView.translatesAutoresizingMaskIntoConstraints = false

        with(view) {
            backgroundColor = UIColor.whiteColor()

            addSubview(widgetView)

            val guide = safeAreaLayoutGuide

            widgetView.leadingAnchor.constraintEqualToAnchor(leadingAnchor).active = true
            widgetView.trailingAnchor.constraintEqualToAnchor(trailingAnchor).active = true
            widgetView.topAnchor.constraintEqualToAnchor(guide.topAnchor).active = true
            widgetView.bottomAnchor.constraintEqualToAnchor(guide.bottomAnchor).active = true
        }
    }
}