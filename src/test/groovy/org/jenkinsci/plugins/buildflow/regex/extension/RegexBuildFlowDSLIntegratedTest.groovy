package org.jenkinsci.plugins.buildflow.regex.extension

import com.cloudbees.plugins.flow.BuildFlow
import com.cloudbees.plugins.flow.BuildFlowDSLExtension
import com.cloudbees.plugins.flow.FlowRun
import hudson.model.Result
import jenkins.model.Jenkins
import org.jvnet.hudson.test.HudsonTestCase

/**
 * Created by jniesen on 5/6/14.
 */
class HttpBuildFlowDSLIntegratedTest extends HudsonTestCase {
  void testUseExtension() {
    assertEquals(1, 1)
  }
}
