package rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.navigation.NavSetup
import rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.theme.CS330DZ11AnteaPrimorac5157Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS330DZ11AnteaPrimorac5157Theme {
                // A surface container using the 'background' color from the theme
                NavSetup()
            }
        }
    }
}
