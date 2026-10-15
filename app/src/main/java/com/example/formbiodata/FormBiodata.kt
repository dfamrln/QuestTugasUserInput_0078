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
    ) }