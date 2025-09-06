package com.slivsound.ui.components.base

sealed class ComponentState {

    data object Enabled : ComponentState()
    data object Disabled : ComponentState()
    data object Loading : ComponentState()
}