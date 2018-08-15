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
package com.example.android.background.sync;


import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import android.os.AsyncTask;

public class WaterReminderFirebaseJobService extends JobService {
    private AsyncTask mAsyncTask;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        mAsyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                ReminderTasks.executeTask(WaterReminderFirebaseJobService.this, ReminderTasks.ACTION_CHARGING_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(jobParameters, false);
            }
        };
        mAsyncTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if(mAsyncTask != null) { mAsyncTask.cancel(true); }
        return true;
    }

}
