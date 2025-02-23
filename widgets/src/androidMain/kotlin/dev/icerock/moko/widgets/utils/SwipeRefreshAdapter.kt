/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets.utils

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("app:onRefreshListener")
fun setOnRefreshListener(view: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener) {
    view.setOnRefreshListener {
        listener.onRefresh()
        // TODO кривенько это. лучше чтобы калбек был и только по завершению работы рефреш в false уходил.
        //  иначе на UI делается вид что обновление моментальное, а по факту новый контент может
        //  через 30 секунд ток появиться из-за кривой сети
        view.isRefreshing = false
    }
}