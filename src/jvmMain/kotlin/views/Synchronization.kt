package views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import repositories.GithubRepository
import repositories.LocalRepository

@Composable
@Preview
fun SynchronizationView() {
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