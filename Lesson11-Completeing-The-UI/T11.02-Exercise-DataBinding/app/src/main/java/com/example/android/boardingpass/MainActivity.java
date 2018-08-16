package com.example.android.boardingpass;

/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.boardingpass.databinding.ActivityMainBinding;
import com.example.android.boardingpass.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        BoardingPassInfo boardingPassInfo = FakeDataUtils.generateFakeBoardingPassInfo();
        displayBoardingPassInfo(boardingPassInfo);

    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        mActivityMainBinding.textViewPassengerName.setText(info.passengerName);
        mActivityMainBinding.textViewFlightCode.setText(info.flightCode);
        mActivityMainBinding.textViewOriginAirport.setText(info.originCode);
        mActivityMainBinding.textViewDestinationAirport.setText(info.destCode);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        mActivityMainBinding.textViewBoardingTime.setText(simpleDateFormat.format(info.boardingTime));
        mActivityMainBinding.textViewDepartureTime.setText(simpleDateFormat.format(info.departureTime));
        mActivityMainBinding.textViewArrivalTime.setText(simpleDateFormat.format(info.arrivalTime));

        mActivityMainBinding.textViewTerminal.setText(info.departureTerminal);
        mActivityMainBinding.textViewGate.setText(info.departureGate);
        mActivityMainBinding.textViewSeat.setText(info.seatNumber);

        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minuteLessHoursUntilBoarding = totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);
        String hoursAndMinutesUntilBoarding = String.format("%d:%d", hoursUntilBoarding, minuteLessHoursUntilBoarding);

        mActivityMainBinding.textViewBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);

    }
}

