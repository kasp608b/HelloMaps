package easv.oe.hellomaps

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val TAG = "xyz"

    private val EASV = LatLng(55.488230, 8.446936)
    private val BAKER = LatLng(55.485386, 8.451585)
    private val ROUND = LatLng(55.473939, 8.435959)

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val easvMarker = MarkerOptions().position(EASV).title("EASV")
        val bakerMarker = MarkerOptions().position(BAKER).title("Baker").icon(
            BitmapDescriptorFactory.fromResource(
                R.drawable.cake
            )
        )
        val roundAboutMarker = MarkerOptions().position(ROUND).title("Round-about")
        mMap.addMarker(easvMarker)
        mMap.addMarker(bakerMarker)
        mMap.addMarker(roundAboutMarker)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(EASV))

        setupZoomlevels()
    }

    fun onClickEASV(view: View) {
        val level: Int = spinnerZoomLevel.getSelectedItem().toString().toInt()
        val viewPoint = CameraUpdateFactory.newLatLngZoom(EASV, level.toFloat())
        // zoomlevel 0..21, where 0 is the world and 21 is single street
        // zoomlevel 0..21, where 0 is the world and 21 is single street
        Log.d(TAG, "Will zoom to easv to level $level")
        mMap.animateCamera(viewPoint)
    }

    private fun setupZoomlevels() {
        spinnerZoomLevel.adapter =
            ArrayAdapter.createFromResource(
                    this,
                             R.array.zoomlevels,
                             android.R.layout.simple_spinner_dropdown_item
        )
    }
}