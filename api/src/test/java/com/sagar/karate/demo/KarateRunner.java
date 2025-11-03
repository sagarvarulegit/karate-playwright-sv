package com.sagar.karate.demo;

import com.intuit.karate.junit5.Karate;

/**
 * This is the main JUnit 5 test runner.
 * It will automatically find and execute all .feature files
 * in this package (com.sagar.karate.demo) and its sub-packages.
 */
class KarateRunner {
    
    @Karate.Test
    Karate testAll() {
        // .relativeTo(getClass()) is the key.
        // It tells Karate to look for feature files in the same
        // directory and sub-directories as this Java class.
        return Karate.run().relativeTo(getClass());
    }
}
