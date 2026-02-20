package com.kancharla.abhilash.fc_003

import androidx.lifecycle.ViewModel

class MissionViewModel : ViewModel() {
    var selectedStrategy: String? = null
    var helicopterDismissed: Boolean = false
    var trustLevel: Int = 0
}
