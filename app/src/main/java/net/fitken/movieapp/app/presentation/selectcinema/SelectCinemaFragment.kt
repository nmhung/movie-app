package net.fitken.movieapp.app.presentation.selectcinema

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import dagger.hilt.android.AndroidEntryPoint
import net.fitken.movieapp.R
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.base.navigation.NavManager
import net.fitken.movieapp.databinding.FragmentSelectCinemaBinding
import javax.inject.Inject

@AndroidEntryPoint
class SelectCinemaFragment : BaseFragment(R.layout.fragment_select_cinema) {

    private val binding: FragmentSelectCinemaBinding by viewBinding()
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    @Inject
    lateinit var navManager: NavManager

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val grantedList = permissions.entries.filter { it.value == true }
            if (grantedList.size == permissions.size) {
                moveToCurrentLocation()
            } else {
                showError("Location permission denied")
            }
        }

    @SuppressLint("PotentialBehaviorOverride")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        lifecycleScope.launchWhenCreated {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            map = mapFragment.awaitMap()
            map.moveCamera(CameraUpdateFactory.zoomTo(15F))

            checkPermissions()


            val saigon = LatLng(10.772365414541174, 106.70429737409198)
            val marker = map.addMarker {
                position(saigon)
                title("Marker in Saigon")
            }

            map.setOnInfoWindowClickListener {
                val action =
                    SelectCinemaFragmentDirections.actionSelectCinemaFragmentToDashboardFragment()
                navManager.navigate(action)
            }
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            moveToCurrentLocation()
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun moveToCurrentLocation() {
        map.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener {
            map.moveCamera(
                CameraUpdateFactory.newLatLng(
                    LatLng(it.latitude, it.longitude)
                )
            )
        }
    }
}