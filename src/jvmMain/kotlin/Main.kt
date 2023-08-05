import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import views.SynchronizationView
import views.IntroductionView
import views.RegistrationView

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        title = "Tibia Hotkeys Synchronizer"
    ) {
        App()
    }
}

@Composable
@Preview
fun App() {
    var step by remember { mutableStateOf(1) }

    val isBackButtonVisible by remember {
        derivedStateOf {
            step != 1
        }
    }

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize().padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
        ) {
            when(step) {
                1 -> IntroductionView(
                    onNext = {
                        step = 2
                    }
                )
                2 -> RegistrationView()
                3 -> SynchronizationView()
            }
            if(isBackButtonVisible) {
                IconButton(
                    onClick = {
                        step -= 1
                    },
                ) {
                    Icon(
                        Icons.Sharp.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondary,
                    )
                }
            }
        }
    }
}