package com.example.phonepe.fragments

import android.content.*
import android.os.*
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    val subscriptions = CompositeDisposable()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscriptions.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }

    override fun onDetach() {
        super.onDetach()
    }


    open fun showLoading() {

    }

    open fun hideLoading() {

    }

    /**
     * Returns true if fragment context is valid and its added
     */
    open fun isContextValid(): Boolean {
        return isAdded && context != null && view != null
    }



}
