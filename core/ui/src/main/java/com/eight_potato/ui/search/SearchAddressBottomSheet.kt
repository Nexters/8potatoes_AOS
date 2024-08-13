package com.eight_potato.ui.search

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetState
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.eight_potato.designsystem.input.HyusikOutlinedTextField
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.ui.databinding.ActivityBaseBinding
import com.eight_potato.ui.ext.getSerializable
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.model.address.AddressUiModel
import com.eight_potato.ui.navigator.Navigator
import com.eight_potato.ui.search.ui.SearchAddressScreen
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 위치 검색 BottomSheet
 */
@AndroidEntryPoint
class SearchAddressBottomSheet : BottomSheetDialogFragment() {
    private val viewModel: SearchAddressViewModel by viewModels()
    private lateinit var binding: ActivityBaseBinding
    private var onSelectAddress: ((AddressUiModel) -> Unit)? = null

    @Inject
    lateinit var navigator: Navigator

    fun onSelectAddress(
        onSelect: (AddressUiModel) -> Unit
    ): SearchAddressBottomSheet {
        this.onSelectAddress = {
            onSelect(it)
            dismiss()
        }
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), theme)
        bottomSheetDialog.setOnShowListener { dialog ->
            (dialog as BottomSheetDialog).findViewById<FrameLayout>(
                com.google.android.material.R.id.design_bottom_sheet
            )?.let {
                BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
                BottomSheetBehavior.from(it).isHideable = true
                BottomSheetBehavior.from(it).skipCollapsed = true
            }
        }
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.container.setContent {
            HyusikMatjuTheme {
                val address = viewModel.address.collectAsState()
                val keyword = viewModel.keyword.collectAsState()

                SearchAddressScreen(
                    keyword = keyword.value,
                    addresses = address.value,
                    onClickCloseButton = { dismiss() },
                    onClearKeyword = { viewModel.changeKeyword("") },
                    onChangeKeyword = viewModel::changeKeyword,
                    onClickCurrentPosition = {
                        context?.let {
                            searchCurrentLocationResult.launch(
                                navigator.getCurrentPositionActivity(it)
                            )
                        }
                    },
                    onClickAddress = {
                        onSelectAddress?.invoke(it)
                    }
                )
            }
        }
    }

    private val searchCurrentLocationResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.getSerializable<AddressUiModel>("location")?.let { address ->
                onSelectAddress?.invoke(address)
            }
        }
    }

    companion object {
        const val SEARCH_ADDRESS_SHEET = "search_address"
    }
}