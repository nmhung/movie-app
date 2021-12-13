package net.fitken.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.fitken.movieapp.base.activity.BaseActivity

class MainActivity : BaseActivity() {
    override fun isNeedWindowLightStatusBar(): Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}