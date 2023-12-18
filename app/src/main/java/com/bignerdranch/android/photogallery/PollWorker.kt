package com.bignerdranch.android.photogallery

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import androidx.work.Worker

private const val TAG = "PollWorker"
class PollWorker(val context: Context, workerParams: WorkerParameters)
    : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.i(TAG, "Work request triggered")
        return Result.success()
    }
}