package garcia.marco.myliverpoolapp.ui.bases

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import garcia.marco.myliverpoolapp.ui.screens.main.MainActivity
import garcia.marco.myliverpoolapp.domain.exceptions.ApiException
import garcia.marco.myliverpoolapp.ui.custom.ErrorManager
import garcia.marco.myliverpoolapp.ui.custom.InterviewLoader
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    lateinit var loader : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loader = InterviewLoader.instance(this)
        createView()
        collectFlows()
    }

    open fun hideSoftKeyboard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    abstract fun createView()
    abstract fun collectFlows()

    protected open fun showError(t : Throwable) {
        when(t) {
            is ApiException -> ErrorManager.showMessage(context = this, _message = t.uiMessage)
            else -> ErrorManager.showMessage(context = this)
        }
    }

    @ExperimentalCoroutinesApi
    fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}