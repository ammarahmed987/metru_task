package com.example.metru.utils.Schedulers

import io.reactivex.Scheduler
import org.jetbrains.annotations.NotNull

/**
 *  @author Abdullah Nagori
 */

interface BaseScheduler {
    @NotNull
    fun io(): Scheduler

    @NotNull
    fun ui(): Scheduler
}