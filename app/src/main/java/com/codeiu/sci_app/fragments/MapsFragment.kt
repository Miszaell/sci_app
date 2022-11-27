package com.codeiu.sci_app.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.Resources.NotFoundException
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.codeiu.sci_app.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener {

    private lateinit var map: GoogleMap
    private lateinit var lastKnownLocation: Location
    private lateinit var cameraPosition: CameraPosition
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var TAG: String
    private val defaultLocation = LatLng(19.432500, -99.133183)

    companion object {
        const val REQUEST_CODE_LOCATION = 0
        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"
        private const val DEFAULT_ZOOM = 15
    }

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        setStyles(googleMap)
//        createMarker(googleMap)
        getDeviceLocation()
        if (isLocationPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
        map.setOnMyLocationClickListener(this)
        map.setOnMyLocationButtonClickListener(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        TAG = MapsFragment::class.java.simpleName
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION)!!
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)!!
        }
        fusedLocationProviderClient = activity?.let {
            LocationServices.getFusedLocationProviderClient(
                it
            )
        }!!
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun setStyles(googleMap: GoogleMap) {
        try {
            var success: Boolean = googleMap.setMapStyle(
                context?.let {
                    MapStyleOptions.loadRawResourceStyle(
                        it, R.raw.style_json
                    )
                }
            )

            if (success) {
                Log.e(TAG, "Style parsing success")
            } else {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
    }

    private fun createMarker(googleMap: GoogleMap) {

        val coords = LatLng(19.432500, -99.133183)
        googleMap.addMarker(MarkerOptions().position(coords).title("México"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coords, 15f), 4000, null)
    }

    private fun isLocationPermissionGranted() = context?.let {
        ContextCompat.checkSelfPermission(
            it,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    } == PackageManager.PERMISSION_GRANTED


    private fun requestLocationPermission() {
        if (activity?.let {
                ActivityCompat.shouldShowRequestPermissionRationale(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == true) {
            Toast.makeText(
                requireContext(),
                "Dirigete  ajustes y acepta los permisos para obtener tu localización",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_CODE_LOCATION
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    requireContext(),
                    "Para activar la localización, dirigete  ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(
            requireContext(),
            "Trasladando...",
            Toast.LENGTH_SHORT
        ).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(
            requireContext(),
            "Coords: ${p0.latitude}, ${p0.longitude}",
            Toast.LENGTH_SHORT
        ).show()
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation

            activity?.let {
                locationResult.addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            map?.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        lastKnownLocation!!.latitude,
                                        lastKnownLocation!!.longitude
                                    ), DEFAULT_ZOOM.toFloat()
                                )
                            )
                        }
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.")
                        Log.e(TAG, "Exception: %s", task.exception)
                        map?.moveCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat())
                        )
                        map?.uiSettings?.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }
}