package com.winson.apibasedemo.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.winson.apibasedemo.R

/**
 * @date 2019/11/25
 * @author winson-zhou
 */
class StairBannerView : FrameLayout {

    constructor(context: Context) : super(context) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr, 0)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    //    private val imageUrl = "http://img3.imgtn.bdimg.com/it/u=2783525887,3246024378&fm=26&gp=0.jpg"
    private val imageUrl1 =
        "https://img.shixijob.net/college/20190921/E08197083058746ACB97795FAE3FEC9F.jpg"
    private val imageUrl2 =
        "https://img.shixijob.net/college/20190921/370B196584E027705A6B5BD2DC6417E8.jpeg"
    private val imageUrl3 =
        "https://img.shixijob.net/college/20190829/6013B011049502A0C36E7BFFB4AB8D7F.png"
    private val imageUrl4 =
        "https://img.shixijob.net/college/20190829/B0B3F990EC4292751E7A7DDA1CE3263E.png"
    private val imageUrl5 =
        "https://img.shixijob.net/college/20190829/A4DE10855E024376B4AC0AE111E0A1F7.png"
    private val imageUrl6 =
        "https://img.shixijob.net/college/20190830/54B10C10D4DE1B4FE2315C184B5D9395.jpg"

    private val testImageUrl =
        arrayListOf(imageUrl1, imageUrl2, imageUrl3, imageUrl4, imageUrl5, imageUrl6)

    private val fiveScale = 0.7f
    private var fiveTranslateX = 0f
    private var b45w = 0f

    private val fourScale = 0.8f
    private var fourTranslateX = 0f
    private var b34w = 0f

    private val threeScale = 0.9f
    private var threeTranslateX = 0f

    private val aspectRatio = 2.38f
    private var paddTB = 0
    private var paddLR = 0
    private var offset = 0
    private var radius = 0f

    private fun fromLayoutParams(): LayoutParams {
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val leftMargin = paddLR
        val rightMargin = paddLR + offset * 2
        layoutParams.leftMargin = leftMargin
        layoutParams.rightMargin = rightMargin
        return layoutParams
    }

    fun actionScroll(scrollOffset: Float) {

        // second hierarchy view
        val secondView = getChildAt(3)
        secondView.translationX = -(secondView.width + paddTB + paddLR) * scrollOffset
//        secondView.translationX = -secondView.width * 2f

        // third hierarchy view
        val ts = threeScale + (1f - threeScale) * scrollOffset
        val thirdView = getChildAt(2)
        thirdView.translationX = threeTranslateX * (1 - scrollOffset)
        thirdView.scaleX = ts
        thirdView.scaleY = ts
//        thirdView.translationX = -secondView.width * 2f

        // four hierarchy view
        val fs = fourScale + (threeScale - fourScale) * scrollOffset
        val fourView = getChildAt(1)
        fourView.translationX = threeTranslateX + b34w * (1 - scrollOffset)
        fourView.scaleX = fs
        fourView.scaleY = fs

        // five hierarchy view
        val fiveS = fiveScale + (fourScale - fiveScale) * scrollOffset
        val fiveView = getChildAt(0)
        fiveView.translationX = fourTranslateX + b45w * (1 - scrollOffset)
        fiveView.alpha = scrollOffset
        fiveView.scaleX = fiveS
        fiveView.scaleY = fiveS

    }

    private fun refreshLayoutParams() {
        for (i in 0 until childCount) {
            getChildAt(i).layoutParams = fromLayoutParams()
        }

        val topImage = getChildAt(childCount - 1)
        fiveTranslateX = topImage.width * (1 - fiveScale) / 2f + offset + offset + offset
        fourTranslateX = topImage.width * (1 - fourScale) / 2f + offset + offset
        threeTranslateX = topImage.width * (1 - threeScale) / 2f + offset
        b45w = fiveTranslateX - fourTranslateX
        b34w = fourTranslateX - threeTranslateX

        // five
        val fiveImage = getChildAt(0)
        fiveImage.scaleX = fiveScale
        fiveImage.scaleY = fiveScale
        fiveImage.alpha = 0.0f
        fiveImage.translationX = fiveTranslateX

        // four
        val fourImage = getChildAt(1)
        fourImage.scaleX = fourScale
        fourImage.scaleY = fourScale
        fourImage.translationX = fourTranslateX

        // three
        val threeImage = getChildAt(2)
        threeImage.scaleX = threeScale
        threeImage.scaleY = threeScale
        threeImage.translationX = threeTranslateX

        // one
        val oneTx = topImage.width * -1f - offset * 5
        topImage.translationX = oneTx

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun init(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {
        clipChildren = false
        clipToPadding = false
        paddTB = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            20f,
            context.resources.displayMetrics
        ).toInt()

        paddLR = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            20f,
            context.resources.displayMetrics
        ).toInt()

        offset = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            10f,
            context.resources.displayMetrics
        ).toInt()

        radius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            5f,
            context.resources.displayMetrics
        )

        val rp = RoundingParams.fromCornersRadius(radius)

        for ((index) in (0..4).withIndex()) {
            val fiveImage = SimpleDraweeView(context)
            fiveImage.setImageURI(testImageUrl[index])
            fiveImage.hierarchy.actualImageScaleType = ScalingUtils.ScaleType.CENTER_CROP
            fiveImage.hierarchy.roundingParams = rp
            fiveImage.aspectRatio = aspectRatio
            addView(fiveImage)
        }

        refreshLayoutParams()
        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                refreshLayoutParams()
                viewTreeObserver.removeOnPreDrawListener(this)
                return false
            }

        })

    }

}