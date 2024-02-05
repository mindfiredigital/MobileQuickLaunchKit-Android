package com.foss.core_ui.widgets

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun MQLKWebView(navController: NavController, url: String?) {
    MFMKAppBarWrapper(navController = navController) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.setSupportZoom(true)
                }
            },
            update = { webView ->
                webView.loadUrl(if (!url.isNullOrBlank()) url else "https://www.google.com")
            },
            modifier = it.fillMaxSize()
        )
    }
}