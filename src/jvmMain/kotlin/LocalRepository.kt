import kotlinx.serialization.json.Json
import java.io.File
import kotlin.io.path.Path

class LocalRepository {
    fun listLocalHotkeys(): Set<String> {
        val appDataPath = System.getenv("APPDATA")
        val tibiaConfFileUri = Path(appDataPath, "..", "Local", "Tibia", "packages", "Tibia", "conf", "otherclientoptions.json").toUri()
        val tibiaConfFile = File(tibiaConfFileUri)
        val tibiaConfFileText = tibiaConfFile.readText()
        val json = Json {
            ignoreUnknownKeys = true
        }
        val tibiaConfFileJson = json.decodeFromString<TibiaConf>(tibiaConfFileText)

//        val characterName = "Paralyszs Warlock"
//        val characterHotkeys = tibiaConfFileJson.hotkeyOptions.hotkeySets[characterName]
//        if(characterHotkeys === null) {
//            println("$characterName hotkeys not found!")
//            return@getGists
//        }
//        println(characterHotkeys)
//        coroutineScope.launch {
//            var repository = GithubRepository()
//            println("Vai fazer!")
//            try {
//                setGists(repository.listUserGists("gsevla"))
//                println(gists)
//            } catch (e: Exception) {
//                println("Houve um erro: ${e.message}")
//            }
//        }

        return tibiaConfFileJson.hotkeyOptions.hotkeySets.keys
//    Button(onClick = getGists) {
//        Text("Listar AppData")
//    }
    }
}