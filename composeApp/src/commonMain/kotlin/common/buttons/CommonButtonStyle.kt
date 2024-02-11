package common.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

data class CommonButtonStyle(
  val modifier: Modifier,
  val enabled: Boolean,
  val interactionSource: MutableInteractionSource,
  val elevation: ButtonElevation,
  val shape: Shape,
  val border: BorderStroke,
  val colors: ButtonColors,
  val contentPadding: PaddingValues
)

@Composable
fun CommonButtonStyles(): CommonButtonStyle {
  return CommonButtonStyle(
    modifier = Modifier,
    enabled = false,
    interactionSource = MutableInteractionSource(),
    elevation = ButtonDefaults.elevation(),
    shape = MaterialTheme.shapes.medium,
    border = ButtonDefaults.outlinedBorder,
    colors = ButtonDefaults.outlinedButtonColors(),
    contentPadding = ButtonDefaults.TextButtonContentPadding
  )
}

//private val commonButtonElevation = ButtonElevation