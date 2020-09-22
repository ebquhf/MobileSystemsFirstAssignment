package com.example.firstassignment


import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Permission


class MainActivity : AppCompatActivity(),LocationListener,SensorEventListener {

    lateinit var locationManager: LocationManager
    lateinit var sensorManager: SensorManager

    val REQUEST_LOCATION = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager =  getSystemService(Context.SENSOR_SERVICE) as SensorManager
       sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_NORMAL)

        setLocation(

        )
    }

    private fun setLocation() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
            &&ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
            arrayOf<String>(ACCESS_FINE_LOCATION),REQUEST_LOCATION)
        }else {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()
            val provider = locationManager.getBestProvider(criteria,false)
            val location = locationManager.getLastKnownLocation(provider)
            if (location!= null){
                text_view.text = "Latitude: ${location.latitude}\n\n" + "Longitude: ${location.latitude}"
            }else{
                Toast.makeText(this,"Location not available",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onLocationChanged(location: Location?) {
        TODO("Not yet implemented")
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("Not yet implemented")
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("Not yet implemented")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION){
            setLocation()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        lightmeter_data.text = "Lx: ${event?.values?.get(0)}"
    }
}