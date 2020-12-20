package com.aleson.marvel.marvelcharacters.core.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.aleson.marvel.marvelcharacters.core.ApplicationSetup.Companion.toastColor
import com.aleson.marvel.marvelcharacters.core.model.error.MULTIPLE_LOADING


abstract class BaseFragment : BaseDialogFragment() {

    lateinit var toolbarIcon: ImageView
    lateinit var toolbarButton: ImageButton
    lateinit var toolBarTitle: TextView
    lateinit var toolbar: Toolbar
    private var dialog: Dialog? = null

    abstract fun getFragmentTag(): String?

    abstract fun getFragmentLayout(): Int

    abstract fun onBindView(view: View)

    abstract fun setupView()

    abstract fun setupViewModel()

    abstract fun onClickListeners()

    abstract fun oberserverEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getFragmentLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.onBindView(view)
        this.setupView()
        this.onClickListeners()
        this.oberserverEvent()
    }

    override fun onDetach() {
        super.onDetach()
        parentFragment?.onResume()
    }

    fun showToast(context: Context?, message: String?) {
        hideLoading()
        val toast: Toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.view?.setBackgroundColor(Color.parseColor(toastColor))
        toast.show()
    }

    fun showLoading() {
        if (dialog == null) {
            dialog = super.loading(context as Context) as Dialog
            dialog?.show()
        } else {
            if (dialog?.isShowing as Boolean) {
                throw Exception(MULTIPLE_LOADING)
            } else {
                dialog?.show()
            }
        }
    }

    fun hideLoading() {
        if (dialog != null) {
            val state = dialog?.isShowing
            if (state as Boolean)
                dialog?.dismiss()
        }

    }

}