package org.jobrunr.server.runner;

import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.JobDetails;

import static org.jobrunr.utils.JobUtils.assertJobExists;
import static org.jobrunr.utils.reflection.ReflectionUtils.hasDefaultNoArgConstructor;

public class BackgroundJobWithoutIocRunner extends AbstractBackgroundJobRunner {

    @Override
    public boolean supports(Job job) {
        JobDetails jobDetails = job.getJobDetails();
        assertJobExists(jobDetails);
        return !jobDetails.hasStaticFieldName() && hasDefaultNoArgConstructor(jobDetails.getClassName());
    }

    @Override
    protected BackgroundJobWorker getBackgroundJobWorker(Job job) {
        return new BackgroundJobWorker(job);
    }
}
