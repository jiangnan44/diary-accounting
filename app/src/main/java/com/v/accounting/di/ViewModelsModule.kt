package com.v.accounting.di

import com.v.accounting.ui.home.HomeViewModel
import com.v.accounting.ui.mine.MineViewModel
import com.v.accounting.ui.statistic.StatisticViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Author:v
 * Time:2021/6/1
 */
val viewModelsModule = module {
    viewModel { HomeViewModel() }
    viewModel { StatisticViewModel() }
    viewModel { MineViewModel() }
}