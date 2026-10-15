package com.example.formbiodata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun BiodataScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val pesanBerhasil = stringResource(R.string.submit_success)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.screen_background))
            .safeDrawingPadding()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorResource(R.color.screen_background)
        ) { innerPadding ->
            Image(
                painter = painterResource(R.drawable.banner_biodata),
                contentDescription = stringResource(
                    R.string.banner_description
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.banner_height))
            )
        }

        // Form berada di atas Scaffold dan menutupi sebagian banner.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = dimensionResource(R.dimen.form_top))
                .padding(
                    bottom = dimensionResource(R.dimen.screen_bottom_padding)
                )
        ) {
            FormBiodata(
                onProceed = {
                    keyboardController?.hide()

                    scope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()

                        snackbarHostState.showSnackbar(
                            message = pesanBerhasil,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            )
        }

        // Diletakkan terakhir supaya tampil di atas form.
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .imePadding()
                .padding(dimensionResource(R.dimen.snackbar_padding))
        ) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = colorResource(
                    R.color.snackbar_background
                ),
                contentColor = colorResource(R.color.snackbar_text)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BiodataScreenPreview() {
    androidx.compose.material3.MaterialTheme {
        BiodataScreen()
    }
}