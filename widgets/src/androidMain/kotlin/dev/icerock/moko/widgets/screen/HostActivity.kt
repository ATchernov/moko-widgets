/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlin.reflect.KClass

abstract class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                val kclass = Class.forName(className, true, classLoader).kotlin
                val screenKlass = kclass as? KClass<out Screen<Args.Empty>>
                return if (screenKlass != null) {
                    getScreenFactory().instantiateScreen(screenKlass)
                } else {
                    super.instantiate(classLoader, className)
                }
            }
        }

        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val rootScreen = getRootScreen()
            val screenInstance = getScreenFactory().instantiateScreen(rootScreen)

            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, screenInstance)
                .commit()
        }
    }

//    override fun onBackPressed() {
//        val rootScreen = supportFragmentManager.findFragmentById(android.R.id.content)
//        if (rootScreen is Screen<*>) {
//            if (rootScreen.onBackPressed()) return
//        }
//
//        super.onBackPressed()
//    }

    private fun getRootScreen(): KClass<out Screen<Args.Empty>> = application.getRootScreen()

    private fun getScreenFactory(): ScreenFactory = application

    abstract val application: BaseApplication
}