package com.android.composition.presentation

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.android.composition.R
import com.android.composition.domain.entities.GameResult

interface OnOptionClickListener{
    fun onOptionClick(option: Int)
}

@BindingAdapter("requiredAnswers")
fun bindingRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("requiredPercents")
fun bindingRequiredPercents(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindingScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindingScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("resultImage")
fun bindingImage(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}
//////

@BindingAdapter("enoughPercent")
fun bindingEnoughPercent(progressBar: ProgressBar, goodState: Boolean) {
    val color = getColorByState(progressBar, goodState)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(view: View, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(view.context, colorResId)
}

@BindingAdapter("minPercent")
fun bindingMinPercent(progressBar: ProgressBar, progress: Int) {
    progressBar.secondaryProgress = progress
}

@BindingAdapter("percentOfRightAnswers")
fun bindingPercentOfRightAnswers(progressBar: ProgressBar, progress: Int) {
    progressBar.progress = progress
}

@BindingAdapter("enoughCount")
fun bindingEnoughCount(textView: TextView, goodState: Boolean) {
    textView.setTextColor(getColorByState(textView, goodState))
}

@BindingAdapter("numberAsString")
fun bindingNumberAsString(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindingOnOptionClick(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener{
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}