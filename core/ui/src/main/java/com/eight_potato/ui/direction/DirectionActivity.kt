package com.eight_potato.ui.direction

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.ext.getSerializable
import com.eight_potato.ui.model.address.AddressUiModel
import com.eight_potato.ui.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class DirectionActivity : BaseActivity() {
    protected val viewModel: DirectionViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    protected fun moveToSearchScreenForStart() {
        startLocationLauncher.launch(navigator.getSearchAddressActivityIntent(this))
    }

    protected fun moveToSearchScreenForEnd() {
        endLocationLauncher.launch(navigator.getSearchAddressActivityIntent(this))
    }

    private val startLocationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.getSerializable<AddressUiModel>("location")?.let { address ->
                viewModel.setStart(address)
            }
        }
    }

    private val endLocationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.getSerializable<AddressUiModel>("location")?.let { address ->
                viewModel.setEnd(address)
            }
        }
    }

    companion object {
        const val START_ARGS = "start"
        const val END_ARGS = "end"
    }
}