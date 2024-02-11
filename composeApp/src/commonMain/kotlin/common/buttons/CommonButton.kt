package common.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun CommonButton(onClick: () -> Unit,
                 modifier: Modifier,
                 enabled: Boolean? = false,
                 interactionSource: MutableInteractionSource?,
                 elevation: ButtonElevation?,
                 shape: Shape,
                 border: BorderStroke?,
                 colors: ButtonColors,
                 contentPadding: PaddingValues?,
                 content: @Composable() RowScope.() -> Unit) {
  if (interactionSource != null && contentPadding != null) {
      Button(onClick,
        modifier,
        enabled ?: false,
        interactionSource,
        elevation,
        shape,
        border,
        colors,
        contentPadding,
        content
      )
  }
  val ( defaultModifier, defaultEnabled, defaultInteractionSource, defaultElevation,
    defaultShape, defaultBorder, defaultColors, defaultContentPadding ) = CommonButtonStyles()
  Button(onClick, defaultModifier, defaultEnabled, defaultInteractionSource, defaultElevation,
    defaultShape, defaultBorder, defaultColors, defaultContentPadding, content)
}
