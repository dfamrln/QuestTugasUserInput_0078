package com.example.formbiodata

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import java.text.DateFormat
import java.util.Calendar

@Composable
fun FormBiodata(onProceed: () -> Unit) {
    val context = LocalContext.current

    var namaDepan by rememberSaveable { mutableStateOf("") }
    var namaBelakang by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var tanggalLahir by rememberSaveable { mutableStateOf("") }
    var pengalaman by rememberSaveable { mutableStateOf("") }

    var skillAndroid by rememberSaveable { mutableStateOf(false) }
    var skillUiux by rememberSaveable { mutableStateOf(false) }
    var skillWeb by rememberSaveable { mutableStateOf(false) }

    var menuTerbuka by remember { mutableStateOf(false) }

    val daftarPengalaman = stringArrayResource(
        R.array.experience_options
    )
    val placeholderPengalaman = stringResource(R.string.experience)

    val formValid =
        namaDepan.isNotBlank() &&
                namaBelakang.isNotBlank() &&
                email.isNotBlank() &&
                tanggalLahir.isNotBlank() &&
                pengalaman.isNotBlank() &&
                (skillAndroid || skillUiux || skillWeb)

    val bentukForm = RoundedCornerShape(
        topStart = dimensionResource(R.dimen.form_radius),
        topEnd = dimensionResource(R.dimen.form_radius)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = dimensionResource(R.dimen.form_elevation),
                shape = bentukForm
            )
            .background(
                color = colorResource(R.color.form_background),
                shape = bentukForm
            )
            .padding(dimensionResource(R.dimen.form_padding))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.field_spacing)
            )
        ) {
            Text(
                text = stringResource(R.string.form_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.form_primary)
            )

            Text(
                text = stringResource(R.string.form_subtitle),
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(R.color.form_muted)
            )

            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.title_spacing)
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.column_spacing)
                )
            ) {
                BiodataTextField(
                    value = namaDepan,
                    onValueChange = { namaDepan = it },
                    placeholder = stringResource(R.string.first_name),
                    modifier = Modifier.weight(1f)
                )

                BiodataTextField(
                    value = namaBelakang,
                    onValueChange = { namaBelakang = it },
                    placeholder = stringResource(R.string.last_name),
                    modifier = Modifier.weight(1f)
                )
            }

            BiodataTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.column_spacing)
                )
            ) {
                OutlinedTextField(
                    value = tanggalLahir,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.birth_date),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                val kalender = Calendar.getInstance()

                                DatePickerDialog(
                                    context,
                                    { _, tahun, bulan, hari ->
                                        kalender.set(tahun, bulan, hari)

                                        tanggalLahir = DateFormat
                                            .getDateInstance(
                                                DateFormat.SHORT
                                            )
                                            .format(kalender.time)
                                    },
                                    kalender.get(Calendar.YEAR),
                                    kalender.get(Calendar.MONTH),
                                    kalender.get(Calendar.DAY_OF_MONTH)
                                ).apply {
                                    datePicker.maxDate =
                                        System.currentTimeMillis()
                                }.show()
                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    R.drawable.ic_calendar
                                ),
                                contentDescription = stringResource(
                                    R.string.choose_date
                                ),
                                tint = colorResource(R.color.form_text),
                                modifier = Modifier.size(
                                    dimensionResource(R.dimen.icon_size)
                                )
                            )
                        }
                    },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    shape = RoundedCornerShape(
                        dimensionResource(R.dimen.field_radius)
                    ),
                    colors = biodataFieldColors(),
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedButton(
                        onClick = {
                            menuTerbuka = true
                        },
                        shape = RoundedCornerShape(
                            dimensionResource(R.dimen.field_radius)
                        ),
                        border = BorderStroke(
                            width = dimensionResource(
                                R.dimen.border_width
                            ),
                            color = colorResource(R.color.field_border)
                        ),
                        contentPadding = PaddingValues(
                            horizontal = dimensionResource(
                                R.dimen.dropdown_padding
                            )
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                dimensionResource(R.dimen.field_height)
                            )
                    ) {
                        Text(
                            text = pengalaman.ifEmpty {
                                placeholderPengalaman
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorResource(R.color.form_text),
                            modifier = Modifier.weight(1f)
                        )

                        Icon(
                            painter = painterResource(
                                R.drawable.ic_dropdown
                            ),
                            contentDescription = stringResource(
                                R.string.choose_experience
                            ),
                            tint = colorResource(R.color.form_text),
                            modifier = Modifier.size(
                                dimensionResource(R.dimen.icon_size)
                            )
                        )
                    }

                    DropdownMenu(
                        expanded = menuTerbuka,
                        onDismissRequest = {
                            menuTerbuka = false
                        },
                        containerColor = colorResource(
                            R.color.form_background
                        )
                    ) {
                        daftarPengalaman.forEach { pilihan ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = pilihan,
                                        color = colorResource(
                                            R.color.form_text
                                        )
                                    )
                                },
                                onClick = {
                                    pengalaman = pilihan
                                    menuTerbuka = false
                                }
                            )
                        }
                    }
                }
            }

            Text(
                text = stringResource(R.string.choose_skills),
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.form_text),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SkillCheckbox(
                    label = stringResource(R.string.skill_android),
                    checked = skillAndroid,
                    onCheckedChange = {
                        skillAndroid = it
                    }
                )

                SkillCheckbox(
                    label = stringResource(R.string.skill_uiux),
                    checked = skillUiux,
                    onCheckedChange = {
                        skillUiux = it
                    }
                )

                SkillCheckbox(
                    label = stringResource(R.string.skill_web),
                    checked = skillWeb,
                    onCheckedChange = {
                        skillWeb = it
                    }
                )
            }

            Button(
                onClick = onProceed,
                enabled = formValid,
                shape = RoundedCornerShape(
                    dimensionResource(R.dimen.field_radius)
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        R.color.form_primary
                    ),
                    contentColor = colorResource(
                        R.color.form_background
                    ),
                    disabledContainerColor = colorResource(
                        R.color.button_disabled
                    ),
                    disabledContentColor = colorResource(
                        R.color.form_background
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        dimensionResource(R.dimen.button_height)
                    )
            ) {
                Text(
                    text = stringResource(R.string.proceed),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}