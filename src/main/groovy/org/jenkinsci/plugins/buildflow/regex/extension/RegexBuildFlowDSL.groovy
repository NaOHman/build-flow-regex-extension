package org.jenkinsci.plugins.buildflow.regex.extension

import com.cloudbees.plugins.flow.FlowDelegate
import com.cloudbees.plugins.flow.JobNotFoundException
import hudson.model.AbstractProject
import hudson.model.ItemGroup
import jenkins.model.Jenkins

/**
 * Created by jniesen on 5/5/14.
 */
class RegexBuildFlowDSL {
    def FlowDelegate dsl;
    def RegexBuildFlowDSL(FlowDelegate dsl) {
        this.dsl = dsl;
    }

    def build_matches(String regex){
        build_matches([:], regex);
    }

    def build_matches(Map args, String regex) {
        //get project names
        final ItemGroup context = dsl.getProject().getParent();
        List<String> matchingProjects = new ArrayList<>();
        for (AbstractProject project : Jenkins.getInstance().getItems((ItemGroup) context, AbstractProject.class) ){
            if (project.getName().matches(regex)){
                matchingProjects.add(project.getName());
            }
        }
        if (matchingProjects.isEmpty()){
            throw new JobNotFoundException("No jobs found matching regex: " + regex);
        }
        for (String project: matchingProjects){
            dsl.build(project);
        }
    }
}
