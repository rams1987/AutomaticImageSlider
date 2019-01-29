package com.rams.automaticimageslider

import android.media.MediaPlayer
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.viewpagerindicator.CirclePageIndicator
import java.util.ArrayList
import java.util.Timer
import java.util.TimerTask


//https://demonuts.com/kotlin-image-slider/
class MainActivity : AppCompatActivity() {

    private var imageModelArrayList: ArrayList<ImageModel>? = null
    private val myImageList = intArrayOf(R.drawable.bear, R.drawable.deer, R.drawable.fox, R.drawable.lion, R.drawable.panther, R.drawable.tiger)

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()

        Toast.makeText(this, "Click on ", Toast.LENGTH_SHORT).show()

        init()
    }

    private fun populateList(): ArrayList<ImageModel> {

        val list = ArrayList<ImageModel>()

        for (i in 0..5) {
            val imageModel = ImageModel()
            imageModel.setImage_drawables(myImageList[i])
            list.add(imageModel)
        }

        return list
    }

    private fun init() {

        mPager = findViewById(R.id.pager) as ViewPager
        mPager!!.adapter = SlidingImageAdapter(this@MainActivity, this.imageModelArrayList!!)

        val indicator = findViewById(R.id.indicator) as CirclePageIndicator

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.setRadius(5 * density)

        NUM_PAGES = imageModelArrayList!!.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
            mediaPlayer = MediaPlayer.create(this, R.raw.lion)
            mediaPlayer!!.start()
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })
    }

    companion object {

        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }

}
