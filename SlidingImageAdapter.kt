package com.rams.automaticimageslider

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class SlidingImageAdapter(private val context:Context, private val imageModelArrayList: ArrayList<ImageModel>): PagerAdapter() {

    private val inflater: LayoutInflater

    init{
        inflater = LayoutInflater.from(context)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0==p1
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.slidingimages_layout,view,false)!!
        val imageView = imageLayout.findViewById(R.id.image) as ImageView

        imageView.setImageResource(imageModelArrayList[position].getImage_drawable())
        view.addView(imageLayout, 0)
        return imageLayout
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

}