
package  com.gebeya.begonet.framework.base.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutId: Int
    var primaryBaseActivity: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }


    override fun attachBaseContext(newBase: Context?) {
        primaryBaseActivity = newBase
        super.attachBaseContext(newBase)
    }

}
