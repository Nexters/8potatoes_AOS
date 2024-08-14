package com.eight_potato.ui.permission

import android.Manifest
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.ui.databinding.ActivityBaseBinding
import com.eight_potato.ui.manager.LocationManager
import com.eight_potato.ui.permission.ui.RequestPermissionBody


class RequestPermissionDialog: DialogFragment()  {
    private var onResponse: ((Boolean) -> Unit)? = null
    lateinit var binding: ActivityBaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.container.setContent {
            HyusikMatjuTheme {
                RequestPermissionBody {
                    locationPermissionRequest.launch(
                        LocationManager.LOCATION_PERMISSION
                    )
                }
            }
        }
    }

    fun setOnResponse(onResponse: (Boolean) -> Unit): RequestPermissionDialog {
        this.onResponse = onResponse
        return this
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onResponse?.invoke(false)
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocation = permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)
        val coarseLocation = permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)

        onResponse?.invoke(fineLocation || coarseLocation)
        onResponse = null
        dismiss()
    }
}