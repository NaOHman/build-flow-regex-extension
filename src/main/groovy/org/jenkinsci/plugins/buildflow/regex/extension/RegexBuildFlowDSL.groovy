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

    def buildMatches(String regex){
        buildMatches([:], regex);
    }

    def buildMatches(Map args, String regex){
        List<AbstractProject> matchingProjects = getMatchingProjects(regex);
        for (String project: matchingProjects){
            dsl.build(args, project);
        }
    }

    def buildMatchesParallel(String regex){
        buildMatchesParallel([:], regex);
    }

    def buildMatchesParallel(Map args, String regex){
        List<AbstractProject> matchingProjects = getMatchingProjects(regex);
        List<Closure> closures = new ArrayList();
        for (String project: matchingProjects){
            closures.add({p -> dsl.build(args, p)}.curry(project));
        }
        dsl.parallel(closures);
    }

    def getMatchingProjects(String regex) {
        //get project names
        List<String> matchingProjects = new ArrayList<>();
        for (AbstractProject project : Jenkins.getInstance().getAllItems(AbstractProject.class)){
            if (project.getName().matches(regex)){
                matchingProjects.add(project.getName());
            }
        }
        if (matchingProjects.isEmpty()){
            throw new JobNotFoundException("No jobs found matching regex: " + regex);
        }
        return matchingProjects;
    }
}
