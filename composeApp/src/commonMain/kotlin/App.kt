import TenantConstants.CONTEXT_KEYS
import TenantConstants.CONTEXT_KEY_TENANT_ID_MAP
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.User
import models.id.UserId
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        var greetingText by remember { mutableStateOf("Hello World!") }
        var userText by remember { mutableStateOf("") }
        var showImage by remember { mutableStateOf(false) }
        val userUuid = uuid4()
        val personName = "Brent"
        val personNameInputSanitized = personName.lowercase()
        val tenantContextKeyOpt = CONTEXT_KEYS.find { it == TenantConstants.ContextKey(personNameInputSanitized) }
        val tenantUuid = CONTEXT_KEY_TENANT_ID_MAP.filter { it.key == tenantContextKeyOpt }
            .map { it.value }.first()
        val userInitState = User(UserId(userUuid), tenantUuid,
            personName, email = null, username = null)
        var user by remember { mutableStateOf(userInitState) }
        var greeting by remember { mutableStateOf(Greeting(user)) }
        CenterColumnFillMaxWidth {
            Text(
                text = "It is currently ${todayDateAndTime()}",
                modifier = Modifier.padding(20.dp),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Button(onClick = {
                greetingText = greeting.greet()
                userText = greeting.currentUserDetail()
                showImage = !showImage
            }) { Text(greetingText) }
            AnimatedVisibility(showImage) {
                CenterColumnFillMaxWidth {
                    CenterRowFillMaxWidth { Text(userText) }
                }
            }
            AnimatedVisibility(showImage) {
                Image(
//                    painterResource("compose-multiplatform.xml"),
//                    painterResource("curationhealth/curation-icon.xml"),
                    painterResource("curationhealth/curation-icon.png"),
                    null
                )
            }
        }
    }
}

fun todayDateAndTime(): String {
    val now = now()
    with (now) {
        return todaysDate() + " " + currentTime()
    }
}

//context (Instant)
//fun currentTime(): String {
//    return it.currentTime()
//}

fun todaysDate(): String {
    val now = now()
    with (now) {
        val zone = TimeZone.currentSystemDefault()
        return toLocalDateTime(zone).dateFormat()
    }
}

//context(Instant)
private fun Instant.todaysDate(): String {
    val zone = TimeZone.currentSystemDefault()
    return toLocalDateTime(zone).dateFormat()
}

//context(Instant)
//private fun currentTime(): String {
private fun Instant.currentTime(): String {
    val zone = TimeZone.currentSystemDefault()
    return toLocalDateTime(zone).iso8601TimeFormat()
//    return toLocalDateTime(zone).timeFormat()
}

private fun LocalDateTime.dateFormat() = toString().substringBefore('T')

private fun LocalDateTime.iso8601TimeFormat() = toString().substringAfter('T')

private fun LocalDateTime.timeFormat() = "${this.hour}:${this.minute}"

private fun now(): Instant {
    return Clock.System.now()
}

@Composable
private fun CenterColumnFillMaxWidth(compose: @Composable ColumnScope.() -> Unit): Unit =
    Column(Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) { compose() }

@Composable
private fun CenterRowFillMaxWidth(compose: @Composable RowScope.() -> Unit): Unit =
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) { compose() }



//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material.Button
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import org.jetbrains.compose.resources.ExperimentalResourceApi
//import org.jetbrains.compose.resources.painterResource
//
//@OptIn(ExperimentalResourceApi::class)
//@Composable
//fun App() {
//    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        val greeting = remember { Greeting().greet() }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource("compose-multiplatform.xml"), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
//    }
//}