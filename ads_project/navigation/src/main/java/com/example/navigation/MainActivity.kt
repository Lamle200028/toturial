package com.example.navigation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle("TIÊU ĐỀ ACTIVITY")
        actionBar?.setLogo(R.drawable.ic_launcher_background)

        val title: String = actionBar?.getTitle().toString()

//        actionBar?.hide()
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        activityMainBinding.apply {

            // TODO custom bottom
            bottomNav.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.home_item -> {
                        val navController = findNavController(R.id.nav_host_fragment)
                        navController.navigate(R.id.homeFragment)
                    }
                    R.id.camera_item ->{
                        val navController = findNavController(R.id.nav_host_fragment)
                        navController.navigate(R.id.splashFragment)
                    }
                    R.id.account_item ->{
                        val navController = findNavController(R.id.nav_host_fragment)
                        navController.navigate(R.id.settingFragment)
                    }
                    else ->{

                    }
                }
                true
            }
            //TODO custom drawer
            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.home_item ->{
                        tranferFragment(R.id.nav_host_fragment, R.id.homeFragment)
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.camera_item ->{
                        tranferFragment(R.id.nav_host_fragment, R.id.splashFragment)
                        drawerLayout.closeDrawer(GravityCompat.START)
                        hintbottom()
                    }
                }
                true
            }

        }
    }
    fun hintbottom(){
        activityMainBinding.bottomNav.visibility = View.GONE

    }
    fun openDrawer(){
        activityMainBinding.drawerLayout.openDrawer(GravityCompat.START)
    }
    fun closeDrawer(){
        activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    //Todo chuyển đổi giữa các fragment sử dụng getSuport
    fun setcurrunt(newfragment: Fragment){
        val ft: FragmentTransaction = getSupportFragmentManager().beginTransaction()
        ft.replace(R.id.nav_host_fragment, newfragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.addToBackStack(null)
        ft.commit()
    }

    //Todo chuyển đổi màn sử dụng navigation

    fun tranferFragment(id : Int, id2 : Int){
        val navController = findNavController(id)
        navController.navigate(id2)
    }



    //Todo setup click item của thanh toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        if (item.itemId == android.R.id.home){
            tranferFragment(R.id.nav_host_fragment, R.id.homeFragment)
        }
    }
}