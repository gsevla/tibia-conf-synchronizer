import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ChatOptions(
    val chatModeOn: Boolean,
    val lootChannelOpen: Boolean,
    val openChannels: List<String>
)

@Serializable
data class HotkeyOptions(
    val autoSwitchHotkeyPreset: Boolean,
    val currentHotkeySetName: String,
    val hotkeySets: JsonObject
)

@Serializable
data class TibiaConf(
    val hotkeyOptions: HotkeyOptions
)
