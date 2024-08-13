package com.eight_potato.ui.direction

import androidx.activity.viewModels
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.navigator.Navigator
import com.eight_potato.ui.search.SearchAddressBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class DirectionActivity : BaseActivity() {
    protected val viewModel: DirectionViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    protected fun moveToSearchScreenForStart() {
        SearchAddressBottomSheet()
            .onSelectAddress { viewModel.setStart(it) }
            .show(
                supportFragmentManager,
                SearchAddressBottomSheet.SEARCH_ADDRESS_SHEET
            )
    }

    protected fun moveToSearchScreenForEnd() {
        SearchAddressBottomSheet()
            .onSelectAddress { viewModel.setEnd(it) }
            .show(
                supportFragmentManager,
                SearchAddressBottomSheet.SEARCH_ADDRESS_SHEET
            )
    }

    companion object {
        const val START_ARGS = "start"
        const val END_ARGS = "end"
    }
}