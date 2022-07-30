package com.example.phonepe.helpers

import android.os.SystemClock
import android.view.View
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun <E> MutableList<E>.clearAndAddAll(elements: Collection<E>) {
    this.clear()
    this.addAll(elements)
}

fun View.click(debounceTime: Long = 300L, action: (view: View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

operator fun CompositeDisposable?.plusAssign(other: Disposable?): Unit = if (other != null) this?.add(other).let { return } else Unit
