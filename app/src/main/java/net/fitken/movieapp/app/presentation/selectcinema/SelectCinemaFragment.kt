package net.fitken.movieapp.app.presentation.selectcinema

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.addPolyline
import com.google.maps.android.ktx.awaitMap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import net.fitken.movieapp.R
import net.fitken.movieapp.app.utils.textChanges
import net.fitken.movieapp.base.activity.BaseActivity
import net.fitken.movieapp.base.delegate.viewBinding
import net.fitken.movieapp.base.extension.observe
import net.fitken.movieapp.base.fragment.BaseFragment
import net.fitken.movieapp.base.navigation.NavManager
import net.fitken.movieapp.databinding.FragmentSelectCinemaBinding
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class SelectCinemaFragment : BaseFragment(R.layout.fragment_select_cinema) {

    private val binding: FragmentSelectCinemaBinding by viewBinding()
    private val viewModel: SelectCinemaViewModel by viewModels()
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation: LatLng? = null


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

    private val stateObserver = Observer<SelectCinemaViewModel.ViewState> {
        binding.isLoading = it.isLoading
        if (it.address != null) {
            map.clear()
            val cinema = LatLng(it.address.latitude, it.address.longitude)
            map.addMarker {
                position(cinema)
                title(it.address.getAddressLine(0))
            }
            map.moveCamera(CameraUpdateFactory.newLatLng(cinema))
        }
        if (it.stepDirection != null) {
            map.addPolyline {
                it.stepDirection.forEach { step ->
                    val startLatLng = LatLng(step.startLocation.lat, step.startLocation.lng)
                    val endLatLng = LatLng(step.endLocation.lat, step.endLocation.lng)
                    add(startLatLng, endLatLng).width(15F).color(Color.BLUE)
                }
            }
        }
    }


    @SuppressLint("PotentialBehaviorOverride")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(binding.toolbar)

        observe(viewModel.stateLiveData, stateObserver)

        viewModel.init(Geocoder(requireContext()))

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        lifecycleScope.launchWhenCreated {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            map = mapFragment.awaitMap()
            map.moveCamera(CameraUpdateFactory.zoomTo(15F))

            checkPermissions()

            map.setOnInfoWindowClickListener {
                val action =
                    SelectCinemaFragmentDirections.actionSelectCinemaFragmentToDashboardFragment()
                navManager.navigate(action)
            }
        }

        binding.etSearch.textChanges()
            .filterNot { it.isNullOrBlank() }
            .drop(1)
            .debounce(1000L)
            .map { it.toString() }
            .onEach {
                viewModel.loadAddress(it, currentLocation)
            }
            .launchIn(lifecycleScope)
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
            currentLocation = LatLng(it.latitude, it.longitude)
            map.moveCamera(
                CameraUpdateFactory.newLatLng(currentLocation!!)
            )
        }
    }
}