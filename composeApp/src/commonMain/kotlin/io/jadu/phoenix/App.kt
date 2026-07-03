package io.jadu.phoenix

import io.jadu.phoenix.ui.theme.AppTheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.jadu.phoenix.navigation.AppNavigation

@Composable
@Preview
fun App() {
    AppTheme {
        AppNavigation()
    }
}
