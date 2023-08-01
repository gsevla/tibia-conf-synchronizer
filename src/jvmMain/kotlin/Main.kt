import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jetbrains.skia.Color
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.name


@Composable
@Preview
fun ListItem(label: String) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).background(
            color = if(isSelected) MaterialTheme.colors.secondary else androidx.compose.ui.graphics.Color.Transparent,
            shape = MaterialTheme.shapes.small
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ClickableText(
                text = AnnotatedString(label),
                onClick = {
                    println("$label pressed!")
                    isSelected = !isSelected
                },
                style = MaterialTheme.typography.h6,
            )
        }
    }
}

@Composable
@Preview
fun ItemsContainer(itemsArray: Array<String>) {
    Surface(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        )) {
            itemsArray.forEach { item ->
                ListItem(
                    label = item,
                )
            }
        }
    }
}

@Composable
@Preview
fun RowScope.RemoteColumn() {
    val githubRepository = GithubRepository()
    val coroutineScope = rememberCoroutineScope()

    val (gists, setGists) = remember { mutableStateOf<String?>(null) }

//    val getGists: () -> Unit = {
        coroutineScope.launch {
            println("Vai fazer!")
            try {
                setGists(githubRepository.listUserGists("gsevla"))
                println(gists)
            } catch (e: Exception) {
                println("Houve um erro: ${e.message}")
            }
        }
//    }

    Column(
        modifier = Modifier.weight(1f).fillMaxHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Remoto",
            style = MaterialTheme.typography.h2
        )
        Spacer(
            modifier = Modifier.size(4.dp)
        )
        ItemsContainer(
            itemsArray = arrayOf(
                "Item Remoto 1",
                "Item Remoto 2"
            )
        )
    }
}

@Composable
@Preview
fun SyncButtonsColumn() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        Row{
            IconButton(
                onClick = {
                    println("Left pressed!")
                }
            ) {
                Icon(
                    Icons.Sharp.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        Row{
            IconButton(
                onClick = {
                    println("Right pressed!")
                },
            ) {
                Icon(
                    Icons.Sharp.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Composable
@Preview
fun RowScope.LocalColumn() {
    val localRepository = LocalRepository()

    Column(
        modifier = Modifier.weight(1f).fillMaxHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Local",
            style = MaterialTheme.typography.h2
        )
        Spacer(
            modifier = Modifier.size(4.dp)
        )
        ItemsContainer(
            itemsArray = localRepository.listLocalHotkeys().toTypedArray<String>()
        )
    }
}

@Composable
@Preview
fun ColumnsLayout() {
    Row(
        modifier = Modifier.fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        RemoteColumn()
        Spacer(
            modifier = Modifier.size(16.dp)
        )
        SyncButtonsColumn()
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        LocalColumn()
    }
}

@Composable
@Preview
fun RegisterLayout() {
    var githubUsername by remember { mutableStateOf("") }
    var githubToken by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Informações do GitHub",
            style = MaterialTheme.typography.h4
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        TextField(
            value = githubUsername,
            onValueChange = {
                githubUsername = it
            },
            label = {
                Text(
                    text = "Username"
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text("Digite o seu Username do GitHub")
            }
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        TextField(
            value = githubToken,
            onValueChange = {
                githubToken = it
            },
            label = {
                Text(
                    text = "Token"
                )
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text("Digite o seu Token do GitHub")
            }
        )
        Spacer(
            modifier = Modifier.height(48.dp)
        )
        Button(
            onClick = {
                println("prosseguir")
            },
            enabled = false
        ) {
            Text("Prosseguir")
        }
    }
}

@Composable
@Preview
fun IntroductionLayout(onNext: () -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    append("oi, ")
                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colors.primary
                        )
                    ) {
                        append("eu sou o goku!")
                    }
                }
            )
            Text(
                text = "- explicação básica"
            )
            Text(
                text = "- um gist será criado caso não exista. caso contrário ele será carregado"
            )
            Button(
                onClick = onNext,
            ) {
                Text("Prosseguir")
            }
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
                1 -> IntroductionLayout(
                    onNext = {
                        step = 2
                    }
                )
                2 -> RegisterLayout()
                3 -> ColumnsLayout()
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

@Composable
@Preview
fun ListGists() {
    val coroutineScope = rememberCoroutineScope()

    val (gists, setGists) = remember { mutableStateOf<String?>(null) }

    val getGists: () -> Unit = {
        coroutineScope.launch {
            var repository = GithubRepository()
            println("Vai fazer!")
            try {
                setGists(repository.listUserGists("gsevla"))
                println(gists)
            } catch (e: Exception) {
                println("Houve um erro: ${e.message}")
            }
        }
    }

    Button(onClick = getGists) {
        Text("Listar Gists")
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        title = "Tibia Hotkeys Synchronizer"
    ) {
        App()
    }
}
