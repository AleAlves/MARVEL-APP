package com.aleson.marvel.marvelcharacters.core.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aleson.marvel.marvelcharacters.core.base.BaseDialogFragment

abstract class BaseFragment : BaseDialogFragment() {

    private var dialog : Dialog? = null

    abstract fun getFragmentTag(): String?

    abstract fun getFragmentLayout(): Int

    abstract fun onBindView(view: View)

    abstract fun setupView()

    abstract fun setupViewModel()


    abstract fun onBackPressed(): Unit

    abstract fun oberserverStates()

    abstract fun onClickListeners()

    abstract fun oberserverEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getFragmentLayout(), container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupViewModel()
        this.onBindView(view)
        this.setupView()
        this.onClickListeners()
        this.oberserverEvent()
        this.oberserverStates()
    }


    fun showToast(context: Context?, string: String){
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }

    fun showLoading() {
        if(dialog == null){
            dialog = super.loading(context as Context) as Dialog
        }
        if (dialog?.isShowing as Boolean){
            throw Exception("Can't show more than one loading")
        }
        else {
            dialog?.show()
        }
    }

    fun hideLoading() {
        if (dialog?.isShowing as Boolean)
            dialog?.dismiss()
    }

}