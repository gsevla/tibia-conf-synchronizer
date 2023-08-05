package views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
@Preview
fun IntroductionView(onNext: () -> Unit) {
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