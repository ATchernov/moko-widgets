/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets.screen

import platform.UIKit.UITabBarController
import platform.UIKit.UITabBarItem
import platform.UIKit.UIViewController
import platform.UIKit.tabBarItem

actual abstract class BottomNavigationScreen actual constructor(
    private val screenFactory: ScreenFactory
) : Screen<Args.Empty>() {
    actual abstract val items: List<BottomNavigationItem>

    private var tabBarController: UITabBarController? = null

    override fun createViewController(): UIViewController {
        val controller = UITabBarController()
        val items = items
        val viewControllers = items.map {
            val childScreen = screenFactory.instantiateScreen(it.screen)
            childScreen.parent = this
            childScreen.createViewController().apply {
                tabBarItem = UITabBarItem(title = it.title.localized(), image = null, selectedImage = null)
            }
        }
        controller.setViewControllers(viewControllers = viewControllers)

        tabBarController = controller

        return controller
    }

    actual var selectedItemId: Int
        get() = tabBarController?.selectedIndex?.let {
            items[it.toInt()].id
        } ?: -1
        set(value) {
            tabBarController?.setSelectedIndex(items.indexOfFirst { it.id == value }.toULong())
        }
}

