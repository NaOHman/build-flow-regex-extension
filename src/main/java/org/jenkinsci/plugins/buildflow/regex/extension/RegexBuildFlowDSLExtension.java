package org.jenkinsci.plugins.buildflow.regex.extension;

import com.cloudbees.plugins.flow.BuildFlowDSLExtension;
import com.cloudbees.plugins.flow.FlowDelegate;
import hudson.Extension;

/**
 * Created by jniesen on 5/5/14.
 */

@Extension
public class RegexBuildFlowDSLExtension extends BuildFlowDSLExtension {

    public static final String EXTENSION_NAME = "build-flow-regex-extension";

    @Override
    public Object createExtension(String extensionName, FlowDelegate dsl) {
      if (extensionName == this.EXTENSION_NAME) {
          return new RegexBuildFlowDSL(dsl);
      }
      return null;
    }
}
