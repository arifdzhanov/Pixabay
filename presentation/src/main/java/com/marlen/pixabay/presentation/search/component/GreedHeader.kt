package com.marlen.pixabay.presentation.search.component

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable

fun LazyStaggeredGridScope.fillWidthItem
            (
    content: @Composable LazyStaggeredGridItemScope.() -> Unit
) {
    item(span =  StaggeredGridItemSpan.FullLine , content = content)
}