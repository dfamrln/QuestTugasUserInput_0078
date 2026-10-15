package com.example.formbiodata

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role

@Composable
fun BiodataTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = RoundedCornerShape(
            dimensionResource(R.dimen.field_radius)
        ),
        colors = biodataFieldColors(),
        modifier = modifier
    )
}

@Composable
fun biodataFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = colorResource(R.color.form_text),
    unfocusedTextColor = colorResource(R.color.form_text),
    focusedBorderColor = colorResource(R.color.form_primary),
    unfocusedBorderColor = colorResource(R.color.field_border),
    focusedPlaceholderColor = colorResource(R.color.form_text),
    unfocusedPlaceholderColor = colorResource(R.color.form_text),
    focusedContainerColor = colorResource(R.color.form_background),
    unfocusedContainerColor = colorResource(R.color.form_background),
    cursorColor = colorResource(R.color.form_primary)
)

@Composable
fun SkillCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.toggleable(
            value = checked,
            role = Role.Checkbox,
            onValueChange = onCheckedChange
        )
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(R.color.form_primary),
                uncheckedColor = colorResource(R.color.form_text),
                checkmarkColor = colorResource(
                    R.color.form_background
                )
            )
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.form_text)
        )
    }
}