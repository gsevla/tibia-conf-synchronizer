package views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun RegistrationView() {
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